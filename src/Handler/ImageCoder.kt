package Handler

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.imageio.ImageIO


class ImageCoder(private var mode: String = "enc", private var password: String) : Coder {

    //     main cryptography parameters
    private val transformation = "AES/CBC/PKCS5Padding"
    private val algorithm = "AES"

    override fun code(image: BufferedImage): Array<Any> {
        var resultImage: BufferedImage = BufferedImage(0,0,0)
//        image to byte[]
        val baos = ByteArrayOutputStream()
        ImageIO.write(image, "jpg", ByteArrayOutputStream())
        baos.flush()
        val fileContent = baos.toByteArray()
        baos.close()
//        create ivspec, key
        val ivspec = IvParameterSpec(password.toByteArray())
        val key: SecretKeySpec
        try {
            key = SecretKeySpec(password.toByteArray(), algorithm)
        }
        catch(exc: Exception){
            return arrayOf(resultImage, CodeResult.WRONG_KEY_LENGTH)
        }
//        create and init cipher
        val cipher = Cipher.getInstance(transformation)
        cipher.init(if(mode == "enc") Cipher.ENCRYPT_MODE
                    else Cipher.DECRYPT_MODE,
                    key, ivspec)
//        encrypt(decrypt) by cipher and convert to(from) base 64
        val result: ByteArray
        result = if(mode == "enc")
            Base64.getEncoder().encode(cipher.doFinal(fileContent))
        else {
            try {
                cipher.doFinal(Base64.getDecoder().decode(fileContent))
            } catch (exc: Exception) {return arrayOf(resultImage, CodeResult.WRONG_DECRYPT_KEY)}
        }
//        save result in image
        val bis = ByteArrayInputStream(result)
        resultImage = ImageIO.read(bis)
//        return result
        return arrayOf(resultImage, CodeResult.SUCCESS)
    }

    override fun code(listOfImages: List<BufferedImage>): Array<Any> {
        var resultList = mutableListOf<BufferedImage>()
        for(image in listOfImages) {
            val resultImageArray = code(image)
            if(resultImageArray[1] == CodeResult.SUCCESS)
                resultList.add(resultImageArray[0] as BufferedImage)
            else
                return arrayOf(resultList, resultImageArray[1])
        }
        return arrayOf(resultList, CodeResult.SUCCESS)
    }
}