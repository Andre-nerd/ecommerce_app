@file:Suppress("unused")

package com.zoom_machine.feature_cartscreen.presentation.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.zoom_machine.api.services.CartScreenService
import com.zoom_machine.core.FeatureCart
import com.zoom_machine.database.cart_model.CartScreenDao
import com.zoom_machine.feature_cartscreen.data.CartRepositoryImpl
import com.zoom_machine.feature_cartscreen.data.SharedPrefCartScreen
import com.zoom_machine.feature_cartscreen.domain.CartRepository
import com.zoom_machine.feature_cartscreen.presentation.ui.CartFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import kotlin.properties.Delegates.notNull


@[FeatureCart Component(
    modules = [BindRepository::class],
    dependencies = [CartScreenDeps::class]
)]
internal interface CartScreenComponent {
    fun inject(fragment: CartFragment)

    @Component.Builder
    interface Builder {
        fun deps(mainScreenDeps: CartScreenDeps): Builder
        fun build(): CartScreenComponent
    }
}

@Module
interface BindRepository {
    @Binds
    fun bindRepository(repositoryImpl: CartRepositoryImpl): CartRepository
}

interface CartScreenDeps {
    val cartScreenService: CartScreenService
    val cartScreenDao: CartScreenDao
    val sharedPrefCartScreen: SharedPrefCartScreen
}


interface CartScreenProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: CartScreenDeps

    companion object : CartScreenProvider by CartScreenDepsStore
}

object CartScreenDepsStore : CartScreenProvider {
    override var deps: CartScreenDeps by notNull()
}

internal class CartScreenComponentViewModel : ViewModel() {
    val newDetailComponent = DaggerCartScreenComponent
        .builder()
        .deps(CartScreenProvider.deps)
        .build()
}
