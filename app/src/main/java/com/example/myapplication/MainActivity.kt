package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.myapplication.room.DataBase
import com.example.myapplication.utils.animation.Animations
import com.example.myapplication.viewModel.ViewModelClass
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var navController : NavController? = null
    private var fragmentId: Int? = null
    private var fragmentView: View? = null
    private val viewModel: ViewModelClass by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment).findNavController()
        navController!!.addOnDestinationChangedListener {_, destination, _ ->
            fragmentId = destination.id
        }
        fragmentView = fragment
        viewModel.dataBase = Room.databaseBuilder(this, DataBase::class.java,"model").build()

    }



    override fun onBackPressed(){
        when(fragmentId){
            R.id.home_fragment ->Navigation.findNavController(fragment).navigate(R.id.login_fragment,null, Animations.options_fade)
            R.id.details_fragment-> Navigation.findNavController(fragment).navigate(R.id.payment_fragment,null, Animations.options_slide_out)
            R.id.payment_fragment-> Navigation.findNavController(fragment).navigate(R.id.home_fragment,null, Animations.options_slide_out)
            R.id.list_fragment-> Navigation.findNavController(fragment).navigate(R.id.home_fragment,null, Animations.options_slide_out)
         }
    }


}