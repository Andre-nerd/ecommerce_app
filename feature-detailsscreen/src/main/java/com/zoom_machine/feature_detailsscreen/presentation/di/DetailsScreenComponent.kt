package com.zoom_machine.feature_detailsscreen.presentation.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.zoom_machine.api.services.DetailsScreenService
import com.zoom_machine.core.FeatureDetails
import com.zoom_machine.feature_detailsscreen.data.DetailsScreenRepositoryImpl
import com.zoom_machine.feature_detailsscreen.data.SharedPrefDetailsScreen
import com.zoom_machine.feature_detailsscreen.domain.DetailScreenRepository
import com.zoom_machine.feature_detailsscreen.presentation.ui.DetailsFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import kotlin.properties.Delegates

@[FeatureDetails Component(
    modules = [BindRepository::class],
    dependencies = [DetailsScreenDeps::class]
)]
internal interface DetailsScreenComponent {
    fun inject(fragment: DetailsFragment)

    @Component.Builder
    interface Builder {
        fun deps(detailsScreenDeps: DetailsScreenDeps): Builder
        fun build(): DetailsScreenComponent
    }
}

@Module
interface BindRepository {
    @Binds
    fun bindRepository(repositoryImpl: DetailsScreenRepositoryImpl): DetailScreenRepository
}

interface DetailsScreenDeps {
    val detailsScreenService: DetailsScreenService
    val sharedPrefDetailsScreen:SharedPrefDetailsScreen
}

interface DetailsScreenProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: DetailsScreenDeps

    companion object : DetailsScreenProvider by DetailsScreenDepsStore
}

object DetailsScreenDepsStore : DetailsScreenProvider {
    override var deps: DetailsScreenDeps by Delegates.notNull()
}

internal class DetailsScreenComponentViewModel : ViewModel() {
    val newDetailComponent = DaggerDetailsScreenComponent
        .builder()
        .deps(DetailsScreenProvider.deps)
        .build()
}