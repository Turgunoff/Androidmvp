package com.uz.androidmvvm.view

import com.uz.androidmvvm.model.Note


/**
 * Created by Eldor Turgunov on 23.08.2022.
 * Android mvp
 * eldorturgunov777@gmail.com
 */
interface CreateView {

    fun onUpdateSucces(note: Note)
    fun onUpdateFailure(error: String)

    fun onCreateSucces(note: Note)
    fun onCreateFailure(error: String)
}