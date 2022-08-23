package com.uz.androidmvvm.presenter

import android.annotation.SuppressLint
import com.uz.androidmvvm.model.Note
import com.uz.androidmvvm.network.RetrofitInstance
import com.uz.androidmvvm.network.services.ServiceApi
import com.uz.androidmvvm.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Eldor Turgunov on 23.08.2022.
 * Android mvp
 * eldorturgunov777@gmail.com
 */
class MainPresenter(var mainView: MainView) : MainPresenterImpl {

    override fun apiPostList() {
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<ArrayList<Note>> = serviceApi.getNotes()
        call.enqueue(object : Callback<ArrayList<Note>> {
            override fun onResponse(
                call: Call<ArrayList<Note>>,
                response: Response<ArrayList<Note>>
            ) {
                mainView.onPostListSucces(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<Note>>, t: Throwable) {
                mainView.onPostListFailure(t.toString())
            }
        })
    }

    override fun apiPostDelete(note: Note) {
        val serviceApi: ServiceApi =
            RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.deletePost(note.id)
        call.enqueue(object : Callback<Note> {
            @SuppressLint("NotifyDataSetChanged", "NewApi")
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                mainView.onPostDeleteSucces(response.body())
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                mainView.onPostDeleteFailure(t.toString())
            }
        })
    }
}