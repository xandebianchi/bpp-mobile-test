package br.com.bpp.bppmobiletest.ii_dependency_injection.component;

import android.app.Application;

import javax.inject.Singleton;

import br.com.bpp.bppmobiletest.App;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.i_presentation.ActivityModule;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.ii_data.RepositoryModel;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.ii_data.local.InMemoryModel;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.ii_data.remote.InterfaceRetrofitServiceModule;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.iii_networking.ServiceModule;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.iv_app.AppModule;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        ActivityModule.class,
        AppModule.class,
        ServiceModule.class,
        InterfaceRetrofitServiceModule.class,
        InMemoryModel.class,
        RepositoryModel.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
