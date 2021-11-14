package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.AdapterResponse
import com.example.myapplication.DialogPersonalized
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.databinding.FragmentPaymentBinding
import com.example.myapplication.model.ResponseModel
import com.example.myapplication.proxi.RetrofitController
import com.example.myapplication.viewModel.ViewModelClass
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ListFragment: Fragment()  {
    private val viewModel: ViewModelClass by activityViewModels()
    private val retrofitController: RetrofitController by inject()
    private val dialog: DialogPersonalized by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.context = context
        recycler_response.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            val list = viewModel.dataBase!!.responseModelDAOCreater().getAll()
            recycler_response.adapter = AdapterResponse(this@ListFragment,list as ArrayList<ResponseModel>)
        }
    }

    fun eraseElementAndRefresh(item:ResponseModel,array:ArrayList<ResponseModel>){
        lifecycleScope.launch{
            viewModel.dataBase!!.responseModelDAOCreater().delete(item)
            val list = viewModel.dataBase!!.responseModelDAOCreater().getAll()
            recycler_response.adapter = AdapterResponse(this@ListFragment,list  as ArrayList<ResponseModel>)
        }
    }
}