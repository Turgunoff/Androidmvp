package com.uz.androidmvvm.presenter

import com.uz.androidmvvm.model.Note


/**
 * Created by Eldor Turgunov on 23.08.2022.
 * Android mvp
 * eldorturgunov777@gmail.com
 */
interface CreatePresenterImpl {
    fun updateNote(idExtra: Int,note: Note)
    fun createNote(note: Note)
}