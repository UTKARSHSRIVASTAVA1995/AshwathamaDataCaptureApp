
import android.content.Context
import com.utkarsh.ashwathama.BuildConfig
import com.utkarsh.ashwathama.network.NetworkConnectionInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AshwathamaService {

    private const val baseUrl = "http://ashwathama.dailynewswire.info/api/Ashwathama/"
    //http://ashwathama.dailynewswire.info/api/values

    private fun createOkHttpClient(appContext: Context): OkHttpClient {

        return OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val original = chain.request()
                    val builder = chain.request().newBuilder()
                    builder.method(original.method, original.body)
                    return@Interceptor chain.proceed(builder.build())
                }
            )
            if (BuildConfig.DEBUG) {
                this.addInterceptor(HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
            }
        }.addInterceptor(NetworkConnectionInterceptor(appContext)).build()
    }

    fun createRetrofit(appContext: Context): Retrofit {
        val httpClient = createOkHttpClient(appContext)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}