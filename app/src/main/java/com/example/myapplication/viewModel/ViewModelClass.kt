package com.example.myapplication.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.fragments.PaymentFragment
import com.example.myapplication.model.ResponseModel
import com.example.myapplication.room.DataBase
import com.example.myapplication.utils.animation.Animations

class ViewModelClass : ViewModel(){

    var paymentFragment:PaymentFragment? = null

    val details: MutableLiveData<ResponseModel?> = MutableLiveData(null)

    val user = MutableLiveData("")
    val password = MutableLiveData("")

    val names = MutableLiveData("")
    val surnames = MutableLiveData("")
    val creditNumber = MutableLiveData("")
    val email = MutableLiveData("")
    val cellphone = MutableLiveData("")
    val price = MutableLiveData("")
    val verificationCode = MutableLiveData("")


    val stateRequest = MutableLiveData(StateRequest.NO_CHARGING)

    var dataBase: DataBase? = null

    val selectedValue =  MutableLiveData<ResponseModel?>(null)


    fun screenController(view: View, idField: Int){
        var futureScreen = 0
        when(view.id){
            R.id.login_button -> futureScreen = R.id.home_fragment
            R.id.payment_button -> {
                paymentFragment!!.refreshRequest(view,R.id.details_fragment)
                return
            }
            R.id.new_purchase_button -> futureScreen = R.id.payment_fragment
            R.id.list_of_purchases_button -> futureScreen = R.id.list_fragment
        }
        Navigation.findNavController(view).navigate(futureScreen,null, Animations.options_slide_in)
    }

    fun fieldTextChanged(charSequence: CharSequence, idField: Int){
        when (idField) {
            R.id.user -> user.value = charSequence.toString()
            R.id.password -> password.value = charSequence.toString()

            R.id.names -> names.value = charSequence.toString()
            R.id.surnames -> surnames.value = charSequence.toString()
            R.id.credit_number -> creditNumber.value = charSequence.toString()
            R.id.email -> email.value =  charSequence.toString()
            R.id.cellphone -> cellphone.value =  charSequence.toString()
            R.id.price -> price.value = charSequence.toString()
            R.id.verification_code -> verificationCode.value = charSequence.toString()
        }

    }
}