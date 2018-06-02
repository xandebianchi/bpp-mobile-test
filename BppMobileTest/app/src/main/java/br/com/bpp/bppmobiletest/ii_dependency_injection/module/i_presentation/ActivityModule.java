package br.com.bpp.bppmobiletest.ii_dependency_injection.module.i_presentation;

import br.com.bpp.bppmobiletest.i_layers.i_presentation.view.activities.LoginActivity;
import br.com.bpp.bppmobiletest.i_layers.i_presentation.view.activities.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
