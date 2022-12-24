package hien.android.uthus.ui.activity

import hien.android.uthus.R
import hien.android.uthus.core.base.BaseBindingActivity
import hien.android.uthus.databinding.ActivityMainBinding
import hien.android.uthus.ui.adapter.FoodAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    private val mViewModel by viewModel<MainViewModel>()
    private var mAdapter: FoodAdapter? = null

    override fun onViewBindingCreated() {
        setUpUI()
        observeViewModel()
    }

    private fun setUpUI() = binding?.run {
        mAdapter = FoodAdapter(mViewModel)
        rvFood.setHasFixedSize(true)
        rvFood.adapter = mAdapter

        btSave.setOnClickListener {
            mViewModel.save()
        }
    }

    private fun observeViewModel() {
        mViewModel.getFoodRecords()
        mViewModel.foodRecordsLiveData.observe(this) {
            mAdapter?.submit(it)
        }
        mViewModel.expiredTimeLiveData.observe(this) {
            mAdapter?.notifyDataSetChanged()
        }
        mViewModel.countDownTime()
    }

}