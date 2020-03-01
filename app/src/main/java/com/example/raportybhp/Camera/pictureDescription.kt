package com.example.raportybhp.Camera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.raportybhp.FireBase.EventDTB
import com.example.raportybhp.R
import com.example.raportybhp.Report.ReportPDF
import com.example.raportybhp.addProject.projectsDTB
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.io.File

class pictureDescription : AppCompatActivity() {

    lateinit var pickBTN: Button
    lateinit var dspBTN: Button
    lateinit var imageToPick: ImageView
    lateinit var picDES : EditText
    lateinit var mStorageRef : StorageReference
    lateinit var ref : DatabaseReference
    lateinit var downloadUrl : Task<Uri>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.picture_description)

        mStorageRef = FirebaseStorage.getInstance().getReference("Images")
        ref = FirebaseDatabase.getInstance().getReference("events")

        imageToPick = findViewById(R.id.imageToPick)
        dspBTN = findViewById(R.id.dspBTN)
        pickBTN = findViewById(R.id.nextBTN)
        picDES = findViewById(R.id.picDES)

        dspBTN.setOnClickListener() {
            display()

        }

        pickBTN.setOnClickListener() {
            imageUploader()
        }
    }



    private fun imageUploader() {
        var storageRef = mStorageRef.child(System.currentTimeMillis().toString() + "." + "png")

        var filePath = getFile()

        val file = Uri.fromFile(filePath)

        var text = picDES.text.toString()
        val ID = ref.push().key


        storageRef.putFile(file)
            .addOnSuccessListener { taskSnapshot ->
                //   Get a URL to the uploaded content
                downloadUrl = taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    var uri = it.toString()

                    val event = EventDTB(ID, text, uri)




                    ref.child(ID.toString()).setValue(event).addOnCompleteListener {
                        Toast.makeText(applicationContext, "Event add successfully", Toast.LENGTH_SHORT)
                            .show()
                }


                }

            Toast.makeText(this, "Image Uploaded Successfully", Toast.LENGTH_SHORT ).show()

             //   deleteFile()
                pctDes()

            }
            .addOnFailureListener {
                // Handle unsuccessful uploads
                // ...
            }

    }

    private fun pctDes() {
        var intent2 = Intent(this, ReportPDF::class.java)
        var newFile = intent.getStringExtra("pictureDest")
        intent2.putExtra("pictureDest",newFile)
        startActivity(intent2)
    }

    private fun getFile() : File {
        val extra = intent.getStringExtra("pictureDest")

        val sd = Environment.getExternalStorageDirectory()
        val dest = File(sd, extra)

        return dest
    }

    private fun deleteFile(){
        val extra = intent.getStringExtra("pictureDest")

        val sd = Environment.getExternalStorageDirectory()
        val dest = File(sd, extra)
        dest.delete()

    }

    private fun display() {

        var file = getFile()

        Picasso.get().load(file).into(imageToPick)
    }

}