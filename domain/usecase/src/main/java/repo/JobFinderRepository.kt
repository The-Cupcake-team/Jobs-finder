package repo

import com.cupcake.models.Job
import com.cupcake.models.JobTitle
import com.cupcake.models.JobWithTitle
import com.cupcake.models.Post

interface JobFinderRepository {


    // region Job

    suspend fun createJob(jobInfo: Job): Boolean

    suspend fun getJobs(): List<Job>

    suspend fun getAllJobTitles(): List<JobTitle>

    suspend fun getJobById(jobId: String): Job

    suspend fun insertJob(job: JobWithTitle)

    suspend fun deleteJob(job: JobWithTitle)

    suspend fun getSavedJobById(id: String): JobWithTitle?

    //endregion

    // clean up

    // region Post

    suspend fun createPost(content: String): Post

    suspend fun getAllPosts(): List<Post>

    suspend fun getPostById(id: String): Post


    //endregion

}