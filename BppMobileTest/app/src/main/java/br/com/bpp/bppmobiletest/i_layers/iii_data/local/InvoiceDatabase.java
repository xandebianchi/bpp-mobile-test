package br.com.bpp.bppmobiletest.i_layers.iii_data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Invoice;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao.InvoiceDao;

@Database(entities = {Invoice.class}, version = 1)
public abstract class InvoiceDatabase extends RoomDatabase {

    // ===========
    // --- DAO ---
    // ===========
    public abstract InvoiceDao invoiceDao();
}