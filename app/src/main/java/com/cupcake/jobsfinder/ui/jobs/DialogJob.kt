package com.cupcake.jobsfinder.ui.jobs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cupcake.jobsfinder.R
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
        binding.textViewMessageDialog.text = R.string.dialog_message.toString()
        binding.applyButton.setOnClickListener {
            Toast.makeText(context, "coming soon", Toast.LENGTH_SHORT).show()
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