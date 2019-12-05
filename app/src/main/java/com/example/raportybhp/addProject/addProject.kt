package com.example.raportybhp.addProject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.raportybhp.R
import com.google.firebase.database.FirebaseDatabase

class addProject : AppCompatActivity(){

    lateinit var editTextGW: EditText
    lateinit var editTextIN: EditText
    lateinit var editTextTAG: EditText
    lateinit var editTextName: EditText

    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_project)

        editTextGW = findViewById(R.id.editTextGW)
        editTextIN = findViewById(R.id.editTextIN)
        editTextTAG = findViewById(R.id.editTextTAG)
        editTextName = findViewById(R.id.editTextName)

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

        val ref = FirebaseDatabase.getInstance().getReference("projects")
        val heroID = ref.push().key

        val hero = projectsDTB(heroID, nameGW, nameIN, TAG, name )

        ref.child(heroID.toString()).setValue(hero).addOnCompleteListener {
            Toast.makeText(applicationContext, "Hero saved successfully", Toast.LENGTH_SHORT).show()
        }
    }
}