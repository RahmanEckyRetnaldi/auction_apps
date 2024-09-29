package com.rer.core.utils

class NativeLib {
    external fun getDevBaseUrl() : String
    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

}