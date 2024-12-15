package com.example.nearbyapp.core.network

import com.example.nearbyapp.core.network.KtorHttpClient.httpClientAndroid
import com.example.nearbyapp.data.model.Category
import com.example.nearbyapp.data.model.Coupon
import com.example.nearbyapp.data.model.Market
import com.example.nearbyapp.data.model.MarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object RemoteDataSource {
    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
    private const val LOCAL_HOST_PHYSICAL_BASE_URL = "http://PLACEHOLDER_LOCALHOST_PHYSICAL_IP:3333"
    private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL

    suspend fun getCategories(): Result<List<Category>> = try {
        val categories = httpClientAndroid.get("$BASE_URL/categories").body<List<Category>>()

        Result.success(categories)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {
        val markets =
            httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}").body<List<Market>>()

        Result.success(markets)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> = try {
        val marketDetails =
            httpClientAndroid.get("$BASE_URL/markets/${marketId}").body<MarketDetails>()

        Result.success(marketDetails)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun patchCoupon(marketId: String): Result<Coupon> = try {
        val coupon = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}").body<Coupon>()

        Result.success(coupon)
    } catch (e: Exception) {
        Result.failure(e)
    }
}