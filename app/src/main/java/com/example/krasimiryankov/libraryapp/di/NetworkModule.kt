package com.example.krasimiryankov.libraryapp.di

import com.example.krasimiryankov.libraryapp.data.BookConst
import com.example.krasimiryankov.libraryapp.data.network.BookApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    internal fun provideBookApi(retrofit: Retrofit): BookApi {
        return retrofit.create(BookApi::class.java)
    }

    @Provides
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BookConst.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

}