package hien.android.uthus.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hien.android.uthus.R
import hien.android.uthus.data.FoodRecord
import hien.android.uthus.databinding.ItemFoodBinding
import hien.android.uthus.ui.activity.MainViewModel

class FoodAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    /**
     * Create DiffUtil
     * */
    private val diffUtil = object : DiffUtil.ItemCallback<FoodRecord>() {
        override fun areItemsTheSame(oldItem: FoodRecord, newItem: FoodRecord): Boolean {
            return oldItem.name.contentEquals(newItem.name)
        }

        override fun areContentsTheSame(oldItem: FoodRecord, newItem: FoodRecord): Boolean {
            return false
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffUtil)

    fun submit(list: List<FoodRecord>) {
        listDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_food,
                parent,
                false
            )
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindData(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    inner class FoodViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: FoodRecord) {
            (binding as? ItemFoodBinding)?.run {
                record = data
                val isChecked = viewModel.hashMapNumberRecord.containsKey(data.name)
                llControllerNumber.isVisible = isChecked
                cbCheckBox.isChecked = isChecked
                cbCheckBox.setOnClickListener {
                    llControllerNumber.isVisible = cbCheckBox.isChecked
                    when (cbCheckBox.isChecked) {
                        true -> viewModel.hashMapNumberRecord[data.name] = 1
                        else -> viewModel.hashMapNumberRecord.remove(data.name)
                    }
                    val number = viewModel.hashMapNumberRecord[data.name] ?: 1
                    updateNumber(number)
                }

                var number = viewModel.hashMapNumberRecord[data.name] ?: 1
                updateNumber(number)
                btPlus.setOnClickListener {
                    number += 1
                    viewModel.hashMapNumberRecord[data.name] = number
                    updateNumber(number)
                }
                btMinus.setOnClickListener {
                    if (number > 1) {
                        number -= 1
                        viewModel.hashMapNumberRecord[data.name] = number
                        updateNumber(number)
                    }
                }
                viewModel.hashMapExpireRecord[data.name]?.let {
                    (it < 1L).let { isExpired ->
                        tvExpire.isVisible = isExpired
                        llControllerNumber.isVisible = !isExpired && viewModel.hashMapNumberRecord.containsKey(data.name)
                        cbCheckBox.isGone = isExpired
                        if (isExpired) {
                            viewModel.hashMapNumberRecord.remove(data.name)
                        }
                    }

                }
            }
        }

        private fun updateNumber(number: Int) = (binding as? ItemFoodBinding)?.run {
            tvNumber.setText(number.toString(), TextView.BufferType.NORMAL)
        }

    }
}

