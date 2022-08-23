package com.uz.androidmvvm.presenter

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.uz.androidmvvm.activity.MainActivity
import com.uz.androidmvvm.model.Note
import com.uz.androidmvvm.network.RetrofitInstance
import com.uz.androidmvvm.network.services.ServiceApi
import com.uz.androidmvvm.view.CreateView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Eldor Turgunov on 23.08.2022.
 * Android mvp
 * eldorturgunov777@gmail.com
 */
class CreatePresenter(var createView: CreateView) : CreatePresenterImpl {

    override fun updateNote(idExtra: Int, note: Note) {
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.updatePost(idExtra, note)
        call.enqueue(object : Callback<Note?> {
            override fun onResponse(call: Call<Note?>, response: Response<Note?>) {
                createView.onUpdateSucces(response.body()!!)
            }

            override fun onFailure(call: Call<Note?>, t: Throwable) {
                createView.onUpdateFailure(t.toString())
            }
        })
    }

    override fun createNote(note: Note) {
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.createPost(note)
        call.enqueue(object : Callback<Note?> {
            override fun onResponse(call: Call<Note?>, response: Response<Note?>) {
                createView.onCreateSucces(response.body()!!)
            }

            override fun onFailure(call: Call<Note?>, t: Throwable) {
                createView.onCreateFailure(t.toString())
            }
        })
    }

}