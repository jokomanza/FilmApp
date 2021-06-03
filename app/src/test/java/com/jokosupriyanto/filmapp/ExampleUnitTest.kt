package com.jokosupriyanto.filmapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.jokosupriyanto.filmapp.data.model.Show
import com.jokosupriyanto.filmapp.ui.main.ViewModel
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var observe: LiveData<Show?>

    @get:Rule
     var rule: TestRule = InstantTaskExecutorRule()
     val viewModel = ViewModel()

    @Test
    fun addition_isCorrect() {
        // observe = viewModel.getDetail()
        viewModel.fetchDetails()
        viewModel.getDetail().observeForever(Observer {
            println("nilai it adalah $it")
            assertNull(it)
        })
        Thread.sleep(10000)
        println(viewModel.getDetail().value)
        println(viewModel.error)
    }
}