package br.com.bpp.bppmobiletest.ii_dependency_injection.module.iii_networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ServiceModule {

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient() // Aceita certas violações no JSON e tenta o melhor pra poder ler formatos questionáveis de JSON
                .create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Gson gson, Call.Factory callFactory, @Named("BaseUrl") String baseUrl) {
        return new Retrofit.Builder()
                .callFactory(callFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(baseUrl)
                .build();
    }
}
