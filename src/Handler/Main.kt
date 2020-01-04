package Handler

fun main(args: Array<String>) {
//    val file = File("photo.jpg");
//    var fileContent = Files.readAllBytes(file.toPath())
////    create cipher algo and pass
//    var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
//    var password = "1234567890123456".toByteArray()
//    val ivspec = IvParameterSpec(password)
//    val algorithm = "AES"
////    create key and cipher
//    var key = SecretKeySpec(password, algorithm)
//    cipher.init(Cipher.ENCRYPT_MODE, key, ivspec)
////    encrypt
//    val encryptedBase64 = Base64.getEncoder().encode(cipher.doFinal(fileContent))
////    save
//    File("photo.jpg").writeBytes(encryptedBase64)
////    read
//    val encryptFromFile = File("photo.jpg").readBytes()
////    decrypt
//    cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
//    password = "1234567891123456".toByteArray()
//    key = SecretKeySpec(password, algorithm)
//    cipher.init(Cipher.DECRYPT_MODE, key, ivspec)
//    val decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptFromFile))
////    convert to image
//    File("result.jpg").writeBytes(decrypted)
}