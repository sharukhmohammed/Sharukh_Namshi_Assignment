package com.namshi.sharukh.modules.firstScreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.namshi.sharukh.base.BaseActivity
import com.namshi.sharukh.databinding.ActivityFirstBinding
import timber.log.Timber

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityFirstBinding

    private val model: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
    }


    private fun initViews() {

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            model.refresh()
        }

        addFragment(MainFragment.newInstance(), replace = true)



    }

    /*override fun onBackPressed() {
        val stackCount = supportFragmentManager.backStackEntryCount
        if (stackCount > 0) {
            supportFragmentManager.popBackStack()
        } else
            super.onBackPressed()
    }*/

}