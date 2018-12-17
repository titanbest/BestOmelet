package com.titan.bestomelet.di.module

import com.titan.bestomelet.di.annotation.PerFragment
import com.titan.bestomelet.view.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    @PerFragment
    fun mainFragment(): MainFragment
}