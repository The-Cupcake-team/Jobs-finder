package com.cupcake.ui.jobs

import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cupcake.ui.databinding.DialogBinding

class DialogJob(
    context: Context,
) {
    private val alertDialog: AlertDialog
    private val binding: DialogBinding

    init {
        val alertBuilder = AlertDialog.Builder(context)
        binding = DialogBinding.inflate(LayoutInflater.from(context))
        alertBuilder.setView(binding.root)
        alertDialog = alertBuilder.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCancelable(false)
        binding.applyButton.setOnClickListener {

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