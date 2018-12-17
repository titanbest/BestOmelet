@file:Suppress("unused")

package com.titan.bestomelet.di.module

import com.titan.bestomelet.view.activities.MainActivity
import com.titan.bestomelet.di.annotation.PerActivity
import com.titan.bestomelet.view.activities.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [(AndroidSupportInjectionModule::class), (FragmentModule::class)])
interface ActivityModule {

    @ContributesAndroidInjector
    @PerActivity
    fun splashActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    @PerActivity
    fun mainActivity(): MainActivity
}