package com.example.myapplication

import com.example.myapplication.di.roomModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class KoinTestDI {
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAllModules() {
        roomModule.verify()
    }
}