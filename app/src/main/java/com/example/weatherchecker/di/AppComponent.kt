package com.example.weatherchecker.di

import android.app.Application
import com.example.weatherchecker.app.WeatherChecker
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [AndroidInjectionModule::class, BuildersModule::class, ApiModule::class,
        DatabaseModule::class, RepositoryModule::class]
)
@Singleton
interface AppComponent {
    fun inject(weatherChecker: WeatherChecker)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}