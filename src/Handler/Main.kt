package Handler

import java.io.File

fun main(args: Array<String>){
    var coder = ImageCoder(password = "1234567890123456")
    var result = coder.code(File("photo_2020-01-04_12-06-22.jpg"))
    File("result.jpg").writeBytes(result[0] as ByteArray)
    var decoder = ImageCoder(password = "1234567890123456", mode = "dec")
    result = decoder.code(File("result.jpg"))
    File("resultOfDecode.jpg").writeBytes(result[0] as ByteArray)
}