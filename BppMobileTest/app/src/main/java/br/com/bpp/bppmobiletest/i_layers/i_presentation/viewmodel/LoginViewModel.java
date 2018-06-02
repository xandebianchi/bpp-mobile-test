package br.com.bpp.bppmobiletest.i_layers.i_presentation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Login;
import br.com.bpp.bppmobiletest.i_layers.iii_data.LoginRepository;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    private LiveData<Login> login;
    private LoginRepository loginRepository;

    @Inject
    LoginViewModel(LoginRepository mLoginRepository) {
        loginRepository = mLoginRepository;

        login = loginRepository.getLogin();
    }

    public LiveData<Login> getLogin() {
        return login;
    }

    public void deleteLogin() {
        Observable.just(1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Integer integer) {
                        loginRepository.deleteLogin();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void onButtonLoginClick(String email, String senha) {
        loginRepository.doLogin(email, senha);
    }
}
