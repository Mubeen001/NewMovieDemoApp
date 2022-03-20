package com.example.newmoviedemoapp.diModules

import com.example.myapplication.app.App
import com.example.newmoviedemoapp.api.RetrofitClient
import com.example.newmoviedemoapp.repository.MainRepository
import com.example.newmoviedemoapp.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class apModule {

    fun getModule(app: App): Module {
        return module {
            single { RetrofitClient() }
            single { MainRepository(get()) }
            viewModel { MainActivityViewModel(get()) }
        }
    }

}