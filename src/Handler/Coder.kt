package Handler

import java.awt.image.BufferedImage

interface Coder {
    open fun code(image: BufferedImage) : Array<Any>
    open fun code(listOfImages: List<BufferedImage>) : Array<Any>
}