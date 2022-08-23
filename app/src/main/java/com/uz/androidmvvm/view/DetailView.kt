package com.uz.androidmvvm.view

import com.uz.androidmvvm.model.Note


/**
 * Created by Eldor Turgunov on 23.08.2022.
 * Android mvp
 * eldorturgunov777@gmail.com
 */
interface DetailView {

    fun onPostGetSucces(note: Note)
    fun onPostGetFailure(error: String)
}