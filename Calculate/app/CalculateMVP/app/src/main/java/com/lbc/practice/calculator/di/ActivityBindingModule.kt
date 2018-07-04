package com.lbc.practice.calculator.di

import com.lbc.practice.calculator.view.claculator.CalculatorActivity
import com.lbc.practice.calculator.view.claculator.CalculatorModule
import com.lbc.practice.calculator.view.problemSolving.ProblemSolvingActivity
import com.lbc.practice.calculator.view.problemSolving.ProblemSolvingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(ProblemSolvingModule::class))
    abstract fun problemSolvingActivity() : ProblemSolvingActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(CalculatorModule::class))
    abstract fun calculatoActivity() : CalculatorActivity
}