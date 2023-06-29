package com.cupcake.ui.post

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentCreatePostBinding
import com.cupcake.viewmodels.post.CreatePostEvent
import com.cupcake.viewmodels.post.CreatePostViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream


class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>(
    R.layout.fragment_create_post,
    CreatePostViewModel::class.java
) {
    override val LOG_TAG: String = this::class.java.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUI.setupWithNavController(binding.toolbarCreatePost, findNavController())
        setSpinnerAdapter()
        observePostState()
    }

    private fun observePostState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postEvent.collect { postEvent ->
                    postEvent.getContentIfNotHandled()?.let { event ->
                        handlePostEvent(event)
                    }
                }
            }
        }
    }

    private fun handlePostEvent(event: CreatePostEvent) {
        when (event) {
            CreatePostEvent.OnCameraClick -> checkPermissionAndOpenCamera()
            CreatePostEvent.OnPhotoClick -> checkPermissionAndOpenGallery()
            CreatePostEvent.OnPostClick -> navigateToHome()
        }
    }

    private fun navigateToHome() {
        Toast.makeText(context, "Post created successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigate(CreatePostFragmentDirections.actionCreatePostFragmentToPostsFragment())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CAMERA_CODE -> {
                    val bitmapImageFromCamera = data?.extras?.get("data") as Bitmap
                    val imageFile = convertBitmapToFile(bitmapImageFromCamera)
                    viewModel.handleImageResult(imageFile)
                }

                REQUEST_GALLERY_CODE -> {
                    val selectedImageUri = data?.data
                    val imageFile = selectedImageUri?.let { convertUriToFile(context, it) }
                    viewModel.handleImageResult(imageFile)
                }
            }
        }
    }

    private fun convertBitmapToFile(bitmap: Bitmap): File? {
        return context?.let { context ->
            val fileName = "${System.currentTimeMillis()}.png"
            val file = File(context.cacheDir, fileName)
            FileOutputStream(file).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
            }
            file
        }
    }


    private fun convertUriToFile(context: Context?, uri: Uri): File? {
        val inputStream = context?.contentResolver?.openInputStream(uri)
        inputStream?.use { input ->
            val fileName = "${System.currentTimeMillis()}.png"
            val file = File(context.cacheDir, fileName)
            val outputStream = FileOutputStream(file)
            val buffer = ByteArray(4 * 1024)
            while (true) {
                val bytesRead = input.read(buffer)
                if (bytesRead == -1) break
                outputStream.write(buffer, 0, bytesRead)
            }
            outputStream.flush()
            outputStream.close()
            return file
        }
        return null
    }


    private fun checkPermissionAndOpenCamera() {
        if (hasPermission(PERMISSION_CAMERA, PERMISSION_STORAGE)) {
            openCamera()
        } else {
            requestPermission(REQUEST_CAMERA_CODE, PERMISSION_CAMERA, PERMISSION_STORAGE)
        }
    }

    private fun checkPermissionAndOpenGallery() {
        if (hasPermission(PERMISSION_STORAGE)) {
            openGallery()
        } else {
            requestPermission(REQUEST_GALLERY_CODE, PERMISSION_STORAGE)
        }
    }

    private fun hasPermission(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    private fun requestPermission(requestCode: Int, vararg permissions: String) {
        ActivityCompat.requestPermissions(requireActivity(), permissions, requestCode)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA_CODE -> {
                if (isPermissionGranted(grantResults)) {
                    openCamera()
                } else {
                    showPermissionAlertDialog()
                }
            }

            REQUEST_GALLERY_CODE -> {
                if (isPermissionGranted(grantResults)) {
                    openGallery()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "You have denied the storage permission to select an image",
                        Toast.LENGTH_SHORT
                    ).show()
                    showPermissionAlertDialog()
                }
            }
        }
    }

    private fun isPermissionGranted(grantResults: IntArray): Boolean {
        return grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    private fun showPermissionAlertDialog() {
        AlertDialog.Builder(requireContext()).setMessage(
            "It looks like you have turned off the permission " +
                    "required for this feature. You can enable it under app settings."
        )
            .setPositiveButton("Go To Settings") { _, _ ->
                goToAppSettings()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun goToAppSettings() {
        try {
            val settingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            settingsIntent.data = Uri.fromParts("package", requireActivity().packageName, null)
            startActivity(settingsIntent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA_CODE)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/"
        startActivityForResult(galleryIntent, REQUEST_GALLERY_CODE)
    }

    private fun setSpinnerAdapter() {
        val listStateVisibility = listOf(VISIBILITY_PUBLIC, VISIBILITY_PRIVATE)
        val adapterSpinner = ArrayAdapter(
            requireContext(),
            R.layout.custom_spinner,
            listStateVisibility
        )
        binding.spinnerStatus.apply {
            adapter = adapterSpinner
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(
                        requireContext(),
                        "Please select the status visibility of the post",
                        Toast.LENGTH_SHORT
                    ).show()
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
        const val PERMISSION_CAMERA = Manifest.permission.CAMERA
        const val PERMISSION_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    }
}
