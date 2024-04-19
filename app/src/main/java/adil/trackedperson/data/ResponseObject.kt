package adil.trackedperson.data

import com.google.gson.annotations.SerializedName
class ResponseObject(@SerializedName("msg") val message:String, @SerializedName("request_code") val requestCode:Int, @SerializedName("user_token") val userToken:String?)

