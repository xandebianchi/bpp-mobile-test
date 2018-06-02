package br.com.bpp.bppmobiletest.i_layers.ii_domain.model.i_adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import br.com.bpp.bppmobiletest.R;
import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Invoice;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceHolder> {

    private ArrayList<Invoice> lstInvoices;

    public class InvoiceHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTransactionId) AppCompatTextView txtTransactionId;
        @BindView(R.id.txtDateHour) AppCompatTextView txtDateHour;
        @BindView(R.id.txtTransactionAmmount) AppCompatTextView txtTransactionAmmount;
        @BindView(R.id.txtBillingAmmount) AppCompatTextView txtBillingAmmount;
        @BindView(R.id.txtStatus) AppCompatTextView txtStatus;
        @BindView(R.id.txtTransactionName) AppCompatTextView txtTransactionName;
        @BindView(R.id.txtMerchantName) AppCompatTextView txtMerchantName;
        @BindView(R.id.txtMCCCode) AppCompatTextView txtMCCCode;
        @BindView(R.id.txtMCCDescription) AppCompatTextView txtMCCDescription;

        InvoiceHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public InvoiceAdapter() {
        lstInvoices = new ArrayList<>();
    }

    @NonNull
    @Override
    public InvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_layout_tela_invoice_linha_item_invoice, parent, false);

        return new InvoiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InvoiceHolder holder, final int listPosition) {
        holder.txtTransactionId.setText(
                String.format("ID: %s",
                        lstInvoices.get(listPosition).getTransactionId()
                )
        );
        holder.txtDateHour.setText(
                String.format("Date/Hour: %s",
                        DateTimeFormat.forPattern("dd/MM/yyyy hh:mm")
                                .print(
                                        new DateTime(lstInvoices.get(listPosition).getTransactionFormattedDate())
                                                .toInstant()
                                )
                )
        );
        holder.txtMerchantName.setText(
                String.format("Local: %s",
                        lstInvoices.get(listPosition).getMerchantName()
                )
        );
        holder.txtStatus.setText(
                String.format("Status: %s",
                        lstInvoices.get(listPosition).getTransactionStatus()
                )
        );
        holder.txtTransactionAmmount.setText(
                String.format("Transaction ammount: %s %s",
                        lstInvoices.get(listPosition).getTransactionAmount().toString(),
                        lstInvoices.get(listPosition).getTransactionCurrency()
                )
        );
        holder.txtBillingAmmount.setText(
                String.format("Billing ammount: %s %s",
                        lstInvoices.get(listPosition).getBillingAmount().toString(),
                        lstInvoices.get(listPosition).getBillingCurrency()
                )
        );
        holder.txtTransactionName.setText(
                String.format("Name: %s",
                        lstInvoices.get(listPosition).getTransactionName()
                )
        );
        holder.txtMCCCode.setText(
                String.format("MCC Code: %s",
                        lstInvoices.get(listPosition).getMccCode()
                )
        );
        holder.txtMCCDescription.setText(
                String.format("MCC Description: %s",
                        lstInvoices.get(listPosition).getMccDescription()
                )
        );
    }

    @Override
    public int getItemCount() {
        return lstInvoices.size();
    }

    public void atualizaInvoices(List<Invoice> invoices) {
        lstInvoices.addAll(invoices);
        notifyDataSetChanged();
    }
}
