import com.example.hospitalapp.Api.Api_interface
import com.example.hospitalapp.Shared.SharedPrefrence
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroConnection {


    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalRequest = chain.request()
                    val originalUrl = originalRequest.url
                    val url = originalUrl.newBuilder().build()
                    val requestBuilder = originalRequest.newBuilder().url(url)
                        .addHeader("Accept", "application/json")
                        .addHeader(
                            "Authorization", "Bearer ${SharedPrefrence.getUserToken()}"
                        )
                    val request = requestBuilder.build()
                    val response = chain.proceed(request)
                    response.code //status code
                    return response
                }
            }).build()
        Retrofit.Builder()
            .baseUrl("https://hospital.elhossiny.net/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api by lazy {
        retrofit.create(Api_interface::class.java)
    }
}
