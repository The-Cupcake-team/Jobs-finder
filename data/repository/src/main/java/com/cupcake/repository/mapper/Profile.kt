package com.cupcake.repository.mapper

import com.cupcake.models.Education
import com.cupcake.remote.response.profile.EducationDto

fun EducationDto.toEducation(): Education{
    return Education(
        id = this.id,
        degree = this.degree,
        school = this.school,
        city = this.city,
        startDate = this.date?.start,
        endDate = this.date?.end,

        )
}