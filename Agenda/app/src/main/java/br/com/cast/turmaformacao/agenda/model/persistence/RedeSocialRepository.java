package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.RedeSocial;
import br.com.cast.turmaformacao.agenda.model.entities.RedeSocial;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;

/**
 * Created by Administrador on 02/10/2015.
 */
public class RedeSocialRepository {
    private RedeSocialRepository() {
    }

    public static void save(RedeSocial redeSocial) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = RedeSocialContract.getContentValues(redeSocial);

        if (redeSocial.getId() == null) {
            db.insert(RedeSocialContract.TABLE, null, values);
        } else {
            String where = RedeSocialContract.ID + " = ?";
            String[] params = {redeSocial.getId().toString()};
            db.update(RedeSocialContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }


    public static List<RedeSocial> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(RedeSocialContract.TABLE, RedeSocialContract.COLUNS, null, null, null, null, RedeSocialContract.ID);
        List<RedeSocial> values = RedeSocialContract.getRedeSociais(cursor);


        db.close();
        databaseHelper.close();
        return values;
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = RedeSocialContract.IDCONTATO + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(RedeSocialContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }
    public static ArrayList<RedeSocial> getById(Long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(RedeSocialContract.TABLE, RedeSocialContract.COLUNS, null, null, null, null, RedeSocialContract.ID);
        ArrayList<RedeSocial> values = new ArrayList<>(RedeSocialContract.getRedeSocialById(cursor, id));


        db.close();
        databaseHelper.close();
        return values;
    }
}
