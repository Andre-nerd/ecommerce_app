package com.zoom_machine.l_tech_app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoom_machine.l_tech_app.domain.AuthorizationRepository
import com.zoom_machine.l_tech_app.domain.ContentRepository
import com.zoom_machine.l_tech_app.ui.authorization.AuthorizationViewModel
import com.zoom_machine.l_tech_app.ui.dev_exam.DevExamViewModel
import dagger.assisted.AssistedInject


class ViewModelFactory @AssistedInject constructor(
    private val authorizationRepository: AuthorizationRepository,
    private val contentRepository: ContentRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AuthorizationViewModel::class.java -> AuthorizationViewModel(authorizationRepository) as T
            DevExamViewModel::class.java -> DevExamViewModel(contentRepository) as T
            else -> null as T
        }
    }
}