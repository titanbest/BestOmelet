package com.titan.bestomelet.utils

import com.titan.domain.executor.PostExecutionThread
import com.titan.bestomelet.di.annotation.PerApplication
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@PerApplication
class UIThread @Inject constructor() : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
