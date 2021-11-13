package com.example.myapplication.utils.credentials

import android.util.Base64
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

class CredentialsProcessing {

    var nonce = ""
    var seed = ""
    var tranKey = ""
    var login = ""

    companion object {

        fun credentialsProcessing():CredentialsProcessing{
            val nonce = "qwertyuiop"
            val credentials = CredentialsProcessing()
            credentials.login = "6dd490faf9cb87a9862245da41170ff2"
            credentials.nonce = Base64.encodeToString(nonce.toByteArray(),0).replace("\n","")
            credentials.seed = (SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .format(Calendar.getInstance().time)).toString()+"-05:00".replace("\n","")
            credentials.tranKey = Base64.encodeToString(
                MessageDigest.getInstance("SHA-256").digest((nonce+credentials.seed+"024h1IlD").toByteArray()),0
            ).replace("\n","")
            return credentials
        }
    }
}