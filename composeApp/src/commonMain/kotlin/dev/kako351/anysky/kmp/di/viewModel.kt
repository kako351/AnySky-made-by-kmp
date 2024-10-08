package dev.kako351.anysky.kmp.di

import androidx.lifecycle.viewmodel.compose.viewModel
import dev.kako351.anysky.kmp.MainViewModel
import dev.kako351.anysky.kmp.ui.FeedsViewModel
import dev.kako351.anysky.kmp.ui.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { FeedsViewModel(get()) }
}