package adil.trackedperson.data

import com.google.gson.annotations.SerializedName

class LocationEvent(@SerializedName("latitude") val latitude:Double, @SerializedName("longitude") val longitude:Double, @SerializedName("object_id") val objectId:Long)