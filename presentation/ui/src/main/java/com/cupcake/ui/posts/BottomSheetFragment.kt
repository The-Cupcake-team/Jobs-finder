package com.cupcake.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.cupcake.ui.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomSheetButtons()
    }

    private fun setupBottomSheetButtons(){
        val followButton = binding.root.findViewById<Button>(R.id.followTextView)
        followButton.setOnClickListener {
            Toast.makeText(requireContext(), "Follow button clicked", Toast.LENGTH_SHORT).show()
        }

        val saveButton = binding.root.findViewById<Button>(R.id.saveTextView)
        saveButton.setOnClickListener {
            Toast.makeText(requireContext(), "Save button clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
