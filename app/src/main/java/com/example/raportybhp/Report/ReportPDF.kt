package com.example.raportybhp.Report

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.example.raportybhp.R
import com.example.raportybhp.fileName
import com.google.firebase.database.DatabaseReference
import com.itextpdf.text.*
import com.itextpdf.text.pdf.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.picture_description.*
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.io.ByteArrayOutputStream as ByteArrayOutputStream1


class ReportPDF : AppCompatActivity() {

    private val STORAGE_CODE: Int = 100
    lateinit var saveBTN: Button
    lateinit var textPDF: EditText
    lateinit var imgView: ImageView

    lateinit var ref: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)

        saveBTN = findViewById(R.id.SavePDF)
        textPDF = findViewById(R.id.TextPDF)

        saveBTN.setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions, STORAGE_CODE)
                } else {
                    savePDF()
                }
            } else {
                savePDF()
            }
        }
    }


    private fun savePDF() {

        val mDoc = Document(PageSize.A4.rotate())

        val fileNamePattern = "yyyyMMdd_HHmmss"
        val mFileName = fileName().getName(fileNamePattern)

        val mFilePath =
            Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ".pdf"
        try {
            PdfWriter.getInstance(mDoc, FileOutputStream(mFilePath))

            mDoc.open()

            // SET FONT

            var font = FontFactory.getFont("res/font/roboto_regular.ttf", "Cp1250", true)

            // GET TEXT FROM EDITTEXT

            val mText = textPDF.text.toString()

            mDoc.addAuthor("Ja")

            // SET DATE

            val date = fileName().getName("dd.MM.yyyy")

            // CREATE TABLE
            val numberHeadingColumns = 4
            var heading = PdfPTable(numberHeadingColumns)


            var testAssetsList = assets.list("")

            // CREATE IMG INSTANCE

            var ims = assets.open("cp_logo.jpg")
           var bmp = BitmapFactory.decodeStream(ims)
            val stream = ByteArrayOutputStream1()
           bmp.compress(Bitmap.CompressFormat.PNG, 100, stream)
           var img = Image.getInstance(stream.toByteArray())


            // SET DATE TO CELL

            var cell4Date = PdfPCell(Phrase(date))

            val title =
                "Budowa międzysystemowego gazociągu stanowiącego połączenie systemów przesyłowych Rzeczypospolitej Polskiej i Republiki Słowackiej - węzeł rozdzielczo-pomiarowy Strachocina"

            var cell2Title = PdfPCell(Phrase(title, font))

            heading.addCell("")
            heading.addCell(cell2Title)
            heading.addCell("")
            heading.addCell(cell4Date)

            mDoc.add(Paragraph(mText, font))
            mDoc.add(img)
            mDoc.add(heading)

            mDoc.close()

            Toast.makeText(this, "$mFileName.pdf\nis saved to\n$mFilePath", Toast.LENGTH_SHORT)
                .show()
        } catch (e: Exception) {
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
                } else {
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    internal inner class ImageEvent(protected var img: Image) : PdfPCellEvent {
        override fun cellLayout(
            cell: PdfPCell,
            position: Rectangle,
            canvases: Array<PdfContentByte>
        ) {
            img.scaleToFit(position.width, position.height)
            img.setAbsolutePosition(
                position.left + (position.width - img.scaledWidth) / 2,
                position.bottom + (position.height - img.scaledHeight) / 2
            )
            val canvas = canvases[PdfPTable.BACKGROUNDCANVAS]
            try {
                canvas.addImage(img)
            } catch (ex: DocumentException) { // do nothing
            }
        }
    }
}