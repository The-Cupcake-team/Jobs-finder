package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.domain.model.JobTitle

fun JobTitleDto.toJobTitle(): JobTitle {
    return JobTitle(
        id = id,
        title = title,
    )
}