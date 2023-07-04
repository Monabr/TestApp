package com.example.testapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class StateHolder {
    val currentInfoForShow = mutableStateOf<String?>(null)
}

@Composable
fun rememberStateHolder() = remember {
    StateHolder()
}