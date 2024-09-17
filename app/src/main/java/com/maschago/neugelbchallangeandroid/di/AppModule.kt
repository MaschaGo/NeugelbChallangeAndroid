package com.maschago.neugelbchallangeandroid.di

import com.maschago.neugelbchallangeandroid.data.dataModule
import com.maschago.neugelbchallangeandroid.domain.domainModule
import com.maschago.neugelbchallangeandroid.presentation.viewModelModule
import org.koin.dsl.module


val provideAppModules = module {
    includes(
        viewModelModule,
        domainModule,
        dataModule,
    )
}