package br.com.bpp.bppmobiletest.ii_dependency_injection.module.iv_app;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton
    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }
}