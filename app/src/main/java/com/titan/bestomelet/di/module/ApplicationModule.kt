package com.titan.bestomelet.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.titan.bestomelet.BuildConfig
import com.titan.data.db.AppDatabase
import com.titan.data.executor.JobExecutor
import com.titan.data.repository.BASE_URL
import com.titan.data.repository.DBRoomStorage
import com.titan.data.repository.ServerApi
import com.titan.data.repository.ServerStorage
import com.titan.data.utils.DataBaseUtils
import com.titan.domain.executor.PostExecutionThread
import com.titan.domain.executor.ThreadExecutor
import com.titan.domain.repository.DBRoomRepository
import com.titan.domain.repository.ServerRepository
import com.titan.bestomelet.OmeletApp
import com.titan.bestomelet.di.annotation.PerApplication
import com.titan.bestomelet.utils.UIThread
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideApplicationContext(application: OmeletApp): Context = application

    @Provides
    @PerApplication
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @PerApplication
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides
    @PerApplication
    fun provideGSon(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    @Provides
    @PerApplication
    fun okHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @PerApplication
    fun provideRetrofit(gSon: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gSon))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @PerApplication
    fun provideDatabaseRoom(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DataBaseUtils.DB_ROOM).build()
    }

    @Provides
    @PerApplication
    fun provideServerApi(retrofit: Retrofit): ServerApi = retrofit.create(ServerApi::class.java)

    @Provides
    @PerApplication
    fun provideApiRepository(storage: ServerStorage): ServerRepository = storage

    @Provides
    @PerApplication
    fun provideDBRoomRepository(storage: DBRoomStorage): DBRoomRepository = storage
}