package com.example.raportybhp.addProject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.raportybhp.Camera.TakePhoto
import com.example.raportybhp.R
import com.example.raportybhp.keyKeeper
import com.example.raportybhp.projects.Projects
import com.google.firebase.database.*

class addProject : AppCompatActivity(){

    lateinit var editTextGW: EditText
    lateinit var editTextIN: EditText
    lateinit var editTextTAG: EditText
    lateinit var editTextName: EditText

    lateinit var ref : DatabaseReference
    lateinit var projectList: MutableList<Projects>

    lateinit var buttonSave: Button

    lateinit var keyValue: keyKeeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_project)



        ref = FirebaseDatabase.getInstance().getReference("projects")

        projectList = mutableListOf()

        editTextGW = findViewById(R.id.editTextGW)
        editTextIN = findViewById(R.id.editTextIN)
        editTextTAG = findViewById(R.id.editTextTAG)
        editTextName = findViewById(R.id.editTextName)

        buttonSave = findViewById(R.id.saveBTN)

        buttonSave.setOnClickListener{
            saveProject()
        }
    }

    private fun saveProject() {
        val nameGW = editTextGW.text.toString().trim()
        val nameIN = editTextIN.text.toString().trim()
        val TAG = editTextTAG.text.toString().trim()
        val name = editTextName.text.toString().trim()

        if(name.isEmpty()) {
            editTextName.error = "Please enter a name"
            return
        }

        val ID = ref.push().key

        val project = projectsDTB(ID, nameGW, nameIN, TAG, name )

        ref.child(ID.toString()).setValue(project).addOnCompleteListener {
            Toast.makeText(applicationContext, "Project add successfully", Toast.LENGTH_SHORT).show()
            takePhoto()
        }

       keyValue = keyKeeper(ID.toString())
    }

    private fun takePhoto() {
        var intent = Intent(this, TakePhoto::class.java)
        var key = keyValue.getKey()
        intent.putExtra("key",key)
        startActivity(intent)
    }


}