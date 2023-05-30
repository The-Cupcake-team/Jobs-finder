package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.model.JobTitleDto
import com.cupcake.jobsfinder.ui.mapper.Mapper


class JobTitleMapper @inject constructor() : Mapper<JobTitleDto, JobTitle> {
    override fun mapTo(input: JobTitleDto): JobTitle {
        TODO("Not yet implemented")
    }
}