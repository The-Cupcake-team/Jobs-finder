package com.cupcake.repository.mapper


import com.cupcake.jobsfinder.local.entities.JobTitleEntity
import com.cupcake.models.JobTitle
import com.cupcake.remote.response.JobTitleDto

fun JobTitleDto.toJobTitle(): JobTitle {
    return JobTitle(
        id = id,
        title = title,
    )
}

fun JobTitleEntity.toJobTitle(): JobTitle {
    return JobTitle(
        id = id,
        title = title
    )
}

fun JobTitleDto.toJobTitleEntity(): JobTitleEntity {
    return JobTitleEntity(
        id = id,
        title = title
    )
}