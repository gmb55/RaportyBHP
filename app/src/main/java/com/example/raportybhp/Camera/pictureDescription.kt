package com.example.raportybhp.Camera

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.raportybhp.R

abstract class pictureDescription : AppCompatActivity() {

    lateinit var pickBTN: Button
    lateinit var dspBTN: Button
    lateinit var imageToPick: ImageView
    lateinit var picDES : EditText
//    private lateinit var mStorageRef : StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.picture_description)
//        mStorageRef = FirebaseStorage.getInstance().getReference("Images")


        imageToPick = findViewById(R.id.imageToPick)
        dspBTN = findViewById(R.id.dspBTN)

        pickBTN = findViewById(R.id.nextBTN)

        dspBTN.setOnClickListener() {
            display()
//            imageUploader()
        }

        pickBTN.setOnClickListener() {
            next()
        }

        picDES = findViewById(R.id.picDES)

    }

//    private fun imageUploader() {
//        var ref = mStorageRef.child(System.currentTimeMillis().toString() + "." + "png")
//
//        var filePath = getFile()
//
//        val file = Uri.fromFile(filePath)
//
//        ref.putFile(file)
//            .addOnSuccessListener { taskSnapshot ->
//                // Get a URL to the uploaded content
//             //   val downloadUrl = taskSnapshot.getDownloadUrl()
//
//            Toast.makeText(this, "Image Uploaded Successfully", Toast.LENGTH_SHORT ).show()
//            }
//            .addOnFailureListener {
//                // Handle unsuccessful uploads
//                // ...
//            }
//    }

    private fun next() {
        var text = picDES.text.toString()
    }

//    private fun getFile() : File {
//        val extra = intent.getStringExtra("pictureDest")
//
//        val sd = Environment.getExternalStorageDirectory()
//        val dest = File(sd, extra)
//
//        return dest
//    }

    private fun display() {

//        var file = getFile()

//        Picasso.get().load(file).into(imageToPick)
    }
}