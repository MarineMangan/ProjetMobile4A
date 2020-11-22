package com.example.projetmobile4a.injection

import com.example.projetmobile4a.MainViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { MainViewModel() }
}