package com.cupcake.repository.mapper


import com.cupcake.models.JobTitle
import com.cupcake.remote.response.JobTitleDto

fun JobTitleDto.toJobTitle(): JobTitle {
    return JobTitle(
        id = id,
        title = title,
    )
}