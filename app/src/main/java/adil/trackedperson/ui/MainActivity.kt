package adil.trackedperson


import adil.trackedperson.data.GetLocationCallback
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanContract
import adil.viewmodelconnector.CaptureCamera
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.trackedperson.viewmodelconnector.DatabaseFunctionality
import com.example.trackedperson.viewmodelconnector.GetLocation
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    lateinit var latitude:TextView
    lateinit var longitude:TextView
    lateinit var btnLocation:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        btnLocation = findViewById(R.id.getAddress)
        btnLocation.setOnClickListener{view ->
            runOnUiThread {
                getLocation()
//                var number = 1
//                while (true){
//                    Toast.makeText(this, "text number $number", Toast.LENGTH_LONG).show()
//                    Thread.sleep(8000L)
//                    number++
//                }
            }

        }
        try{
//            scanCode()
        }catch(ex:Exception){
            Log.i("QrCodeError", ex.message.toString())
        }
    }
    private fun getLocation(){
        val location = GetLocation(this@MainActivity)
        location.getLocation(latitude, longitude, object: GetLocationCallback{
            override fun onFailure(msg: String?) {
                Toast.makeText(this@MainActivity, "location permission isn't granted", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(latitude: Double, longitude: Double) {
                var databaseFunctionality = DatabaseFunctionality(this@MainActivity)
                databaseFunctionality.addLoction(1, latitude, longitude)
            }
        })
    }
    fun scanCode(){
        val scanOptions:ScanOptions = ScanOptions()
        scanOptions.apply {
            setPrompt("Scan QrCode")
            setBeepEnabled(true)
            setOrientationLocked(true)
            captureActivity = CaptureCamera::class.java
        }
       barcodeLauncher.launch(scanOptions)
    }
    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(ScanContract()){ result:ScanIntentResult ->
	    if(result.contents != null){
            try{
                val databaseFunctionality = DatabaseFunctionality(this@MainActivity)
                databaseFunctionality.addObjectInDatabase(result)
            }catch (ex: Exception){
                Log.i("resultError", ex.message.toString())
            }
	    }
    }
}
