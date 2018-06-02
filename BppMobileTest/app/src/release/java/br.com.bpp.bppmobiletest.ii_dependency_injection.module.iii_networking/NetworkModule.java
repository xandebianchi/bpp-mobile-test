package br.com.bpp.bppmobiletest.ii_dependency_injection.module.iii_networking;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;

// Métodos colocados como Static pois o módulo é abstrato e tem instâncias de métodos com @Provides. Devem ser estáticos, nesse caso.
@Module
public class NetworkModule {

    @Singleton
    @Provides
    Call.Factory provideOkHttp() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Named("BaseUrl")
    String provideBaseUrl() { // Url de debug igual a url de produção, se for diferente alterar aqui
        return ConstantesGerais.URL_BASE;
    }
}
