package com.example.raportybhp.Camera

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.raportybhp.R
import com.squareup.picasso.Picasso
import java.io.File

class pictureDescription : AppCompatActivity() {

    lateinit var pickBTN: Button
    lateinit var dspBTN: Button
    lateinit var imageToPick: ImageView
    lateinit var picDES : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.picture_description)

        imageToPick = findViewById(R.id.imageToPick)
        dspBTN = findViewById(R.id.dspBTN)

        pickBTN = findViewById(R.id.nextBTN)

        dspBTN.setOnClickListener() {
            dispaly()
        }

        pickBTN.setOnClickListener() {
            next()
        }

        picDES = findViewById(R.id.picDES)

    }

    private fun next() {
        var text = picDES.text.toString()
    }


    private fun dispaly() {
        val extra = intent.getStringExtra("pictureDest")

        val sd = Environment.getExternalStorageDirectory()
        val dest = File(sd, extra)

        Picasso.get().load(dest).into(imageToPick)
    }
}