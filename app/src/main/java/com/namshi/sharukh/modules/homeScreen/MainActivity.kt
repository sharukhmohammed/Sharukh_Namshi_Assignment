package com.namshi.sharukh.modules.homeScreen

import android.os.Bundle
import com.namshi.sharukh.R
import com.namshi.sharukh.base.BaseActivity
import com.namshi.sharukh.databinding.ActivityFirstBinding
import com.namshi.sharukh.misc.string

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }


    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = string(R.string.app_name)
        addFragment(MainFragment.newInstance(), replace = true, addToBackStack = false)
    }
}