package com.example.myapplication.utils.animation

import android.annotation.SuppressLint
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.example.myapplication.R
import com.example.myapplication.viewModel.StateRequest
import java.text.DecimalFormat

@BindingAdapter("android:chargeImage")
fun imageReponse(imageView: ImageView, response:String?){
    response?.let{
        if(imageView.id == R.id.response_image){
            when(response){
                "REJECTED" -> imageView.setBackgroundResource(R.drawable.ic_error)
                "APPROVED" -> imageView.setBackgroundResource(R.drawable.ic_success)
                "PENDING" -> imageView.setBackgroundResource(R.drawable.ic_waiting)
            }
        }
        if(imageView.id == R.id.list_detail_image){
            when(response){
                "REJECTED" -> imageView.setBackgroundResource(R.drawable.ic_circle_red)
                "APPROVED" -> imageView.setBackgroundResource(R.drawable.ic_circle_green)
                "PENDING" -> imageView.setBackgroundResource(R.drawable.ic_circle_yellow)
            }
        }
    }
}

@SuppressLint("RestrictedApi")
@BindingAdapter("android:text")
fun setText(textView: TextView, oldText: String?, text: String?) {
    if(textView.id == R.id.price) formatTextCurrency(textView,oldText?:"",text?:"")
    else if(textView.id == R.id.text2){
        var responseText = ""
        when(text){
            "REJECTED" -> responseText = "RECHAZADO"
            "APPROVED" -> responseText = "APROVADO"
            "PENDING" -> responseText = "PENDIENTE"
        }
        textView.text = responseText
    }
    else if(
        textView.id != R.id.password ||
        textView.id != R.id.user ||
        textView.id != R.id.verification_code
            )TextViewBindingAdapter.setText(textView, text)

}


@SuppressLint("RestrictedApi")
@BindingAdapter("android:visibility")
fun setVisibility(lottieAnimationView: LottieAnimationView, stateRequest: StateRequest?) {

    when (stateRequest) {
        StateRequest.CHARGING -> {
            lottieAnimationView.visibility = View.VISIBLE
            lottieAnimationView.setAnimation(R.raw.listen)
            lottieAnimationView.playAnimation()
        }
        StateRequest.NO_CHARGING -> {
            lottieAnimationView.visibility = View.GONE
            lottieAnimationView.clearAnimation()
            lottieAnimationView.setImageResource(0)
        }

    }
}


@SuppressLint("RestrictedApi")
fun formatTextCurrency(view: TextView, oldText: String?, text: String?) {
    var goodFormat = true
    val disc = text!!.reversed()
    var i = 0
    while (goodFormat && i < disc.length - 1) {
        if (i % 4 == 3 && disc[i] != '.') goodFormat = false
        i++
    }

    if (goodFormat && oldText!!.replace(".", "") == text.replace(".", "")) return

    var oldSelector = 0
    var difLenghtValue: Int
    var value = text

    if (view is EditText) {
        val difLenghtNewToOld = text.length - oldText!!.length
        oldSelector = view.selectionStart - difLenghtNewToOld
        try {
            if (difLenghtNewToOld == -1 && oldText[view.selectionStart] == '.') //permite borrar numero a la izquierda cuando se borra un punto
                value =
                    StringBuilder(text).also { it.deleteCharAt(view.selectionStart - 1) }.toString()
        } catch (e: Exception) {
        }
    }

    value = value!!.replace(".", "").replace("$", "")
    if (value.isEmpty()) {
        TextViewBindingAdapter.setText(view, "")
        return
    }

    try {
        difLenghtValue = value.length
        value = value.toInt().toString()//quita los 0 a la izquierda
        difLenghtValue -= value.length
        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.isGroupingUsed = true
        decimalFormat.groupingSize = 3
        value = "$" + decimalFormat.format(value.toLong()).replace(",", ".")
        TextViewBindingAdapter.setText(view, value)
        if (view is EditText) {
            try {
                view.setSelection(if (difLenghtValue == 0) oldSelector + view.text.length - oldText!!.length else 1)
            }// ubica el selector en la posicion 1 si se quitaron 0 a la izquierda
            catch (e: Exception) {
                view.setSelection(view.text.length)
            }
        }
    } catch (e: Exception) {
        TextViewBindingAdapter.setText(view, oldText)
        if (view is EditText) view.setSelection(1)
    }
}