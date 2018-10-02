package com.mxr

interface ValueProvider {
    fun getValue(variableName: String): Double
}