package Handler

import java.awt.image.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class ImageCoder(private var mode: String = "enc", private var password: String = "00000000") {
//    initialize main cryptography parameters
    init {
        val transformation = "AES/CBC/PKCS5Padding"
        val algorithm = "AES"
        val ivspec = IvParameterSpec(password.toByteArray())
        val key = SecretKeySpec(password.toByteArray(), algorithm)
    }

    fun сode(image: BufferedImage): BufferedImage {

        return image
    }
}