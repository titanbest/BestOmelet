package com.titan.domain.interactor

import com.titan.domain.executor.PostExecutionThread
import com.titan.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class UseCase<TReturnType, in TParamsType> internal constructor() {
    @Inject lateinit var threadExecutor: ThreadExecutor
    @Inject lateinit var postExecutionThread: PostExecutionThread

    private var disposables: CompositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCaseObservable(params: TParamsType): Observable<TReturnType>

    fun execute(observer: DisposableObserver<TReturnType>, params: TParamsType) {
        val observable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    fun clear() {
        disposables.clear()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}