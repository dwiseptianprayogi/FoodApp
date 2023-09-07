package com.example.kantine_ktp.network

import com.example.kantine_ktp.model.response.checkout.CheckoutResponse
import com.example.kantine_ktp.model.response.transaction.TransactionResponse
import com.example.kantine_ktp.model.response.Wrapper
import com.example.kantine_ktp.model.response.home.HomeResponse
import com.example.kantine_ktp.model.response.login.LoginResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {

//    @Headers("Content-Type: application/json")

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email:String,
              @Field("password") password:String):Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("logout")
    fun logout(@Field("access_token") access_token:String):Observable<Wrapper<Any>>

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("name") name:String,
                 @Field("email") email:String,
                 @Field("password") password:String,
                 @Field("password_confirmation") password_confirmation:String,
                 @Field("address") address:String,
                 @Field("city") city:String,
                 @Field("houseNumber") houseNumber:String,
                 @Field("phoneNumber") phoneNumber:String) : Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(@Part profileImage:MultipartBody.Part) : Observable<Wrapper<Any>>

    @GET("food")
    fun home() : Observable<Wrapper<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(@Field("food_id") food_id:String,
              @Field("user_id") user_id:String,
              @Field("quantity") quantity:String,
              @Field("total") total:String,
              @Field("status") status:String):Observable<Wrapper<CheckoutResponse>>

    @GET("transaction")
    fun transaction() : Observable<Wrapper<TransactionResponse>>
}