package com.example.raportybhp.addProject

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class projectReader (key : String) {
    var  ref = FirebaseDatabase.getInstance().getReference("projects")

    lateinit var name : String

    var nameGW = ""
    var nameIN = ""



}