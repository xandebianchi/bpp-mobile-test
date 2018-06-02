package br.com.bpp.bppmobiletest.i_layers.i_presentation.view.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import br.com.bpp.bppmobiletest.R;
import br.com.bpp.bppmobiletest.i_layers.i_presentation.viewmodel.MainViewModel;
import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.i_adapters.InvoiceAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    // ===============
    // MVVM/AAC FIELDS
    // ===============

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainViewModel viewModel;

    // ===================
    // PRESENTATION FIELDS
    // ===================

    @BindView(R.id.rcvInvoice)
    RecyclerView rcvInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        // =================
        // PRESENTATION CODE
        // =================

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager ltmProdutosComanda = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item_decoration));
        rcvInvoice.setHasFixedSize(true);
        rcvInvoice.setLayoutManager(ltmProdutosComanda);
        rcvInvoice.addItemDecoration(dividerItemDecoration);

        InvoiceAdapter adpInvoiceAdapter = new InvoiceAdapter();
        rcvInvoice.setAdapter(adpInvoiceAdapter);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        viewModel.getInvoices().observe(this, invoices -> {
            if (invoices != null) {
                adpInvoiceAdapter.atualizaInvoices(invoices);
            }
        });

        viewModel.atualizaInvoices();
    }
}