package com.example.qrdisplay

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val qrImage: ImageView = findViewById(R.id.qrView)
        val qrString: TextInputEditText = findViewById(R.id.qrData)
        val btnGenQR: Button = findViewById(R.id.btnGenQR)

        btnGenQR.setOnClickListener {
            val qrData = qrString.text.toString()
            qrGenerateAndDisplay(qrImage, qrData)
        }
    }

    fun qrGenerateAndDisplay(qrImg: ImageView, qrData: String){
        val writer = QRCodeWriter()
        // Here the size represents the pixels in QR Code
        val size = 300
        try{
            val bitMatrix = writer.encode(qrData, BarcodeFormat.QR_CODE, size, size)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            qrImg.setImageBitmap(bmp)
        }
        catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}