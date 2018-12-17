package com.titan.bestomelet.di

import com.titan.bestomelet.OmeletApp
import com.titan.bestomelet.di.annotation.PerApplication
import com.titan.bestomelet.di.module.CoreModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [(CoreModule::class), (AndroidSupportInjectionModule::class)])
interface ApplicationComponent : AndroidInjector<OmeletApp> {
    @Component.Builder abstract class Builder : AndroidInjector.Builder<OmeletApp>()
}