package com.uz.androidmvvm.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.uz.androidmvvm.R
import com.uz.androidmvvm.model.Note
import com.uz.androidmvvm.presenter.CreatePresenter
import com.uz.androidmvvm.view.CreateView
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity(), CreateView {
    var note: Note? = null
    private var idExtra = 0
    var createPresenter: CreatePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        initViews()
    }

    private fun initViews() {
        val createData = findViewById<Button>(R.id.bt_post)
        createData.setOnClickListener {
            val note =
                Note(title_edit_text!!.text.toString(), body_edit_text!!.text.toString(), 0, 0)
            if (idExtra == 0) {
                onCreateSucces(note)
            }
        }
        if (intent.hasExtra("IdExtra")) {
            idExtra = intent.extras!!.getInt("IdExtra")
            createData.text = "Update"
        }
    }

    override fun onUpdateSucces(note: Note) {
        if (idExtra != 0) {
            createPresenter!!.updateNote(idExtra, note)
        }

        startActivity(Intent(this@CreateActivity, MainActivity::class.java))
        finish()
    }

    override fun onUpdateFailure(error: String) {

    }

    override fun onCreateSucces(note: Note) {
        startActivity(Intent(this@CreateActivity, MainActivity::class.java))
        finish()
        Toast.makeText(this@CreateActivity, "Successful Create", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onCreateFailure(error: String) {

    }
}