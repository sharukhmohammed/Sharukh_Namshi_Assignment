package com.namshi.sharukh.modules.homeScreen

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.namshi.sharukh.R
import com.namshi.sharukh.base.BaseActivity
import com.namshi.sharukh.databinding.ActivityFirstBinding
import com.namshi.sharukh.misc.string
import com.namshi.sharukh.modules.about.AboutFragment


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> showAboutPopup()
        }
        return true
    }

    private fun showAboutPopup() {
        AboutFragment
            .newInstance()
            .show(supportFragmentManager, AboutFragment.TAG)
    }
}