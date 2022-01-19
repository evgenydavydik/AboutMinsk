package com.davydikes.aboutminsk

import android.app.Application
import com.davydikes.aboutminsk.cloud.CloudKrokappApi
import com.davydikes.aboutminsk.database.DatabaseConstructor
import com.davydikes.aboutminsk.database.MinskDatabase
import com.davydikes.aboutminsk.datastore.AppSettings
import com.davydikes.aboutminsk.repositories.RepositoryApi
import com.davydikes.aboutminsk.repositories.RepositoryLanguage
import com.davydikes.aboutminsk.repositories.RepositoryPlace
import com.davydikes.aboutminsk.screen.main.ViewModelMain
import com.davydikes.aboutminsk.screen.screen_languages.ViewModelLanguages
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AboutMinsk : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AboutMinsk)
            modules(
                listOf(
                    storageModule,
                    apiModule,
                    viewModels,
                    repositoryModule
                )
            )
        }
    }

    private val viewModels = module {
        viewModel { ViewModelMain(get(), get(), get()) }
        viewModel { ViewModelLanguages(get(), get()) }
    }

    private val repositoryModule = module {
        factory { RepositoryApi(get(), get(), get()) }
        factory { RepositoryPlace(get(), get()) }
        factory { RepositoryLanguage(get(), get()) }
    }

    private val storageModule = module {
        single { DatabaseConstructor.create(get()) }
        factory { get<MinskDatabase>().placesDao() }
        factory { get<MinskDatabase>().languageAppDao() }
        single { AppSettings(get()) }
    }

    private val apiModule = module {
        factory { CloudKrokappApi.get() }
    }
}