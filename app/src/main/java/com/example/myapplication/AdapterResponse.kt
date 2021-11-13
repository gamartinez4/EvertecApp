package com.example.myapplication


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBinding
import com.example.myapplication.model.ResponseModel
import com.example.myapplication.utils.animation.Animations
import com.example.myapplication.viewModel.ViewModelClass


class AdapterResponse(
    private var viewModel: ViewModelClass,
    private var array:ArrayList<ResponseModel>
    ) : RecyclerView.Adapter<AdapterResponse.ViewHolderList>() {

    inner class ViewHolderList(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.details_fragment,null, Animations.options_slide_in)
                viewModel.selectedValue.value = binding.response
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderList{
        return ViewHolderList(ItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
        holder.binding.response = array[position]
        }

    override fun getItemCount() = array.size
}


