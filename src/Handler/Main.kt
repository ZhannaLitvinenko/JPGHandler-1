package Handler

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun help(){
    println("There are such commands:\n" +
            "/path_[directory path] - the directory where picture(s) is(are)\n" +
//            "/path /f [image_path] - the full path to one image\n" +
            "/enc_[password] - to encode picture by password\n" +
            "/dec_[password] - to decode picture by password\n" +
            "/ex - to exit")
}
fun commandReader():List<String> = readLine().toString().split('_')
fun printResultInfo(codeResult: CodeResult){
    println(when(codeResult){
        CodeResult.SUCCESS -> "Done successfully."
        CodeResult.WRONG_DECRYPT_KEY -> "You have entered wrong key."
        CodeResult.WRONG_KEY_LENGTH -> "The key must have 16 symbols."
    })
}
fun menu(){
    var pictures = mutableListOf<File>()
    var changedPictures = mutableListOf<ByteArray>()
    var path: String = ""
    println("/? - help")
    while(true){
        val input = commandReader()
        when(input[0]){
            "/?" -> help()
            "/path" -> {
                path = input[1]
                Runtime.getRuntime().exec("cmd /c cd directory check\\ && start findImg.bat \"${path}\"")
                File("directory check\\file.txt").forEachLine { pictures.add(File("${path}\\${it}")) }
            }
            "/enc" -> {
                var coder = ImageCoder("enc", input[1])
                var result = coder.code(pictures) //list of coded images and result
                changedPictures = result[0] as MutableList<ByteArray>
                for(i in changedPictures.indices){
                    File(pictures[i].path).writeBytes(changedPictures[i] as ByteArray)
                }
                printResultInfo(result[1] as CodeResult)
            }
            "/dec"-> {
                var decoder = ImageCoder("dec", input[1])
                var result = decoder.code(pictures)
                changedPictures = result[0] as MutableList<ByteArray>
                for(i in changedPictures.indices){
                    File(pictures[i].path).writeBytes(changedPictures[i] as ByteArray)
                }
                printResultInfo(result[1] as CodeResult)
            }
            "/ex" -> return
            else -> {
                println("Wrong command.")
            }
        }
    }
}

fun main(args: Array<String>){
    menu()
}