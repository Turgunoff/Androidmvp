package com.uz.androidmvvm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.uz.androidmvvm.R
import com.uz.androidmvvm.model.Note
import com.uz.androidmvvm.network.RetrofitInstance
import com.uz.androidmvvm.network.services.ServiceApi
import com.uz.androidmvvm.presenter.DetailPresenter
import com.uz.androidmvvm.view.DetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity(){
    var title: TextView? = null
    var body: TextView? = null
    var note: Note? = null
    private var idExtra = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initViews()
    }

    private fun initViews() {
        title = findViewById(R.id.title)
        body = findViewById(R.id.body)
        if (intent.hasExtra("IdExtra")) {
            idExtra = intent.extras!!.getInt("IdExtra")
            onPostGetSucces(idExtra)
        }

    }

    private fun onPostGetSucces(idExtra: Int) {
        val serviceApi: ServiceApi = RetrofitInstance.getRetrofit().create(ServiceApi::class.java)
        val call: Call<Note> = serviceApi.getNote(idExtra)
        call.enqueue(object : Callback<Note?> {
            override fun onResponse(call: Call<Note?>, response: Response<Note?>) {
                if (response.isSuccessful) {
                    note = response.body()
                    if (note != null) {
                        title?.setText(note!!.title)
                        body?.setText(note!!.body)
                    }
                }
            }

            override fun onFailure(call: Call<Note?>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}