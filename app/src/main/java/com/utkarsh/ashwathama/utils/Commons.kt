import android.util.Log
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T> handleApiResponse(
    request: suspend () -> Response<T>
): IResult<T?> {
    return try {
        println("Thread ${Thread.currentThread().name}")
        var response: Response<T>
        try {
            response = request.invoke()
        } catch (throwable: Throwable) {
            return when (throwable) {
                is UnknownHostException -> IResult.error(
                    "Oops! Something went Wrong.\\nPlease try again.",
                    null
                )
                is IOException -> IResult.error(throwable.message!!, null)
                is HttpException -> IResult.error(
                    throwable.response()?.errorBody()!!.string(),
                    null
                )
                is IllegalStateException -> IResult.error(
                    "appContext.getString(R.string.error_failed_to_fetch)",
                    null
                )
                else -> {
                    IResult.error("Oops! Something went Wrong.\\nPlease try again.", null)
                }
            }
        }
        if (response.isSuccessful) {
            return when (response.code()) {
                200 -> {
                    val gson = Gson()
                    gson.toJson(response.body())
                    val apiResponseJsonString = gson.toJson(response.body())
                   //    val apiResponseJsonObject = JSONArray(apiResponseJsonString)
                    //   Log.e("apiResponseJsonObject", "!!!!$apiResponseJsonObject")

                    IResult.success(response.body())
                }

                else -> {
                    Log.e("error", "!!!!oinginnnnn")
                    IResult.error(response.errorBody()!!.string(), null)
                }
            }

        }
        return when (response.code()) {
            400 -> {
                Log.e("error", "responseoopsElsePart")
                IResult.error(response.errorBody()!!.string(), null)
            }
            else -> {
                Log.e("error", "oopsElsePart")
                IResult.error("Oops! Something went Wrong.\\nPlease try again.", null)
            }
        }

    } catch (e: Throwable) {
        Log.d("error", "Something Went Wrong: $e")
        IResult.error("Oops! Something went Wrong.\\nPlease try again.", null)
    }


}