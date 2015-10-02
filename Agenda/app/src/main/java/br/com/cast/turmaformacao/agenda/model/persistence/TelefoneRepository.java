package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Telefone;


/**
 * Created by Administrador on 02/10/2015.
 */
public class TelefoneRepository {
    private TelefoneRepository() {
    }

    public static void save(Telefone telefone) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = TelefoneContract.getContentValues(telefone);

        if (telefone.getId() == null) {
            db.insert(TelefoneContract.TABLE, null, values);
        } else {
            String where = TelefoneContract.ID + " = ?";
            String[] params = {telefone.getId().toString()};
            db.update(TelefoneContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }


    public static List<Telefone> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(TelefoneContract.TABLE, TelefoneContract.COLUNS, null, null, null, null, TelefoneContract.ID);
        List<Telefone> values = TelefoneContract.getTelefones(cursor);


        db.close();
        databaseHelper.close();
        return values;
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = TelefoneContract.IDCONTATO + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(TelefoneContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static ArrayList<Telefone> getById(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(TelefoneContract.TABLE, TelefoneContract.COLUNS, null, null, null, null, TelefoneContract.ID);
        ArrayList<Telefone> values = new ArrayList<>(TelefoneContract.getTelefoneById(cursor, id));


        db.close();
        databaseHelper.close();
        return values;
    }

}
