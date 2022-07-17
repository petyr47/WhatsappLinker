package com.aneke.peter.whatsapplinker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    val phoneNumber = MutableLiveData("")
    val link = Transformations.map(phoneNumber) {
        if (it.length >= 10 && it.isValidPhoneNumber()){
            makeNigerianWhatsappUrl(it)
        } else {
            ""
        }
    }



}