package hien.android.uthus.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<_ViewDataBinding : ViewDataBinding> : AppCompatActivity() {

    var binding: _ViewDataBinding? = null

    abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding?.lifecycleOwner = this
        onViewBindingCreated()
    }

    abstract fun onViewBindingCreated()

}