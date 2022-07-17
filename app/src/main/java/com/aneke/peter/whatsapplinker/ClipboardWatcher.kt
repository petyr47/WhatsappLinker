package com.aneke.peter.whatsapplinker

import android.app.Service
import android.content.ClipboardManager
import android.content.ClipboardManager.OnPrimaryClipChangedListener
import android.content.Intent
import android.os.IBinder
import android.util.Log

/*
* Wanted to add functionality to trigger notifications when number copied
* and enter whatsapp from notification click action
* but clipboard cannot be accessed from android 10
*  so this obsolete...for now
* */

class ClipboardWatcher : Service() {

    lateinit var clipboardManager : ClipboardManager

    private val onPrimaryClipChangedListener = OnPrimaryClipChangedListener {
        val clip = clipboardManager.primaryClip?.getItemAt(0)?.text.toString()
        if (clip.isValidPhoneNumber()){
            makeWhatsappNotification(clip)
        }
        Log.e("on prim", clip)
        makeWhatsappNotification(clip)
    }

    override fun onBind(p0: Intent?): IBinder?  = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.addPrimaryClipChangedListener(onPrimaryClipChangedListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        clipboardManager.removePrimaryClipChangedListener(onPrimaryClipChangedListener)
    }

}