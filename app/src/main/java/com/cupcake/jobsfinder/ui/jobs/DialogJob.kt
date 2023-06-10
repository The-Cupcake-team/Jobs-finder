package com.cupcake.jobsfinder.ui.jobs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cupcake.jobsfinder.databinding.DialogBinding

class Dialog(
    context: Context,
) {
    private val alertDialog: AlertDialog
    private val binding: DialogBinding

    init {
        val alertBuilder = AlertDialog.Builder(context)
        binding = DialogBinding.inflate(LayoutInflater.from(context))
        alertBuilder.setView(binding.root)
        alertDialog = alertBuilder.create()
        alertDialog.setCancelable(false)
        binding.textView2.text = "To complete the Application you need to send your Resume"
        binding.applyButton.setOnClickListener {
            Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }
        binding.cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    fun show() {
        alertDialog.show()
    }

    fun dismiss() {
        alertDialog.dismiss()
    }
}