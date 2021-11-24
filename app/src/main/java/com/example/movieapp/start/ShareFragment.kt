package com.example.movieapp.start

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidmads.library.qrgenearator.QRGEncoder
import androidx.navigation.Navigation
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDirectorBinding
import com.example.movieapp.databinding.FragmentShareBinding
import com.google.zxing.qrcode.QRCodeWriter
import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView

import com.google.zxing.BarcodeFormat

import com.google.zxing.common.BitMatrix
import java.lang.Exception


class ShareFragment : Fragment() {
    private var _binding: FragmentShareBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShareBinding.inflate(inflater, container, false)
        // TODO: 24.11.2021 generate a database id and pass it to the intent
        val databaseId = "todo"//Database.
        val startSwipeButton= binding.startSwiping
        val shareButton = binding.shareLink
        val deepLink = "https://www.meineurl.com/path?key="+databaseId
        val qrView = binding.qrView

        startSwipeButton.setOnClickListener{
            val swipeIntent = Intents(databaseId,"admin",this.context)
            swipeIntent.intentToSwipe()
        }
        shareButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT,"Hi there, let's watch a movie together:"+deepLink)
            try {
                startActivity(intent)
            }catch (e: ActivityNotFoundException){
                Toast.makeText(this.context, "couldn't find a matching application on your device", Toast.LENGTH_SHORT).show()
            }
        }
        generateQRCode(deepLink,qrView)
        val root: View = binding.root
        return root
    }
    private fun generateQRCode(message : String,qrView: ImageView){
        val qrCodeWriter = QRCodeWriter()
        try {
            val bitMatrix = qrCodeWriter.encode(message, BarcodeFormat.QR_CODE, 200, 200)
            val bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565)
            for (x in 0..199) {
                for (y in 0..199) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            qrView.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }// source https://programmerworld.co/android/how-to-create-or-generate-qr-quick-response-code-in-your-android-app-complete-source-code/
    }
}