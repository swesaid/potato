package com.example.roomdetailsbottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
        try {
            // Create a BottomSheetDialog instance
            val bottomSheetDialog = BottomSheetDialog(this)

            // Inflate the bottom sheet layout with parent as the root view to avoid passing `null`
            val bottomSheetView = LayoutInflater.from(this).inflate(
                R.layout.activity_layout_bottom_sheet,
                findViewById<ViewGroup>(R.id.main), // Attach to a parent layout
                false
            )

            // Set the view for the BottomSheetDialog
            bottomSheetDialog.setContentView(bottomSheetView)

            // Retrieve the BottomSheetBehavior directly from the dialog content
            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            if (bottomSheet != null) {
                val behavior = BottomSheetBehavior.from(bottomSheet)

                // Set initial collapsed peek height to fit everything till "See the Schedule" button
                behavior.peekHeight = 400 // Adjust this based on your design and screen size
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED

                // Expand/Collapse Description Handling
                val roomDescription = bottomSheetView.findViewById<TextView>(R.id.roomDescription)
                roomDescription.maxLines = 3 // Limit lines initially for collapsed state

                // Listen to bottom sheet state changes
                behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        when (newState) {
                            BottomSheetBehavior.STATE_EXPANDED -> {
                                // Remove maxLines constraint to show full text on expansion
                                roomDescription.maxLines = Int.MAX_VALUE
                            }
                            BottomSheetBehavior.STATE_COLLAPSED -> {
                                // Reset maxLines to 3 for collapsed state
                                roomDescription.maxLines = 3
                            }
                            BottomSheetBehavior.STATE_HIDDEN -> {
                                bottomSheetDialog.dismiss()
                            }
                            // Handle additional states to satisfy the warning
                            BottomSheetBehavior.STATE_DRAGGING -> { /* No action needed */ }
                            BottomSheetBehavior.STATE_HALF_EXPANDED -> { /* No action needed */ }
                            BottomSheetBehavior.STATE_SETTLING -> { /* No action needed */ }
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        // Optional: Animate elements during the slide if desired
                    }
                })
            } else {
                Log.e("BottomSheet", "Failed to find bottom sheet behavior.")
            }

            // Set up the back button to close the bottom sheet
            val backButton = bottomSheetView.findViewById<ImageButton>(R.id.imageButton2)
            backButton?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            // Show the bottom sheet dialog
            bottomSheetDialog.show()
        } catch (e: Exception) {
            Log.e("BottomSheet", "Error displaying bottom sheet", e)
        }
    }
}
