package com.aneke.peter.whatsapplinker

import androidx.core.text.isDigitsOnly
import java.util.regex.Pattern

const val REG = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
var PATTERN = Pattern.compile(REG)

//TODO: Improve this function with regex for more types of numbers
fun String?.isValidPhoneNumber() : Boolean {
    return if (this == null || !this.isDigitsOnly()) {
        false
    } else {
        this.length >= 11 //&& PATTERN.matcher(this).find()
    }
}