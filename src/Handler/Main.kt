package Handler

import java.io.File

fun main(args: Array<String>){
    var coder = ImageCoder(password = "1234567890123456")
    var result = coder.code(File("some photo.jpg"))
    File("resultOfCode.jpg").writeBytes(result[0] as ByteArray)
    var decoder = ImageCoder(password = "1234567890123456", mode = "dec")
    result = decoder.code(File("resultOfCode.jpg"))
    File("resultOfDecode.jpg").writeBytes(result[0] as ByteArray)
}