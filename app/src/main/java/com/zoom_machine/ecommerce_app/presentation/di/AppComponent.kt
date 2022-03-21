package com.zoom_machine.l_tech_app.di

import android.content.Context
import com.zoom_machine.l_tech_app.MainActivity
import com.zoom_machine.l_tech_app.networking.Api
import com.zoom_machine.l_tech_app.networking.OkHttpClient
import com.zoom_machine.l_tech_app.ui.authorization.AuthorizationFragment
import com.zoom_machine.l_tech_app.ui.dev_exam.DevExamDetailFragment
import com.zoom_machine.l_tech_app.ui.dev_exam.DevExamFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkingModule::class, BindRepositoryModule::class],
    dependencies = [OkHttpClient::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun okHttp(okHttp: OkHttpClient): Builder
        fun build(): AppComponent
    }

    fun providesNetworkingApi(): Api
    fun injectToAuthorizationFragment(fragment: AuthorizationFragment)
    fun injectToDevExamFragment(fragment: DevExamFragment)
    fun injectToDevExamDetailFragment(fragment: DevExamDetailFragment)
    fun injectToMainActivity(activity: MainActivity)
}
