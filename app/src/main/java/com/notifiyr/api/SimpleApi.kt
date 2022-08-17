package com.notifiyr.api

import com.notifiyr.models.Phone
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleApi {
//    @GET("appscript.php")
//    suspend fun phonehome(): Phone
    @POST("posttest3.php")
    suspend fun postit(@Body post: Array<String>): Phone
}