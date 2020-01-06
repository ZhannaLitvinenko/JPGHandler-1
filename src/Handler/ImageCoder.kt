package Handler

import java.io.File
import java.lang.Exception
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class ImageCoder(private var mode: String = "enc", private var password: String) : Coder {
    //     main cryptography parameters
    private val transformation = "AES/CBC/PKCS5Padding"
    private val algorithm = "AES"

    override fun code(image: File): Array<Any> {
//        get byte arr from file
        var resultArray = byteArrayOf()
        val fileContent = image.readBytes()
//        create ivspec, key
        if(password.length != 16)
            return arrayOf(resultArray, CodeResult.WRONG_KEY_LENGTH)

        val ivspec = IvParameterSpec(password.toByteArray())
        val key = SecretKeySpec(password.toByteArray(), algorithm)
//        create and init cipher
        val cipher = Cipher.getInstance(transformation)
        cipher.init(if(mode == "enc") Cipher.ENCRYPT_MODE
                    else Cipher.DECRYPT_MODE,
                    key, ivspec)
//        encrypt(decrypt) by cipher and convert to(from) base 64
        resultArray = if(mode == "enc")
            Base64.getEncoder().encode(cipher.doFinal(fileContent))
        else {
            try {
                cipher.doFinal(Base64.getDecoder().decode(fileContent))
            } catch (exc: Exception) {return arrayOf(resultArray, CodeResult.WRONG_DECRYPT_KEY)}
        }
//        return result
        return arrayOf(resultArray, CodeResult.SUCCESS)
    }

    override fun code(listOfImages: List<File>): Array<Any> {
        var resultList = mutableListOf<ByteArray>()
        for(image in listOfImages) {
            val resultImageArray = code(image)
            if(resultImageArray[1] == CodeResult.SUCCESS)
                resultList.add(resultImageArray[0] as ByteArray)
            else
                return arrayOf(resultList, resultImageArray[1])
        }
        return arrayOf(resultList, CodeResult.SUCCESS)
    }
}