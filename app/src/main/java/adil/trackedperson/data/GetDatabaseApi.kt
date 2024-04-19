package adil.trackedperson.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetDatabaseApi {
   companion object{
       private final val BASE_URL = "http://192.168.0.68/trackerLocation/"
    private var apiDatabase:Retrofit?= null
    fun getRetrofit():Retrofit{
        if(apiDatabase == null){
            apiDatabase = Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
        }
    return apiDatabase!!
    }
   }
}
