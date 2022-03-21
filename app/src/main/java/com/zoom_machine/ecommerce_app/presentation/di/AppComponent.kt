package com.zoom_machine.ecommerce_app.presentation.di

import com.zoom_machine.ecommerce_app.presentation.ui.MainScreenFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BindRepositoryModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

    fun injectMainScreenFragment(fragment: MainScreenFragment)
}
