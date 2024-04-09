package adil.trackedperson

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanContract
import adil.viewmodelconnector.CaptureCamera
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(ScanContract()){ result:ScanIntentResult ->
        if(result.contents != null){
//            val builder: AlertDialog.Builder = AlertDialog.Builder(applicationContext)
//                .apply {
//                        setTitle("Qrcode Message")
//                        setMessage(result.contents)
//                        setPositiveButton("ok", DialogInterface.OnClickListener{ dialogInterface, i -> dialogInterface.dismiss() })
//                    }
//        builder.show()
            Toast.makeText(applicationContext, result.contents.toString(), Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try{
            scanCode()
        }catch(Ex:Exception){
            Toast.makeText(applicationContext, Ex.message, Toast.LENGTH_LONG).show()
        }
    }
    fun scanCode(){
        val scanOptions:ScanOptions = ScanOptions()
        scanOptions.apply {
                setPrompt("Volume up your flash")
                setBeepEnabled(true)
                setOrientationLocked(true)
            captureActivity = CaptureCamera::class.java
            }
       barcodeLauncher.launch(scanOptions)
    }
}
