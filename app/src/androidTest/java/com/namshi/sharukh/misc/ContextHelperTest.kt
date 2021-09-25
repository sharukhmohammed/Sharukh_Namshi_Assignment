package com.namshi.sharukh.misc

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.namshi.sharukh.R
import org.junit.After
import org.junit.Before
import org.junit.Test

class ContextHelperTest {

    lateinit var context: Context

    @Before
    fun before() {
        context = ApplicationProvider.getApplicationContext()
    }

    @After
    fun after() {
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        assertThat(context).isNotNull()
        assertThat(string(R.string.test_string, context)).isEqualTo("Test String")
    }


    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse() {
        assertThat(context).isNotNull()
        assertThat(string(R.string.test_string, context)).isNotEqualTo("Some_thing_else")
    }


}