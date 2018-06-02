package br.com.bpp.bppmobiletest.ii_dependency_injection.module.ii_data.remote;

import javax.inject.Singleton;

import br.com.bpp.bppmobiletest.i_layers.iii_data.remote.InvoiceService;
import br.com.bpp.bppmobiletest.i_layers.iii_data.remote.LoginService;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.iii_networking.ServiceModule;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = ServiceModule.class)
public class InterfaceRetrofitServiceModule {

    @Singleton
    @Provides
    LoginService provideLoginService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }

    @Singleton
    @Provides
    InvoiceService provideInvoiceService(Retrofit retrofit) {
        return retrofit.create(InvoiceService.class);
    }
}
