package com.cupcake.jobsfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.cupcake.jobsfinder.ui.create_job.CreateJobViewModel
import com.cupcake.jobsfinder.ui.jobs.JobViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val jobViewModel: CreateJobViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jobViewModel.createJob()
    }
}