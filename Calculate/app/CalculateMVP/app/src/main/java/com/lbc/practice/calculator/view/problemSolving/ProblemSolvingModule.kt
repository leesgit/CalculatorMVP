package com.lbc.practice.calculator.view.problemSolving

import com.lbc.practice.calculator.di.ActivityScope
import dagger.Binds
import dagger.Module


@Module
abstract class ProblemSolvingModule {
    @ActivityScope
    @Binds
    abstract fun solvingProvider(problemSolvingPresenter: ProblemSolvingPresenter): ProblemSolvingContract.ProblemPresenter
}
