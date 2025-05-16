package com.example.test_task

import androidx.core.splashscreen.SplashScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenController(private val splashScreen: SplashScreen) {

    private var keepSplash = true

    fun holdSplashScreen(scope: CoroutineScope, timeDelay: Long) {
        splashScreen.setKeepOnScreenCondition{ keepSplash }

        scope.launch{
            delay(timeDelay)
            keepSplash = false
        }
    }
}