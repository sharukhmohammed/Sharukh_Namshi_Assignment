package com.namshi.sharukh.base

import android.view.View
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

/**
 * Base recycler view adapter for easy access to common things like
 * 1. recycler view instance
 * 2. last item implementation
 *
 * Also optional connection to Lifecycle if fragment is provided.
 * Clients get access to clearHolders method which is invoked on lifecycle's onStart and onStop methods.
 */


abstract class BaseAdapter<T : RecyclerView.ViewHolder?>(private var fragment: BaseFragment? = null) : RecyclerView.Adapter<T>(), LifecycleObserver {

    protected var rv: RecyclerView? = null

    companion object {

    }


    init {
        fragment?.viewLifecycleOwner?.lifecycle?.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
        Timber.d("onStart")
        notifyDataSetChanged()

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        Timber.d("onStop")
        rv?.children
            ?.mapIndexed { _, view -> rv?.getChildViewHolder(view) }
            ?.filterNotNull()
            ?.forEach { clearHolder(it as T) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        Timber.d("onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        Timber.d("onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        Timber.d("onDestroy")
        fragment = null
        rv = null
    }

    /**
     * Children can override this method to clear of resources for all view holders available
     * in recycler view at the moment
     * Helpful to release glide resources
     */
    open fun clearHolder(holder: T) {
        Timber.d("clearHolder clearing $holder at position ${holder?.adapterPosition}")
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rv = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        rv = null
    }

    /**
     * Checks if a given position is valid with conditions
     * - is not RecyclerView.NO_POSITION
     * - does not exceed getItemCount
     *
     * This must be overridden and used if itemCount is not equal to attached list size
     * */
    open fun isValidPos(position: Int): Boolean {
        return position != RecyclerView.NO_POSITION && position < itemCount
    }

    internal fun viewHolderForAdapterPosition(position: Int) = rv?.findViewHolderForAdapterPosition(position) as? T

    internal fun viewHolderForLayoutPosition(position: Int) = rv?.findViewHolderForLayoutPosition(position) as? T


}

abstract class BaseViewHolder<T>(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bind(data: T)
}

interface LayoutContainer {
    val containerView: View
}
