package com.namshi.sharukh.utils

import com.google.common.truth.Truth.assertThat
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun listClearAndAddAllHasAllElements_returnsTrue() {
        val resultList = listOf(1, 2, 3, 4, 5, 6)
        val mutableList = mutableListOf(9, 6, 5, 6)
        mutableList.clearAndAddAll(resultList)
        assertThat(mutableList).isEqualTo(resultList)
    }

    @Test
    fun listClearAndAddAllHasNoElements_returnsFalse() {
        val resultList = listOf(1, 2, 3, 4, 5, 6)
        val emptyList = listOf<Int>()
        val mutableList = mutableListOf(9, 6, 5, 6)
        mutableList.clearAndAddAll(resultList)
        assertThat(mutableList).isNotEqualTo(emptyList)
    }

    @Test
    fun listClearAndAddAllHasDifferentElements_returnsFalse() {
        val resultList = listOf(1, 2, 3, 4, 5, 6)
        val anotherList = mutableListOf(9, 6, 5, 6)
        val mutableList = mutableListOf(9, 6, 5, 6)
        mutableList.clearAndAddAll(resultList)
        assertThat(mutableList).isNotEqualTo(anotherList)
    }

    @Test
    fun compositeDisposableOperatorFunAdd_returnsTrue() {
        val subscriptions = CompositeDisposable()
        subscriptions += Disposable.empty()
        assertThat(subscriptions.size()).isEqualTo(1)
    }

    @Test
    fun compositeDisposableOperatorFunAdd_returnsFalse() {
        val subscriptions = CompositeDisposable()
        subscriptions += Disposable.empty()
        assertThat(subscriptions.size()).isNotEqualTo(0)
    }

    @Test
    fun listCheckValidIndex_returnsTrue() {
        val list = listOf(0, 1, 2, 3, 4, 5, 6, 7)
        val validIndex = 4
        val invalidIndex = 73
        assertThat(list.isValidIndex(validIndex)).isTrue()
        assertThat(list.isNotValidIndex(invalidIndex)).isTrue()
    }

    @Test
    fun listCheckInValidIndex_returnsFalse() {
        val list = listOf(0, 1, 2, 3, 4, 5, 6, 7)
        val invalidIndex = 34
        val validIndex = 3
        assertThat(list.isValidIndex(invalidIndex)).isFalse()
        assertThat(list.isNotValidIndex(validIndex)).isFalse()
    }
}