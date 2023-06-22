package com.cupcake.ui.post

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import coil.load
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentCreatePostBinding
import com.cupcake.viewmodels.post.CreatePostViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>(
    R.layout.fragment_create_post,
    CreatePostViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCamera.setOnClickListener {
            checkPermissionOfCamera()
        }
        binding.buttonPhoto.setOnClickListener {
            checkPermissionOfGallery()
        }
        setSpinnerAdapter()
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.isPostCreated) {
                    Snackbar.make(requireView(), POST_CREATED, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CAMERA_CODE -> {
                    binding.relativeLayout.visibility = View.VISIBLE
                    val bitmapImageFromCamera = data?.extras?.get("data") as Bitmap

                    binding.imageOfPost.load(bitmapImageFromCamera) {
                        crossfade(true)
                        crossfade(1000)
                    }
                    binding.imageOfPost.visibility = View.VISIBLE
                    binding.iconButtonRemoveImage.setOnClickListener {
                        binding.relativeLayout.visibility = View.GONE
                    }
                }
                REQUEST_GALLERY_CODE -> {
                    binding.imageOfPost.load(data?.data)
                    binding.relativeLayout.visibility = View.VISIBLE
                    binding.iconButtonRemoveImage.setOnClickListener {
                        binding.relativeLayout.visibility = View.GONE
                    }
                }
            }
        }
    }
override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>,
    grantResults: IntArray
) {
    when (requestCode) {
        REQUEST_CAMERA_CODE -> {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                showRotationDialogPermission()
            }
        }

        REQUEST_GALLERY_CODE -> {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "You have denied the storage permission to select an image",
                    Toast.LENGTH_SHORT
                ).show()
                showRotationDialogPermission()
            }
        }
    }
}
private fun checkPermissionOfCamera() {
    val cameraPermission = Manifest.permission.CAMERA
    val storagePermission = Manifest.permission.READ_EXTERNAL_STORAGE

    val permissionsToRequest = mutableListOf<String>()
    if (ContextCompat.checkSelfPermission(
            requireContext(),
            cameraPermission
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        permissionsToRequest.add(cameraPermission)
    }
    if (ContextCompat.checkSelfPermission(
            requireContext(),
            storagePermission
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        permissionsToRequest.add(storagePermission)
    }

    if (permissionsToRequest.isNotEmpty()) {
        requestPermissions(
            permissionsToRequest.toTypedArray(),
            REQUEST_CAMERA_CODE
        )
    } else {
        openCamera()
    }
}

    private fun checkPermissionOfGallery() {
        val storagePermission = Manifest.permission.READ_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                storagePermission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(storagePermission),
                REQUEST_GALLERY_CODE
            )
        } else {
            openGallery()
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA_CODE)
    }

    private fun showRotationDialogPermission() {
        AlertDialog.Builder(this.context).setMessage(
            "it look like you have turned off permission" +
                    "required for this feature, it can be enable under app setting"
        )
            .setPositiveButton("Go To Setting") { _, _ ->

                try {
                    val settingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    settingsIntent.data =
                        Uri.fromParts("package", requireActivity().packageName, null)
                    startActivity(settingsIntent)

                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }

            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/"
        startActivityForResult(galleryIntent, REQUEST_GALLERY_CODE)
    }

    private fun setSpinnerAdapter(){
        val listStateVisibility = listOf(VISIBILITY_PUBLIC, VISIBILITY_PRIVATE)
        val adapterSpinner = ArrayAdapter(this.requireContext(), R.layout.custom_spinner,listStateVisibility)
        binding.spinnerStatus.apply{
            adapter = adapterSpinner
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(requireContext(), listStateVisibility[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(requireContext(), "Please Select the status visibility of post", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private companion object {
        const val REQUEST_CAMERA_CODE = 1
        const val REQUEST_GALLERY_CODE = 2
        const val VISIBILITY_PUBLIC = "Public"
        const val VISIBILITY_PRIVATE = "Private"
        const val POST_CREATED = "Post Created Successfully"
    }
}
