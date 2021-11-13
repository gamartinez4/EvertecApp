package com.example.myapplication


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface


class DialogPersonalized() {

    var contenido:String = ""
    var context: Context? =  null
    var funcion:()->Unit = {}


    fun showDialog(): Dialog {
        return (AlertDialog.Builder(context))
            .setTitle("ALERTA!")
            .setMessage(contenido)
            .setPositiveButton(
                "OK"
            ) { _: DialogInterface, i: Int ->
                funcion()
                if( funcion != {})funcion = {}
            }.show()
    }
}