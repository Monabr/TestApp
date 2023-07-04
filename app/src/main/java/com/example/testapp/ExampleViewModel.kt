package com.example.testapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ExampleViewModel @Inject constructor() : ViewModel() {

    fun someFunction() = Random.nextBytes(15).toString()
}