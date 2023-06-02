package com.cupcake.jobsfinder.data.repository
import com.cupcake.jobsfinder.data.remote.response.JobDto
import com.cupcake.jobsfinder.data.dto.JobTitleDto

   
interface Repository {

  suspend fun getAllJobTitles():List<JobTitleDto>
  
  suspend fun createJob(jobInfo: JobDto): Boolean

	suspend fun createPost(content: String): Boolean


}