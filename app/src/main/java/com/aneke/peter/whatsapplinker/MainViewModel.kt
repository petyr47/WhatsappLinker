package com.aneke.peter.whatsapplinker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val phoneNumber = MutableLiveData("")
    var link = ""

}