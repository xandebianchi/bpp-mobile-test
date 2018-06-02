package br.com.bpp.bppmobiletest.i_layers.iii_data.remote;

import com.google.gson.JsonArray;

import br.com.bpp.bppmobiletest.iii_utils.ConstantesGerais;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface InvoiceService {
    @GET(ConstantesGerais.CAMINHOURL_INVOICE)
    Single<JsonArray> getFrom();
}
