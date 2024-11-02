package com.example.roomdetailsbottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Apply insets to your root layout if necessary
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up the button click to show the bottom sheet
        findViewById<View>(R.id.button).setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        // Create a BottomSheetDialog instance
        val bottomSheetDialog = BottomSheetDialog(this)

        // Inflate the bottom sheet layout
        val bottomSheetView = LayoutInflater.from(this).inflate(
            R.layout.activity_layout_bottom_sheet,
            findViewById(R.id.main), // Use the root view here
            false // Set attachToRoot to false
        )

        // Set the view for the BottomSheetDialog
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }
}
