package com.example.trackedperson.viewmodelconnector

import adil.trackedperson.data.GetLocationCallback
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GetLocation(val context:Context) {
    lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    private val requestCode = 2
    fun getLocation(latitude:TextView, longitude:TextView, callback: GetLocationCallback){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        while (checkPersmissions(latitude, longitude, callback) == null){
           checkPersmissions(latitude, longitude, callback)
        }

    }
    fun checkPersmissions(latitude: TextView, longitude: TextView, callback:GetLocationCallback):String?{
        if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION),
                requestCode
                )
            callback.onFailure(null)
            return  null
        }else{
           var value:String? = "work"
            val location = fusedLocationProviderClient.lastLocation
            location.addOnSuccessListener { locationValue ->
                if(locationValue == null){
                    value = null
                    callback.onFailure(null)
                }else{
                    try {
                        val latitudeText =  "latitude: ${locationValue.latitude}"
                        val longitudeText = "longitude : ${locationValue.longitude}"
                        latitude.text = latitudeText
                        longitude.text = longitudeText
                        callback.onResponse(locationValue.latitude, locationValue.longitude)
                    }catch (ex:Exception){
                        Log.i("locationError", ex.message.toString())
                        Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
            return value
        }
    }
}
