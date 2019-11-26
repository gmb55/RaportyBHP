package com.example.raportybhp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.raportybhp.Camera.CameraState
import com.example.raportybhp.Camera.FlashState
import com.example.raportybhp.Camera.FotoapparatState
import com.example.raportybhp.Camera.TakePhoto
import com.example.raportybhp.Report.ReportPDF
import io.fotoapparat.Fotoapparat
import io.fotoapparat.configuration.CameraConfiguration
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.selector.*
import io.fotoapparat.view.CameraView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File



class MainActivity : AppCompatActivity() {

    lateinit var takePhotoBtn : Button
    lateinit var addReport : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takePhotoBtn = findViewById(R.id.tkPhBtn)

        takePhotoBtn.setOnClickListener(){
            takePhoto()
        }

        addReport = findViewById(R.id.AddReport)

        addReport.setOnClickListener(){
            addReport()
        }
    }

    private fun takePhoto() {
        var intent = Intent(this, TakePhoto::class.java)

        startActivity(intent)
    }

    private fun addReport() {
        var intent = Intent(this, ReportPDF::class.java)

        startActivity(intent)
    }
}