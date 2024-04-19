package adil.trackedperson.data

import com.google.gson.annotations.SerializedName

class ResponseEvent(@SerializedName("msg") val message:String, @SerializedName("request_code") val requestCode:Int, @SerializedName("event") val event:LocationEvent)
