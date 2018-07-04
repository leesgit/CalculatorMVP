package com.lbc.practice.calculator.view.claculator

import com.lbc.practice.calculator.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class CalculatorModule {

    @ActivityScope
    @Binds
    abstract fun calPresenterProvier(calculatorPresenter: CalculatorPresenter) : CalculatorContractor.CalculatorPresenter
}