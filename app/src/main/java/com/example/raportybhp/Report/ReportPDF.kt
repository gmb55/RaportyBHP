package com.example.raportybhp.Report

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.raportybhp.R
import com.itextpdf.text.Document
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfPCell
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ReportPDF : AppCompatActivity() {

    private val STORAGE_CODE: Int = 100
    lateinit var saveBTN : Button
    lateinit var textPDF : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)

        saveBTN = findViewById(R.id.SavePDF)
        textPDF = findViewById(R.id.TextPDF)

        saveBTN.setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ) {
                    val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions, STORAGE_CODE)
                }
                else {
                    savePDF()
                }
            }
            else {
                savePDF()
            }
        }
    }

    private fun savePDF() {

        val mDoc = Document (PageSize.A4.rotate())

        val mFileName = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())

        val mFilePath = Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ".pdf"
        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))

            mDoc.open()

            val mText = textPDF.text.toString()

            mDoc.addAuthor("Ja")

            var heading = PdfPTable(4)
            var cell1 = PdfPCell(Phrase("cośtam"))

            for (x in 0..5) {
                if (x <= 4) {
                    heading.addCell(cell1)
                } else {
                    heading.addCell("")
                }
                }

            mDoc.add(Paragraph(mText))
            mDoc.add(heading)

            mDoc.close()

            Toast.makeText(this,"$mFileName.pdf\nis saved to\n$mFilePath",Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            STORAGE_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savePDF()
                }
                else {
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}