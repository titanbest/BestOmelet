package com.titan.bestomelet.di.module

import com.titan.bestomelet.di.module.viewmodel.ViewModelModule
import com.titan.bestomelet.di.module.ApplicationModule
import com.titan.bestomelet.di.module.FragmentModule
import dagger.Module

@Module(includes = [
    (ApplicationModule::class),
    (ViewModelModule::class),
    (ActivityModule::class),
    (FragmentModule::class)
])
interface CoreModule