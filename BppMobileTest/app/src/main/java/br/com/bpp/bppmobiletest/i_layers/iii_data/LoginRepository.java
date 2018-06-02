package br.com.bpp.bppmobiletest.i_layers.iii_data;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.bpp.bppmobiletest.App;
import br.com.bpp.bppmobiletest.R;
import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Login;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao.LoginDao;
import br.com.bpp.bppmobiletest.i_layers.iii_data.remote.LoginService;
import br.com.bpp.bppmobiletest.iii_utils.Utils;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Singleton
public class LoginRepository {
    private final LoginService loginService;
    private final LoginDao loginDao;
    private final Executor executor;

    @Inject
    public LoginRepository(LoginService mLoginService,
                           LoginDao mLoginDao,
                           Executor mExecutor) {
        loginService = mLoginService;
        loginDao = mLoginDao;
        executor = mExecutor;
    }

    public LiveData<Login> getLogin() {
        return loginDao.getLogin();
    }

    @SuppressLint("CheckResult")
    public void doLogin(String email, String password) {
        Log.v("Debugando", "doLogin: email: " + email);

        String passwordBase64 = Utils.convertToBase64(password);

        Log.v("Debugando", "doLogin: String Base64: " + passwordBase64 + " - " + passwordBase64.length());

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", passwordBase64)
                .build();

        Single<JsonObject> single = loginService.post(
                requestBody
        );

        single.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(new SingleObserver<JsonObject>() {
                  @Override
                  public void onSubscribe(Disposable d) {}

                  @Override
                  public void onSuccess(JsonObject result) {
                      Log.v("Debugando", "onSuccess: " + result);

                      try {
                          JSONObject jsonResult = new JSONObject(result.toString());

                          final Login login;

                          String status = jsonResult.get("status").toString();

                          if (status.equals(App.getContext().getString(R.string.strSuccess))) { // status == success
                              login = new Login(
                                      1, // Sempre grava no localPrivateId 1
                                      email,
                                      status,
                                      "",
                                      jsonResult.getInt("code")
                              );
                          } else { // status == error
                              login = new Login(
                                      1, // Sempre grava no localPrivateId 1
                                      "",
                                      status,
                                      jsonResult.getString("message"),
                                      jsonResult.getInt("code")
                              );
                          }

                          executor.execute(() -> loginDao.insert(login));
                      } catch (Throwable t) {
                          // TODO ERROR malformed JSON
                      }
                  }

                  @Override
                  public void onError(Throwable e) {
                      Log.v("Debugando", "onError: " + e);
                      // Caso não requisitado no exercício
                  }
              });
    }

    public void deleteLogin() {
        loginDao.deleteAll();
    }
}
