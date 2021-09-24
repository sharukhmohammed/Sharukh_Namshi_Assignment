package com.namshi.sharukh.modules.firstScreen

import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.network.response.Carousel
import com.namshi.sharukh.network.response.HomeContent
import com.namshi.sharukh.utils.clearAndAddAll
import io.reactivex.rxjava3.core.Observable

class MainModel {

    private val repo = MainRepo()

    private var homeContent: HomeContent = HomeContent()

    fun getMainScreenContent(): Observable<HomeContent> {
        return Observable.create { emitter ->
            repo.getMainScreenContent()
                .map {
                    homeContent = it
                    if (emitter.isDisposed.not())
                        emitter.onNext(it)
                    it
                }
                .flatMap { Observable.fromIterable(it.content.filter { it.type == NamshiWidget.Type.carousel }) }
                .flatMap { repo.getCarouselData(it) }
                .map { widget ->
                    val data = homeContent.content.find { it.url == widget.url }
                    data?.images?.clearAndAddAll(widget.images)
                    if (emitter.isDisposed.not())
                        emitter.onNext(homeContent)
                }
                .subscribe({
                    if (emitter.isDisposed.not()) emitter.onComplete()
                }, emitter::onError)
        }

    }

    fun getProductList(): Observable<Carousel> {
        return repo.getProductList()
    }

}