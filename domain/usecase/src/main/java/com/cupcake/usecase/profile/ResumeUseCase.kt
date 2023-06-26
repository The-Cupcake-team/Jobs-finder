package com.cupcake.usecase.profile

import com.cupcake.models.Education
import repo.JobFinderRepository
import javax.inject.Inject

class ResumeUseCase @Inject constructor(
   private val jobFinderRepository : JobFinderRepository
) {

    suspend fun  getAllEducations():List<Education>{
        return  jobFinderRepository.getAllEducations()
    }


}