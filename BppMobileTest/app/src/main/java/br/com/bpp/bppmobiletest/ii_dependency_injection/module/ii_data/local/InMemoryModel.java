package br.com.bpp.bppmobiletest.ii_dependency_injection.module.ii_data.local;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import br.com.bpp.bppmobiletest.i_layers.iii_data.local.InvoiceDatabase;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.LoginDatabase;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao.InvoiceDao;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao.LoginDao;
import dagger.Module;
import dagger.Provides;

@Module
public class InMemoryModel {

    @Singleton
    @Provides
    LoginDatabase provideLoginDatabase(Application application) {
        return Room.inMemoryDatabaseBuilder(application, LoginDatabase.class)
                .build();
    }

    @Singleton
    @Provides
    LoginDao provideLoginDao(LoginDatabase loginDatabase) {
        return loginDatabase.loginDao();
    }

    @Singleton
    @Provides
    InvoiceDatabase provideInvoiceDatabase(Application application) {
        return Room.inMemoryDatabaseBuilder(application, InvoiceDatabase.class)
                .build();
    }

    @Singleton
    @Provides
    InvoiceDao provideInvoiceDao(InvoiceDatabase invoiceDatabase) {
        return invoiceDatabase.invoiceDao();
    }
}
