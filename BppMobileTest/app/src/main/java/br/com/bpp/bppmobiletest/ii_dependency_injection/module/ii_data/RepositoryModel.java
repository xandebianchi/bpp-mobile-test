package br.com.bpp.bppmobiletest.ii_dependency_injection.module.ii_data;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import br.com.bpp.bppmobiletest.i_layers.iii_data.InvoiceRepository;
import br.com.bpp.bppmobiletest.i_layers.iii_data.LoginRepository;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao.InvoiceDao;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao.LoginDao;
import br.com.bpp.bppmobiletest.i_layers.iii_data.remote.InvoiceService;
import br.com.bpp.bppmobiletest.i_layers.iii_data.remote.LoginService;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.ii_data.local.InMemoryModel;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.ii_data.remote.InterfaceRetrofitServiceModule;
import br.com.bpp.bppmobiletest.ii_dependency_injection.module.iv_app.AppModule;
import dagger.Module;
import dagger.Provides;

@Module(includes = {
        InterfaceRetrofitServiceModule.class,
        InMemoryModel.class,
        AppModule.class})
public class RepositoryModel {

    @Singleton
    @Provides
    LoginRepository provideLoginRepository(LoginService loginService,
                                           LoginDao loginDao,
                                           Executor executor) {
        return new LoginRepository(loginService, loginDao, executor);
    }

    @Singleton
    @Provides
    InvoiceRepository provideInvoiceRepository(InvoiceService invoiceService,
                                               InvoiceDao invoiceDao,
                                               Executor executor) {
        return new InvoiceRepository(invoiceService, invoiceDao, executor);
    }
}
