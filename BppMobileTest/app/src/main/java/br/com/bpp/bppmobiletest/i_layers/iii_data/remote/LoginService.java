package br.com.bpp.bppmobiletest.i_layers.iii_data.remote;

import com.google.gson.JsonObject;

import br.com.bpp.bppmobiletest.iii_utils.ConstantesGerais;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST(ConstantesGerais.CAMINHOURL_LOGIN)
    Single<JsonObject> post(@Body RequestBody multipartBody);
}
