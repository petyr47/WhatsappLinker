package com.aneke.peter.whatsapplinker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.loylapapplication.util.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel
    @Before
    fun setupViewModel(){
        viewModel = MainViewModel()
    }

    @Test
    fun typeText_expectSameDataInLiveData(){
        val testData = "2334"
        viewModel.phoneNumber.value = testData
        assertEquals(testData, viewModel.phoneNumber.getOrAwaitValue())
    }
}