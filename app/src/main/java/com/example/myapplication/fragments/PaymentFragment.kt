package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.DialogPersonalized
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.example.myapplication.databinding.FragmentPaymentBinding
import com.example.myapplication.proxi.RetrofitController
import com.example.myapplication.viewModel.ViewModelClass
import org.koin.android.ext.android.inject

class PaymentFragment: Fragment()  {
    private val viewModel: ViewModelClass by activityViewModels()
    private val retrofitController: RetrofitController by inject()
    private val dialog: DialogPersonalized by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentPaymentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.context = context
    }

    /*
    private fun refreshRequest(){
        val credentials = CredentialsProcessing.credentialsProcessing()
        val json ="{\"auth\": {\"login\": \"${credentials.login}\"," +
                "\"tranKey\": \"${credentials.tranKey}\"," +
                "\"nonce\": \"${credentials.nonce}\"," +
                "\"seed\": \"${credentials.seed}\"" +
                "}," +
                "\"payer\": {" +
                "    \"name\": \"Ms. Nelle Beahan DVM\"," +
                "    \"surname\": \"Spencer\"," +
                "    \"email\": \"gsteuber@kling.com\"," +
                "    \"documentType\": \"CC\"," +
                "    \"document\": \"3154383838\"," +
                "    \"mobile\": \"3006108300\"" +
                "  }," +
                "  \"payment\": {" +
                "    \"reference\": \"TEST_20210526_141005\"," +
                "    \"description\": \"Cum vitae et consequatur quas adipisci ut rem.\"," +
                "    \"amount\": {" +
                "      \"currency\": \"COP\"," +
                "      \"total\": 32030" +
                "    }" +
                "  }," +
                "  \"instrument\": {" +
                "    \"card\": {" +
                "      \"number\": \"36545400000008\"," +
                "      \"expiration\": \"12/20\"," +
                "      \"cvv\": \"123\"," +
                "      \"installments\": 2" +
                "    }" +
                "  }," +
                "  \"ipAddress\": \"190.85.90.130\"," +
                "  \"userAgent\": \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36\""+
                "}"
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json"), json)

        charging.setAnimation(R.raw.listen)
        charging.playAnimation()
        charging.visibility = View.VISIBLE
        retrofitController.executeAPI (retrofitStrings.aprovedURLString(),body,{
            if (it.code().toString() == "200") {
                Log.e("respuesta:",it.body().toString())
            } else {
                dialog.contenido = "Respuesta del servidor invalida."
                dialog.showDialog()
            }
            charging.visibility=View.GONE
            charging.clearAnimation()
        },{
            dialog.contenido = "La información no pudo ser recibida satisfactoriamente, revise su conexion a internet"
            dialog.showDialog()
            charging.visibility=View.GONE
            charging.clearAnimation()
        })
    }



     */



}