package com.cupcake.usecase


import repo.JobFinderRepository
import javax.inject.Inject

class GetAllJobTitleUseCase @Inject constructor(
    private val jobTitleRepository: JobFinderRepository,
) {
//    suspend operator fun invoke() = jobTitleRepository.getAllJobTitles()
//        .map { jobTitleDto -> jobTitleDto.toJobTitle() }
}
