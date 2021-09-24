package com.namshi.sharukh.base

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.namshi.sharukh.R
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {

    protected val subscriptions = CompositeDisposable()
    protected val handler = Handler(Looper.getMainLooper())


    companion object {
    }


    override fun onPause() {
        super.onPause()
        Glide.get(this).clearMemory()
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }


    /**
     * Adds fragment on current stack
     * There are bunch of options which are self explanatory
     */
    fun addFragment(
        fragment: Fragment,
        replace: Boolean = false, addToBackStack: Boolean = true,
        tag: String = fragment.javaClass.simpleName,
        fragmentManager: FragmentManager = supportFragmentManager,
        containerId: Int = R.id.container,
    ) {

        val transaction = fragmentManager.beginTransaction()



        if (replace) {
            transaction.replace(containerId, fragment, tag)
        } else {
            val oldFragment = getCurrentTopFragment(fragmentManager)
            oldFragment?.let { transaction.setMaxLifecycle(it, Lifecycle.State.STARTED) }
            //set maxLifecycle of CurrentTopFragment to STARTED, when fragment is being added only,
            //No need to setMaxLifecycle when fragment is being replaced
            transaction.add(containerId, fragment, tag)
        }

        if (addToBackStack)
            transaction.addToBackStack(tag)

        //this does not crash in case activity was not in correct state
        transaction.commitAllowingStateLoss()
    }


    /**
     * Launch new activity with transition and stuff
     */
    fun launchActivity(intent: Intent, finishSelf: Boolean = false) {

        if (finishSelf) finish()
        startActivity(intent)
    }


    /**
     * Shows snack from root layout of activity if view is null or else from the parent view
     */
    fun showSnack(message: String, view: View? = null) {
        //TODO: Implement snack
    }

    /**
     *Overloaded above function to include string()
     * */
    fun showSnack(@StringRes string: Int, view: View? = null) = showSnack(string(string), view)


    private fun getCurrentTopFragment(fragmentManager: FragmentManager): Fragment? {
        try {
            val stackCount = fragmentManager.backStackEntryCount
            if (stackCount > 0) {
                val backEntry = fragmentManager.getBackStackEntryAt(stackCount - 1)
                return fragmentManager.findFragmentByTag(backEntry.name)
            } else {
                val fragments = fragmentManager.fragments
                if (fragments.size > 0) {
                    for (f in fragments) {
                        if (f != null && !f.isHidden)
                            return f
                    }
                }
            }
            return null
        } catch (e: KotlinNullPointerException) {
            return null
        }
    }

}