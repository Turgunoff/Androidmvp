package com.uz.androidmvvm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uz.androidmvvm.R
import com.uz.androidmvvm.adapter.RetrofitAdapter
import com.uz.androidmvvm.model.Note
import com.uz.androidmvvm.presenter.MainPresenter
import com.uz.androidmvvm.utils.Utils
import com.uz.androidmvvm.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainView {
    var doubleBackToExitPressedOnce = false
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        createData()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun createData() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initViews() {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        mainPresenter = MainPresenter(this)

        mainPresenter.apiPostList()
    }

    private fun refreshAdapter(note: ArrayList<Note>) {
        val adapter = RetrofitAdapter(this, note, this)
        recyclerView!!.adapter = adapter
    }


    override fun onPostListSucces(note: ArrayList<Note>?) {
        progress_bar.visibility = View.VISIBLE
        refreshAdapter(note!!)
        progress_bar!!.visibility = View.GONE
    }

    override fun onPostListFailure(error: String) {
    }

    override fun onPostDeleteSucces(note: Note?) {
        progress_bar!!.visibility = View.VISIBLE
        mainPresenter.apiPostList()
        progress_bar!!.visibility = View.GONE
    }

    override fun onPostDeleteFailure(error: String) {
        TODO("Not yet implemented")
    }
}