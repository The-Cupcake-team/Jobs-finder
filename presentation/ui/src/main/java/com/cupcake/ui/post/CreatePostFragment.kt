package com.cupcake.ui.post

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import coil.load
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentCreatePostBinding
import com.cupcake.viewmodels.post.CreatePostViewModel
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
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


    private fun checkPermissionOfCamera() {
        Dexter.withContext(this.requireContext()).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                report?.let {
                    if (report.areAllPermissionsGranted())
                        openCamera()
                }
            }
            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                showRotationDialogPermission()
            }
        }).check()

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

    private fun checkPermissionOfGallery() {
        Dexter.withContext(this.requireActivity()).withPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                openGallery()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(
                    requireContext(),
                    "You have denied the storage permission to select image",
                    Toast.LENGTH_SHORT
                ).show()
                showRotationDialogPermission()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                showRotationDialogPermission()
            }

        }).check()
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
