package com.titan.bestomelet.extention

import android.content.Context
import com.titan.data.executor.JobExecutor
import com.titan.domain.mapper.OutputMapper
import com.titan.bestomelet.viewmodel.BaseViewModel
import com.titan.domain.interactor.UseCase
import com.titan.bestomelet.exception.ErrorMessageFactory
import com.titan.bestomelet.utils.IDisposable
import com.titan.bestomelet.utils.UIThread
import io.reactivex.Single
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class MappedFlow<
        TParamsType,
        TReturnType,
        TUiModel,
        TMapper : OutputMapper<TReturnType, TUiModel>,
        TUseCase : UseCase<TReturnType, TParamsType>
        > @Inject constructor() : IDisposable {

    @Inject lateinit var useCase: TUseCase
    @Inject lateinit var mapper: TMapper
    @Inject lateinit var context: Context
    @Inject lateinit var uiThread: UIThread
    @Inject lateinit var executor: JobExecutor

    fun run(params: TParamsType, viewModel: BaseViewModel? = null, callback: ((result: TUiModel) -> Unit)? = null) {
        viewModel?.loading?.value = true
        useCase.execute(object : DisposableObserver<TReturnType>() {

            override fun onNext(t: TReturnType) {
                Single.fromCallable {
                    mapper.transformFromDomain(t)
                }.subscribeOn(Schedulers.from(executor))
                        .observeOn(uiThread.scheduler)
                        .subscribe { it: TUiModel ->
                            callback?.invoke(it)
                            viewModel?.loading?.value = false
                        }
            }

            override fun onComplete() {
                viewModel?.error?.value = null
            }

            override fun onError(e: Throwable) {
                viewModel?.loading?.value = false
                viewModel?.error?.value = ErrorMessageFactory.create(context, e)
            }
        }, params)
    }

    override fun dispose() {
        useCase.dispose()
    }

    override fun clear() {
        useCase.clear()
    }
}

class Flow<TParamsType, TReturnType, TUseCase : UseCase<TReturnType, TParamsType>>
@Inject constructor() : MappedFlow<TParamsType, TReturnType, TReturnType, FakeMapper<TReturnType>, TUseCase>()

class FakeMapper<Item>
@Inject constructor() : OutputMapper<Item, Item> {

    override fun transformFromDomain(item: Item): Item {
        return item
    }
}