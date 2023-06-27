package com.cupcake.usecase


import com.cupcake.models.Education
import com.cupcake.models.Skill
import repo.JobFinderRepository
import javax.inject.Inject

class ResumeUseCase @Inject constructor(
    private val jobFinderRepository : JobFinderRepository
) {

    suspend fun  getAllEducations():List<Education>{
        return  jobFinderRepository.getAllEducations()
    }

    suspend fun  getAllSkills():List<Skill>{
        return  jobFinderRepository.getAllSkills()
    }

    suspend fun  deleteSkills(id : String){
        return  jobFinderRepository.deleteSkills(id)
    }



}