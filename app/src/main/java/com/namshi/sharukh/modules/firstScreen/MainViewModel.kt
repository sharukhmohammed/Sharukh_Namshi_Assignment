package com.namshi.sharukh.modules.firstScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.network.response.ContentResponse
import com.namshi.sharukh.utils.clearAndAddAll
import com.namshi.sharukh.utils.plusAssign
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class MainViewModel : ViewModel() {


    val content: MutableLiveData<ContentResponse?> = MutableLiveData()


    private val model: MainModel = MainModel()
    private val subscriptions = CompositeDisposable()

    init {
        fetchInitialData()
    }


    private fun fetchInitialData() {
        subscriptions += model.getMainScreenContent()
            .map { content.postValue(it); it }
            .map { it.content.filter { widget -> widget.type == NamshiWidget.Type.carousel } }
            .flatMap { Observable.fromIterable(it) }
            .flatMapCompletable { fetchCarouselData(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::postComplete, Timber::e)
    }

    private fun postComplete() {
        Timber.i("Complete => All ok")
    }

    private fun fetchCarouselData(widget: NamshiWidget): Completable {
        Timber.w("Calling => ${widget.url}")
        return model.getCarouselData(widget)
            .flatMapCompletable { response ->
                Timber.w("Got response => ${widget.url}")
                val data = content.value?.content?.find { it.url == widget.url }
                data?.images?.clearAndAddAll(response.images)
                content.postValue(content.value)
                Completable.complete()
            }
            .subscribeOn(Schedulers.io())
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}