package com.example.kartotekaapp_v20.network

import com.example.kartotekaapp_v20.data.Company
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://statuspro.by/api/v1/"

interface CompanyApi {
    @GET("egr/{taxId}")
    suspend fun getProductById(@Path("taxId") taxId: Int): Company
}