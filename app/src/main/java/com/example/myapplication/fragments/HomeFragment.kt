package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.AdapterResponse
import com.example.myapplication.App
import com.example.myapplication.DialogPersonalized
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.ResponseModel
import com.example.myapplication.proxi.RetrofitController
import com.example.myapplication.proxi.RetrofitStrings
import com.example.myapplication.utils.credentials.CredentialsProcessing
import com.example.myapplication.viewModel.ViewModelClass
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import org.koin.android.ext.android.inject
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private val viewModel: ViewModelClass by activityViewModels()
    private val retrofitController: RetrofitController by inject()
    private val retrofitStrings: RetrofitStrings by inject()
    private val dialog: DialogPersonalized by inject()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.context = context
    }
}