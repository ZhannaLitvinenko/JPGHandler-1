package Handler

import java.io.File
import javax.imageio.ImageIO


fun main(args: Array<String>){
//    test
    var image = ImageIO.read(File("image.jpg"))
    val coder = ImageCoder(mode = "enc", password = "55085508")
    image = coder.Code(image)
    val outputfile = File("image.jpg")
    ImageIO.write(image, "jpg", outputfile)
}