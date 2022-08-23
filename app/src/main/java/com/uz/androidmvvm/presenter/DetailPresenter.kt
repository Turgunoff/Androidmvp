package com.uz.androidmvvm.presenter

import com.uz.androidmvvm.model.Note
import com.uz.androidmvvm.network.RetrofitInstance
import com.uz.androidmvvm.network.services.ServiceApi
import com.uz.androidmvvm.view.DetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Eldor Turgunov on 23.08.2022.
 * Android mvp
 * eldorturgunov777@gmail.com
 */
class DetailPresenter(var detailView: DetailView) : DetailPresenterImpl {
    var note: Note? = null

    override fun apiPostGetList(idExtra: Int) {
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.getNote(idExtra)
        call.enqueue(object : Callback<Note?> {
            override fun onResponse(call: Call<Note?>, response: Response<Note?>) {
                note = response.body()
                detailView.onPostGetSucces(response.body()!!)
            }

            override fun onFailure(call: Call<Note?>, t: Throwable) {
                detailView.onPostGetFailure(t.toString())
            }
        })
    }
}