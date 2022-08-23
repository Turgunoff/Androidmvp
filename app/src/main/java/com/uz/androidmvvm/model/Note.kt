package com.uz.androidmvvm.model


/**
 * Created by Eldor Turgunov on 22.08.2022.
 * Android mvvm
 * eldorturgunov777@gmail.com
 */
data class Note(
    val title: String,
    val body: String,
    var id: Int,
    val userId: Int
)