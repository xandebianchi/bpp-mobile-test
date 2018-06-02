package br.com.bpp.bppmobiletest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import br.com.bpp.bppmobiletest.ii_dependency_injection.component.DaggerAppComponent;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {
    private static App mApp;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger(); // Para implementação dagger

        mApp = this;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static Context getContext(){
        return mApp;
    }

    private void initDagger(){
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }
}
