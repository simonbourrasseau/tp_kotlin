package com.example.tp_projet_kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface WSInterface {
    // appel get :
    @GET("madrental/get-vehicules.php")
    fun listCar(): Call<List<RetourWSGet>>


    // appel post :
    //@FormUrlEncoded
    //@POST("chemin/relatif/wspost")
    //fun wsPost(
    //        @Field("param1") param1: String,
    //        @Field("param2") param2: String): Call<RetourWSPost>
    // appel post avec body au format JSON (objet BodyWS à créer !) :

    //@POST("chemin/relatif/wspostbody")
    //fun wsPostBody(@Body body: BodyWS): Call<RetourWSPostBody>

}