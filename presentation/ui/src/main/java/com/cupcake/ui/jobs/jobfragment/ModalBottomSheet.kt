package com.cupcake.ui.jobs.jobfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.cupcake.ui.R
import com.cupcake.viewmodels.jobs.JobUiState
import com.cupcake.viewmodels.jobs.JobsEvent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class ModalBottomSheet(private val model:JobUiState ) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


    return    inflater.inflate(R.layout.edit_job_post_bottom_sheets, container, false)

    }

    override fun onResume() {
        super.onResume()
        val share : TextView = requireView().findViewById(R.id.share)
        share.setOnClickListener {
            sharePost(id = model.id)
        }
    }
    private fun sharePost( id : String) {
        val intent = Intent(Intent.ACTION_SEND)
        val base= "https://cup-cake-media-dw2pb.ondigitalocean.app/"
        val shareBody =
            getString(
                R.string.sharePost,
                base.plus(id),

                )
        Log.d("SharePost", "sharePost() called with id: $shareBody")

        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(intent)
    }
    private fun savePost(model:JobUiState ){


    }






}