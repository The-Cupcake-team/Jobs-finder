package com.cupcake.jobsfinder.data.repository
import com.cupcake.jobsfinder.data.dto.JobTitleDto
import com.cupcake.jobsfinder.data.remote.JobApiService
import com.cupcake.jobsfinder.data.remote.response.JobDto
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: JobApiService
) : Repository {
  
    override suspend fun getAllJobTitles(): List<JobTitleDto> {
        return listOf(
            JobTitleDto(
                id = "ID HERE",
                title = "Android"
            )
        )
    }
    
    override suspend fun createJob(jobInfo: JobDto): Boolean {
        return try {
            val job = api.createJob(jobInfo)
            // need more logic
            return true
        } catch (e: Throwable) {
            return false
        }
    }
   	override suspend fun createPost(content: String): Boolean {
		delay(2000)
		return true
	  }

    
}