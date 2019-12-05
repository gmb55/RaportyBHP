package com.example.raportybhp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.raportybhp.Camera.TakePhoto
import com.example.raportybhp.Report.ReportPDF
import com.example.raportybhp.projects.Projects


class MainActivity : AppCompatActivity() {

    lateinit var takePhotoBtn : Button
    lateinit var addReport : Button
    lateinit var addProjectButton : Button

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

        addProjectButton = findViewById(R.id.ProjectsBTN)

        addProjectButton.setOnClickListener(){
            addProjectFNT()
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

    private fun addProjectFNT() {
        var intent = Intent(this, Projects::class.java)

        startActivity(intent)
    }
}