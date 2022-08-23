package com.uz.androidmvvm.network.services

import com.uz.androidmvvm.model.Note
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by Eldor Turgunov on 22.08.2022.
 * Android mvvm
 * eldorturgunov777@gmail.com
 */
interface ServiceApi  {

    //get
    @GET("posts")
    fun getNotes(): Call<ArrayList<Note>>

    //get one note
    @GET("posts/{id}")
    fun getNote(@Path("id") id: Int): Call<Note>

    //insert
    @POST("posts")
    fun createPost(@Body note: Note): Call<Note>

    //update
    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: Int, @Body note: Note): Call<Note>

    //delete
    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Note>

}