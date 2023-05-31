package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.dto.JobTitleDto
import com.cupcake.jobsfinder.domain.model.JobTitle
import com.cupcake.jobsfinder.ui.mapper.Mapper
import javax.inject.Inject


class JobTitleMapper @Inject constructor() : Mapper<JobTitleDto, JobTitle> {
    override fun mapTo(input: JobTitleDto): JobTitle {
        return JobTitle(
            companyName = input.companyName,
            workType = input.workType,
            location = input.location,
            jobType = input.jobType,
            jobDescription = input.jobDescription,
            salary = input.salary
        )
    }
}