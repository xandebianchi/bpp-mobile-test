package br.com.bpp.bppmobiletest.i_layers.i_presentation.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Invoice;
import br.com.bpp.bppmobiletest.i_layers.iii_data.InvoiceRepository;

public class MainViewModel extends ViewModel {
    private LiveData<List<Invoice>> invoices;
    private InvoiceRepository invoiceRepository;

    @Inject
    MainViewModel(InvoiceRepository mInvoiceRepository) {
        invoiceRepository = mInvoiceRepository;

        invoices = invoiceRepository.getInvoice();
    }

    public LiveData<List<Invoice>> getInvoices() {
        return invoices;
    }

    public void atualizaInvoices() {
        invoiceRepository.doSearchInvoices();
    }
}
