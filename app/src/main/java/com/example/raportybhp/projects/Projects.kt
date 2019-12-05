package com.example.raportybhp.projects

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.raportybhp.R
import com.example.raportybhp.addProject.addProject

class Projects : AppCompatActivity(){

    lateinit var addProjectButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.projects)

        addProjectButton = findViewById(R.id.addProjectsBTN)

        addProjectButton.setOnClickListener(){
            addProjectFUN()
        }

    }

    private fun addProjectFUN() {
        var intent = Intent(this, addProject::class.java)

        startActivity(intent)
    }


}