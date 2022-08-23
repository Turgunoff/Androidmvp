package com.uz.androidmvvm.presenter

import com.uz.androidmvvm.model.Note


/**
 * Created by Eldor Turgunov on 23.08.2022.
 * Android mvp
 * eldorturgunov777@gmail.com
 */
interface MainPresenterImpl {
    fun apiPostList()
    fun apiPostDelete(note: Note)
}