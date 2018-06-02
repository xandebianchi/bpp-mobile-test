package br.com.bpp.bppmobiletest.i_layers.iii_data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.bpp.bppmobiletest.i_layers.ii_domain.model.Login;
import br.com.bpp.bppmobiletest.i_layers.iii_data.local.dao.LoginDao;

@Database(entities = {Login.class}, version = 1)
public abstract class LoginDatabase extends RoomDatabase {

    // ===========
    // --- DAO ---
    // ===========
    public abstract LoginDao loginDao();
}