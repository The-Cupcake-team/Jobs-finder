package com.cupcake.repository.mapper

import com.cupcake.models.Education
import com.cupcake.models.Skill
import com.cupcake.remote.response.EducationDto
import com.cupcake.remote.response.SkillsDto

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

fun SkillsDto.toSkill(): Skill {
    return Skill(
        skill=skill,
        id = id,
        userId=userId


    )
}