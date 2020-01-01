package com.example.raportybhp

import java.text.SimpleDateFormat
import java.util.*

class fileName {



    fun getName(pattern : String) : String {

        val mFileName = SimpleDateFormat(
            pattern,
            Locale.getDefault()
        ).format(System.currentTimeMillis())

        return mFileName
    }
}