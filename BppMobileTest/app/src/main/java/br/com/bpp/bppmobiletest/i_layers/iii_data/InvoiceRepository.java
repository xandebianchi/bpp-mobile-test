package br.com.bpp.bppmobiletest.i_layers.iii_data;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Invoice;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao.InvoiceDao;
import br.com.bpp.bppmobiletest.i_layers.iii_data.remote.InvoiceService;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class InvoiceRepository {
    private final InvoiceService invoiceService;
    private final InvoiceDao invoiceDao;
    private final Executor executor;

    @Inject
    public InvoiceRepository(InvoiceService mInvoiceService,
                             InvoiceDao mInvoiceDao,
                             Executor mExecutor) {
        invoiceService = mInvoiceService;
        invoiceDao = mInvoiceDao;
        executor = mExecutor;
    }

    public LiveData<List<Invoice>> getInvoice() {
        return invoiceDao.getInvoice();
    }

    @SuppressLint("CheckResult")
    public void doSearchInvoices() {
        Single<JsonArray> single = invoiceService.getFrom();

        single.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(new SingleObserver<JsonArray>() {
                  @Override
                  public void onSubscribe(Disposable d) {}

                  @Override
                  public void onSuccess(JsonArray result) {
                      try {
                          ArrayList<Invoice> lstInvoices = new ArrayList<>();

                          int invoiceListSize = result.size();

                          Invoice invoice;
                          for (int indice = 0; indice < invoiceListSize; indice++) {
                              JsonObject jsonObject = result.get(indice).getAsJsonObject();

                              invoice = new Invoice(
                                      jsonObject.get("transactionId").getAsString(),
                                      jsonObject.get("transactionDate").getAsString(),
                                      jsonObject.get("transactionFormattedDate").getAsString(),
                                      jsonObject.get("transactionCurrency").getAsString(),
                                      jsonObject.get("transactionAmount").getAsFloat(),
                                      jsonObject.get("billingCurrency").getAsString(),
                                      jsonObject.get("billingAmount").getAsFloat(),
                                      jsonObject.get("transactionStatus").getAsString(),
                                      jsonObject.get("transactionName").getAsString(),
                                      jsonObject.get("merchantName").getAsString(),
                                      jsonObject.get("mccCode").getAsString(),
                                      jsonObject.get("mccDescription").getAsString()
                              );

                              lstInvoices.add(invoice);
                          }

                          executor.execute(() -> invoiceDao.insert(lstInvoices));
                      } catch (Throwable t) {
                          // TODO ERROR malformed JSON
                      }
                  }

                  @Override
                  public void onError(Throwable e) {
                      executor.execute(invoiceDao::deleteAll);
                  }
              });
    }
}
