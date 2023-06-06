package com.cupcake.jobsfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.cupcake.jobsfinder.ui.create_job.CreateJobViewModel
import com.cupcake.jobsfinder.ui.jobs.JobViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_create_job_form_one)
    }
}