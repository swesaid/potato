package com.example.roomdetailsbottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the button click to show the bottom sheet
        findViewById<View>(R.id.button).setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        try {
            val bottomSheetDialog = BottomSheetDialog(this)
            val bottomSheetView = LayoutInflater.from(this).inflate(
                R.layout.activity_layout_bottom_sheet,
                findViewById<ViewGroup>(R.id.main),
                false
            )
            bottomSheetDialog.setContentView(bottomSheetView)

            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            if (bottomSheet != null) {
                val behavior = BottomSheetBehavior.from(bottomSheet)

                // Set a flexible peek height
                behavior.peekHeight = 1400 // Adjust to show some content and the "See the Schedule" button

                val roomDescription = bottomSheetView.findViewById<TextView>(R.id.roomDescription)
                roomDescription.maxLines = 3

                behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        when (newState) {
                            BottomSheetBehavior.STATE_EXPANDED -> {
                                roomDescription.maxLines = Int.MAX_VALUE
                            }
                            BottomSheetBehavior.STATE_COLLAPSED -> {
                                roomDescription.maxLines = 3
                            }
                            BottomSheetBehavior.STATE_HIDDEN -> {
                                bottomSheetDialog.dismiss()
                            }
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        // Optional animations based on slideOffset
                    }
                })
            }

            bottomSheetView.findViewById<ImageButton>(R.id.imageButton2)?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        } catch (e: Exception) {
            Log.e("BottomSheet", "Error displaying bottom sheet", e)
        }
    }
}
