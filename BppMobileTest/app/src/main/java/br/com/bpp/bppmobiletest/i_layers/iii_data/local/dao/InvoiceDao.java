package br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Invoice;

@Dao
public interface InvoiceDao {
    @Query("SELECT * FROM invoice")
    LiveData<List<Invoice>> getInvoice();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Invoice invoice);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Invoice> invoices);

    @Delete
    void delete(Invoice invoice);

    @Query("DELETE FROM invoice")
    void deleteAll();
}