package com.namshi.sharukh.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.namshi.sharukh.R
import com.namshi.sharukh.utils.gone
import com.namshi.sharukh.utils.show
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    abstract val screenTitle: String?

    protected val subscriptions = CompositeDisposable()
    val handler = Handler(Looper.getMainLooper())
    protected var activity: BaseActivity? = null


    companion object {
    }

    override fun onAttach(context: Context) {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onAttach")
        super.onAttach(context)
        activity = getActivity() as? BaseActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onStart")
        super.onStart()
    }

    override fun onResume() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onResume")
        super.onResume()
    }


    override fun onPause() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onPause")
        super.onPause()
        handler.removeCallbacksAndMessages(null)
        context?.let { Glide.get(it).clearMemory() }
    }

    override fun onStop() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onDestroyView")
        super.onDestroyView()
        subscriptions.clear()
    }

    override fun onDestroy() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onDestroy")
        subscriptions.dispose()
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onDetach")
        activity = null
        super.onDetach()
    }


    /**
     * returns true if fragment view is created
     */
    val isViewCreated: Boolean
        get() = view != null

    /**
     * previously we are removing the fragment from backstack.
     * But activity onbackpressed provides the same functionality
     * So, we are using activity's onbackpressed
     */
    fun goBack() {
        activity?.onBackPressed()
    }


    /**
     * Returns true if fragment context is valid and its added
     */
    val isContextValid: Boolean
        get() = isAdded && context != null && view != null

    open fun showLoading() {
        handler.post {
            val rootView = view
            var loader: View? = rootView?.findViewById(R.id.common_loader_view)
            if (loader == null) {
                if (rootView is ViewGroup) {
                    loader = LayoutInflater.from(requireContext()).inflate(R.layout.common_loader, rootView, false)
                    rootView.addView(loader)
                    loader.layoutParams.apply {
                        height = ViewGroup.LayoutParams.MATCH_PARENT
                        width = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                } else
                    Timber.e(IllegalStateException("Root layout must be a sub class of ViewGroup"))
            }
            loader?.show()
        }
    }


    open fun hideLoading() {
        handler.post {
            val rootView = view
            val progress = rootView?.findViewById<View>(R.id.common_loader_view)
            progress?.gone()
        }
    }


    internal fun launchActivity(intent: Intent) = activity?.launchActivity(intent)

}
