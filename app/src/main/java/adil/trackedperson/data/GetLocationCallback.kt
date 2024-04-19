package adil.trackedperson.data

interface GetLocationCallback {
    fun onResponse(latitude:Double, longitude:Double)
    fun onFailure(msg:String?)
}