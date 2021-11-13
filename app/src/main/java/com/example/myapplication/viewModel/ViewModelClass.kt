package com.example.myapplication.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.model.ResponseModel
import com.example.myapplication.room.DataBase
import com.example.myapplication.utils.animation.Animations

class ViewModelClass : ViewModel(){

    val user = MutableLiveData("")
    val password = MutableLiveData("")

    val listGet = ArrayList<ResponseModel>()

    var dataBase: DataBase? = null

    val selectedValue =  MutableLiveData<ResponseModel?>(null)

    fun screenController(view: View, idField: Int){
        var futureScreen = 0
        when(idField){
            R.id.login_button -> futureScreen = R.id.home_fragment
            R.id.payment_button -> futureScreen = R.id.details_fragment
            R.id.new_purchase_button -> futureScreen = R.id.payment_fragment
            R.id.list_of_purchases_button -> futureScreen = R.id.list_fragment
        }
        Navigation.findNavController(view).navigate(futureScreen,null, Animations.options_slide_in)
    }

    fun fieldTextChanged(charSequence: CharSequence, idField: Int){
        when(idField) {
            R.id.user -> user.value = charSequence.toString()
            R.id.password -> password.value = charSequence.toString()
        }
    }
}