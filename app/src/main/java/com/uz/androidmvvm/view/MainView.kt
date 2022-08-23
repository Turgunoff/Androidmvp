package com.uz.androidmvvm.view

import com.uz.androidmvvm.model.Note
import java.lang.Error


/**
 * Created by Eldor Turgunov on 23.08.2022.
 * Android mvp
 * eldorturgunov777@gmail.com
 */
interface MainView {

    fun onPostListSucces(note: ArrayList<Note>?)
    fun onPostListFailure(error: String)

    fun onPostDeleteSucces(note: Note?)
    fun onPostDeleteFailure(error: String)
}