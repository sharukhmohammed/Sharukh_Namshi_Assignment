package com.namshi.sharukh.utils

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExtensionsKtAndroidTest {

    private lateinit var context: Context
    private lateinit var view: View

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        view = LinearLayout(context).apply { layoutParams = LinearLayout.LayoutParams(200, 300) }
    }


    @Test
    fun showViewIsViewVisible_returnsTrue() {
        view.show()
        assertThat(view.visibility).isEqualTo(View.VISIBLE)
    }

    @Test
    fun showViewIsViewInVisible_returnsFalse() {
        view.show()
        assertThat(view.visibility).isNotEqualTo(View.INVISIBLE)
    }

    @Test
    fun showViewIsViewGone_returnsFalse() {
        view.show()
        assertThat(view.visibility).isNotEqualTo(View.GONE)
    }

    @Test
    fun hideViewIsViewInVisible_returnsTrue() {
        view.hide()
        assertThat(view.visibility).isEqualTo(View.INVISIBLE)
    }

    @Test
    fun hideViewIsViewGone_returnsFalse() {
        view.hide()
        assertThat(view.visibility).isNotEqualTo(View.GONE)
    }

    @Test
    fun hideViewIsViewVisible_returnsTrue() {
        view.hide()
        assertThat(view.visibility).isNotEqualTo(View.VISIBLE)
    }


    @Test
    fun goneViewIsViewInVisible_returnsFalse() {
        view.gone()
        assertThat(view.visibility).isNotEqualTo(View.INVISIBLE)
    }

    @Test
    fun goneViewIsViewGone_returnsTrue() {
        view.gone()
        assertThat(view.visibility).isEqualTo(View.GONE)
    }

    @Test
    fun goneViewIsViewVisible_returnsFalse() {
        view.gone()
        assertThat(view.visibility).isNotEqualTo(View.VISIBLE)
    }

    @Test
    fun showIfTrueIsViewVisible_returnsTrue() {
        val condition = true
        view.showIf(condition)
        assertThat(view.visibility).isEqualTo(View.VISIBLE)
    }

    @Test
    fun showIfFalseIsViewGone_returnsTrue() {
        val condition = false
        view.showIf(condition)
        assertThat(view.visibility).isEqualTo(View.GONE)
    }

    @Test
    fun showIfTrueIsViewGone_returnsFalse() {
        val condition = true
        view.showIf(condition)
        assertThat(view.visibility).isNotEqualTo(View.GONE)
    }

    @Test
    fun showIfFalseIsViewVisible_returnsFalse() {
        val condition = false
        view.showIf(condition)
        assertThat(view.visibility).isNotEqualTo(View.VISIBLE)
    }

    @Test
    fun dpToPx_returnsTrue() {
        val dp = 213
        val px = dp.dpToPx
        val pxResult = 585.75F
        //assertThat(px).isEqualTo(pxResult) //Device dependent
        assertThat(px).isNonZero()
    }

    @Test
    fun dpToPxIsZero_returnsFalse() {
        val dp = 213
        val px = dp.dpToPx
        val pxResult = 585.75F
        //assertThat(px).isEqualTo(pxResult) //Device dependent
        assertThat(px == 0F).isFalse()
    }
}