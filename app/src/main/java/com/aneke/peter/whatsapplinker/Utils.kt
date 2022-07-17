package com.aneke.peter.whatsapplinker

fun makeNigerianWhatsappUrl(number : String) : String {
    val sb = StringBuilder(number)
    val phone = if (number.take(4).contains("234")) {
        number
    } else {
        if (number.first() == '0')  sb.deleteAt(0)
        sb.insert(0, "234")
        sb.toString()
    }
    return "https://wa.me/$phone"
}