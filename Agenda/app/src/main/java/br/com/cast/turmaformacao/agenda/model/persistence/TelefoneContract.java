package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Telefone;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;

/**
 * Created by Administrador on 02/10/2015.
 */
public class TelefoneContract {
    public static String TABLE = "telefone";
    public static String ID = "id";
    public static String TELEFONE = "telefone";
    public static String IDCONTATO = "idContato";


    public static final String[] COLUNS = {ID, TELEFONE,IDCONTATO};

    private TelefoneContract() {
    }

    public static String getCreateTableScript() {


        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(TELEFONE + " TEXT, ");
        create.append(IDCONTATO + " INTEGER NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Telefone telefone) {
        ContentValues values = new ContentValues();
        values.put(TelefoneContract.ID, telefone.getId());
        values.put(TelefoneContract.TELEFONE, telefone.getTelefone());
        values.put(TelefoneContract.IDCONTATO, telefone.getIdContact());
        return values;
    }

    private static Telefone getTelefone(Cursor cursor) {
        Telefone telefone = new Telefone();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            telefone.setId(cursor.getLong(cursor.getColumnIndex(TelefoneContract.ID)));
            telefone.setTelefone(cursor.getString(cursor.getColumnIndex(TelefoneContract.TELEFONE)));
            telefone.setIdContact(cursor.getLong(cursor.getColumnIndex(TelefoneContract.IDCONTATO)));

            return telefone;
        }
        return null;
    }

    public static List getTelefones(Cursor cursor) {
        ArrayList<Telefone> telefones = new ArrayList();
        while (cursor.moveToNext()) {
            telefones.add(getTelefone(cursor));
        }
        return telefones;
    }
    public static ArrayList<Telefone> getTelefoneById(Cursor cursor, Long id) {
        ArrayList<Telefone> values = new ArrayList<>();
        while(cursor.moveToNext()){
            if (cursor.getLong(cursor.getColumnIndex(IDCONTATO)) == id){
                values.add(getTelefone(cursor));
            }
        }
        return values;
    }
    
}
