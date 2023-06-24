package com.cupcake.ui.profile.resume.adapter

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.profile.resume.ResumeInteractionListener
import com.cupcake.viewmodels.profile.resume.ResumeItemUiState


class ProfileResumeAdapter ( listOfResume: List<ResumeItemUiState>,
                             listener : ResumeInteractionListener
): BaseAdapter<ResumeItemUiState>(
    listOfResume,listener
) {
    override var layoutId: Int=R.layout.item_profile_resume
}
val listOfResume: List<ResumeItemUiState> = arrayOf(
    ResumeItemUiState(R.drawable.education, R.string.education.toString()),
    ResumeItemUiState(R.drawable.skills, R.string.skills.toString()),
    ResumeItemUiState(R.drawable.employment, R.string.employment.toString()),
    ResumeItemUiState(R.drawable.languages, R.string.languages.toString()),
    ResumeItemUiState(R.drawable.courses, R.string.course.toString()),
).toList()

