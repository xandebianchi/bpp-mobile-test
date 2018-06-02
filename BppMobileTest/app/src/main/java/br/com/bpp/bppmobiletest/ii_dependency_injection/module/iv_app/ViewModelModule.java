package br.com.bpp.bppmobiletest.ii_dependency_injection.module.iv_app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import br.com.bpp.bppmobiletest.i_layers.i_presentation.viewmodel.FactoryViewModel;
import br.com.bpp.bppmobiletest.i_layers.i_presentation.viewmodel.LoginViewModel;
import br.com.bpp.bppmobiletest.i_layers.i_presentation.viewmodel.MainViewModel;
import br.com.bpp.bppmobiletest.ii_dependency_injection.key.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
