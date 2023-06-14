package org.d3if0126.asessment.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0126.asessment.model.Rumus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/KadekDavid/rumus-bangundatar-api/rumus-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RumusApiNetwork {
    @GET("static-api.json")
    suspend fun getRumus(): List<Rumus>
}
object RumusApi {
    val service: RumusApiNetwork by lazy {
        retrofit.create(RumusApiNetwork::class.java)
    }
    fun getRumusUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpeg"
    }
}

enum class ApiStatus {LOADING, SUCCES, FAILED}