package Handler

import java.io.File

interface Coder {
    fun code(image: File) : Array<Any>
    fun code(listOfImages: List<File>) : Array<Any>
}