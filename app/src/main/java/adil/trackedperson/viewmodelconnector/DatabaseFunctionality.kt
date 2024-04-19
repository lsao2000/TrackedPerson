package com.example.trackedperson.viewmodelconnector

import adil.trackedperson.data.ApiDatabase
import adil.trackedperson.data.GetDatabaseApi
import adil.trackedperson.data.ResponseEvent
import adil.trackedperson.data.ResponseObject
import android.content.Context
import com.journeyapps.barcodescanner.ScanIntentResult
import android.widget.Toast
import retrofit2.*

class DatabaseFunctionality(val context: Context) {
    fun addLoction(objectId:Long, latitude:Double, longitude:Double){
        var database = getDatabase()
        var addEvent = database.addEvent(objectId, latitude, longitude)
        addEvent.enqueue(object:Callback<ResponseEvent>{
            override fun onResponse(p0: Call<ResponseEvent>, response: Response<ResponseEvent>) {
               if(response.isSuccessful){
                   val responseEvent = response.body()
                   if(responseEvent?.requestCode == 200){
                       Toast.makeText(context, "success to add event", Toast.LENGTH_LONG).show()
                   }else{
                      Toast.makeText(context, "requestCode: ${response.code()}", Toast.LENGTH_LONG).show()
                   }
               }
            }
            override fun onFailure(p0: Call<ResponseEvent>, throwable: Throwable) {
                Toast.makeText(context, throwable.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
    fun addObjectInDatabase(result:ScanIntentResult){
        val database = getDatabase()
        val addObject = database.addObject(result.contents)
        addObject.enqueue(object: Callback<ResponseObject>{
            override fun onResponse(p0: Call<ResponseObject>, response: Response<ResponseObject>){
                if(response.isSuccessful){
                    val responseObject = response.body()
                    if(responseObject?.requestCode ==  200){
                        Toast.makeText(context, "success add the object", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "fail to add object", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(p0:Call<ResponseObject>, throwable:Throwable){
                Toast.makeText(context, throwable.message.toString(), Toast.LENGTH_LONG).show()
            }
        })

    }
    fun getDatabase(): ApiDatabase {
        val retrofit:Retrofit = GetDatabaseApi.getRetrofit()
        val database = retrofit.create(ApiDatabase::class.java)
        return database
    }
}
