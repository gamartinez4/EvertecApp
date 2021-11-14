package com.example.myapplication


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBinding
import com.example.myapplication.fragments.ListFragment
import com.example.myapplication.model.ResponseModel
import com.example.myapplication.utils.animation.Animations
import com.example.myapplication.viewModel.ViewModelClass
import kotlinx.android.synthetic.main.item.view.*


class AdapterResponse(
    private var listFragment: ListFragment,
    private var array:ArrayList<ResponseModel>
    ) : RecyclerView.Adapter<AdapterResponse.ViewHolderList>() {

    inner class ViewHolderList(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.erase.setOnClickListener{
                listFragment.eraseElementAndRefresh(binding.item!!,array)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderList{
        return ViewHolderList(ItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
        holder.binding.item = array[position]
        }

    override fun getItemCount() = array.size
}


