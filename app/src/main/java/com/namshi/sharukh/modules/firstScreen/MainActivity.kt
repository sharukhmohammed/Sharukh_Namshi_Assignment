package com.namshi.sharukh.modules.firstScreen

import android.os.Bundle
import androidx.activity.viewModels
import com.namshi.sharukh.base.BaseActivity
import com.namshi.sharukh.databinding.ActivityFirstBinding
import com.namshi.sharukh.utils.gone

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
        supportActionBar?.title = "Sharukh x Namshi"

        binding.fab.setOnClickListener { view ->
            model.refreshMainScreen()
        }

        binding.fab.gone()

        addFragment(MainFragment.newInstance(), replace = true, addToBackStack = false)

    }

    /*override fun onBackPressed() {
        val stackCount = supportFragmentManager.backStackEntryCount
        if (stackCount > 0) {
            supportFragmentManager.popBackStack()
        } else
            super.onBackPressed()
    }*/

}