package Handler

import java.awt.image.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class ImageCoder(private var mode: String = "enc", private var password: String = "00000000") : Coder {

//     main cryptography parameters
    private val transformation = "AES/CBC/PKCS5Padding"
    private val algorithm = "AES"

    override fun code(image: BufferedImage): Array<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val codeResult = CodeResult.NONE
        val codedImage = image

//        val ivspec = IvParameterSpec(password.toByteArray())
//        val key = SecretKeySpec(password.toByteArray(), algorithm)
//
//        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
//        cipher.init(Cipher.ENCRYPT_MODE, key, ivspec)
//    encrypt
        
//    val encryptedBase64 = Base64.getEncoder().encode(cipher.doFinal(fileContent))

        return arrayOf(codedImage, codeResult)
    }

    override fun code(listOfImages: List<BufferedImage>): Array<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var codeResult = CodeResult.NONE
        var resultList = listOfImages

        return arrayOf(resultList, codeResult)
    }
}