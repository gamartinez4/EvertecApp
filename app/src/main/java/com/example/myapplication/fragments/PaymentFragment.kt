package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.myapplication.AdapterResponse
import com.example.myapplication.DialogPersonalized
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.example.myapplication.databinding.FragmentPaymentBinding
import com.example.myapplication.model.ResponseModel
import com.example.myapplication.proxi.RetrofitController
import com.example.myapplication.proxi.RetrofitStrings
import com.example.myapplication.utils.animation.Animations
import com.example.myapplication.utils.credentials.CredentialsProcessing
import com.example.myapplication.viewModel.StateRequest
import com.example.myapplication.viewModel.ViewModelClass
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import org.koin.android.ext.android.inject

class PaymentFragment: Fragment()  {
    private val viewModel: ViewModelClass by activityViewModels()
    private val retrofitController: RetrofitController by inject()
    private val retrofitStrings: RetrofitStrings by inject()
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
        viewModel.paymentFragment = this
    }


    fun refreshRequest(view: View, id:Int) {
        viewModel.stateRequest.value = StateRequest.CHARGING
        val credentials = CredentialsProcessing.credentialsProcessing()
        val json = "{\"auth\": {\"login\": \"${credentials.login}\"," +
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
                "      \"total\": ${
                    viewModel.price.value?.replace("$", "")?.replace(".", "") ?: "0"
                }" +
                "    }" +
                "  }," +
                "  \"instrument\": {" +
                "    \"card\": {" +
                "      \"number\": \"${viewModel.creditNumber.value ?: "1"}\"," +
                "      \"expiration\": \"12/20\"," +
                "      \"cvv\": \"123\"," +
                "      \"installments\": 2" +
                "    }" +
                "  }," +
                "  \"ipAddress\": \"190.85.90.130\"," +
                "  \"userAgent\": \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36\"" +
                "}"
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json"), json)
        retrofitController.executeAPI(retrofitStrings.aprovedURLString(), body, {
            viewModel.stateRequest.value = StateRequest.NO_CHARGING
            if (it.code().toString() == "200") {
                Log.e("respuesta", it.body().toString())
                val responseModel = ResponseModel()
                responseModel.state =
                    ((JSONObject(it.body().toString()))["status"] as JSONObject).getString("status")
                responseModel.buyDate =
                    ((JSONObject(it.body().toString()))["status"] as JSONObject).getString("date")
                responseModel.names = viewModel.names.value.toString()
                responseModel.surnames = viewModel.surnames.value.toString()
                responseModel.creditNumber = viewModel.creditNumber.value.toString()
                responseModel.price = viewModel.price.value.toString()
                viewModel.details.value = responseModel

                lifecycleScope.launch {
                    viewModel.dataBase!!.responseModelDAOCreater()
                        .insertToList(viewModel.details.value!!)
                    Navigation.findNavController(view)
                        .navigate(id, null, Animations.options_slide_in)
                }
            } else {
                dialog.contenido = "Respuesta del servidor invalida."
                dialog.showDialog()
            }

        }, {
            viewModel.stateRequest.value = StateRequest.NO_CHARGING
            dialog.contenido =
                "La información no pudo ser recibida satisfactoriamente, revise su conexion a internet"
            dialog.showDialog()
        })
    }

}