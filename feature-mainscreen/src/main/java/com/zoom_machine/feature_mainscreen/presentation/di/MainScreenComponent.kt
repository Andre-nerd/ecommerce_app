@file:Suppress("unused")

package com.zoom_machine.feature_mainscreen.presentation.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.zoom_machine.api.services.MainScreenService
import com.zoom_machine.core.Feature
import com.zoom_machine.database.mainscreen_model.MainScreenDao
import com.zoom_machine.feature_mainscreen.data.MainScreenRepositoryImpl
import com.zoom_machine.feature_mainscreen.data.SharedPrefMainScreen
import com.zoom_machine.feature_mainscreen.domain.MainScreenRepository
import com.zoom_machine.feature_mainscreen.presentation.ui.MainScreenFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import kotlin.properties.Delegates.notNull


@[Feature Component(
    modules = [BindRepository::class],
    dependencies = [MainScreenDeps::class]
)]
internal interface MainScreenComponent {
    fun inject(fragment: MainScreenFragment)

    @Component.Builder
    interface Builder {
        fun deps(mainScreenDeps: MainScreenDeps): Builder
        fun build(): MainScreenComponent
    }
}

@Module
interface BindRepository {
    @Binds
    fun bindRepository(repositoryImpl: MainScreenRepositoryImpl): MainScreenRepository
}

interface MainScreenDeps {
    val mainScreenService: MainScreenService
    val mainScreenDao: MainScreenDao
    val sharedPrefMainScreen: SharedPrefMainScreen
}


interface MainScreenProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: MainScreenDeps

    companion object : MainScreenProvider by MainScreenDepsStore
}

object MainScreenDepsStore : MainScreenProvider {
    override var deps: MainScreenDeps by notNull()
}

internal class MainScreenComponentViewModel : ViewModel() {
    val newDetailComponent = DaggerMainScreenComponent
        .builder()
        .deps(MainScreenProvider.deps)
        .build()
}
