package com.mymu.app.utils

import platform.UIKit.UIImage


object Icons {
    const val AppleLogo = "Applelogo"
    const val GoogleLogo = "Googlelogo"
}


fun loadImage(named: String): UIImage? {
    val uiImage = UIImage.imageNamed(named)
    if (uiImage == null) {
        println("Image not found: $named")
    }
    return uiImage
}
