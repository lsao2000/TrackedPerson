package adil.trackedperson.data
import retrofit2.http.*
import retrofit2.Call
interface ApiDatabase {
    @FormUrlEncoded
    @POST("addObject.php")
    fun addObject(@Field("user_token") user_token:String):Call<ResponseObject>
    @FormUrlEncoded
    @POST("addLocation.php")
    fun addEvent(@Field("object_id") objectId:Long, @Field("latitude") latitude:Double, @Field("longitude") longitude:Double):Call<ResponseEvent>
}
