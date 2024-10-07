package com.mymu.app

interface Platform {
    val name: String
    val isApple: Boolean
}

expect fun getPlatform(): Platform