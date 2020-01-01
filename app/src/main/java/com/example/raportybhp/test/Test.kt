package com.example.raportybhp.test

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.raportybhp.R
import com.example.raportybhp.addProject.projectsDTB
import com.google.firebase.database.*

class Test : AppCompatActivity(){

    lateinit var  ref : DatabaseReference

    lateinit var projectsList: MutableList<projectsDTB>

    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)

        projectsList = mutableListOf()
        listView = findViewById(R.id.listView)

        ref = FirebaseDatabase.getInstance().getReference("projects")

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                projectsList.clear()

                if (p0.exists()) {

                    for (p in p0.children) {
                        val project = p.getValue(projectsDTB::class.java)

                        projectsList.add(project!!)
                    }

                    val adapter = ProjectAdapter(applicationContext, R.layout.projects_list, projectsList)
                    listView.adapter = adapter
                }
            }

        })

    }

    }