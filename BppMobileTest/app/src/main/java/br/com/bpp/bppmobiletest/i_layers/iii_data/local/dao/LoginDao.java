package br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Login;

@Dao
public interface LoginDao {
    @Query("SELECT * FROM login LIMIT 1")
    LiveData<Login> getLogin();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Login usuarioLogin);

    @Delete
    void delete(Login login);

    @Query("DELETE FROM login")
    void deleteAll();
}