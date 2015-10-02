package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.RedeSocial;

/**
 * Created by Administrador on 02/10/2015.
 */
public class RedeSocialContract {
    public static String TABLE = "redeSocial";
    public static String ID = "id";
    public static String REDESOCIAL = "redeSocial";
    public static String IDCONTATO = "idContato";


    public static final String[] COLUNS = {ID, REDESOCIAL,IDCONTATO};

    private RedeSocialContract() {
    }

    public static String getCreateTableScript() {


        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(REDESOCIAL + " TEXT,");
        create.append(IDCONTATO + " INTEGER NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(RedeSocial redeSocial) {
        ContentValues values = new ContentValues();
        values.put(RedeSocialContract.ID, redeSocial.getId());
        values.put(RedeSocialContract.REDESOCIAL, redeSocial.getRedeSocial());
        values.put(RedeSocialContract.IDCONTATO, redeSocial.getIdContact());
        return values;
    }

    private static RedeSocial getRedeSocial(Cursor cursor) {
        RedeSocial redeSocial = new RedeSocial();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            redeSocial.setId(cursor.getLong(cursor.getColumnIndex(RedeSocialContract.ID)));
            redeSocial.setRedeSocial(cursor.getString(cursor.getColumnIndex(RedeSocialContract.REDESOCIAL)));
            redeSocial.setIdContact(cursor.getLong(cursor.getColumnIndex(RedeSocialContract.IDCONTATO)));

            return redeSocial;
        }
        return null;
    }
    public static List getRedeSociais(Cursor cursor) {
        ArrayList<RedeSocial> redeSocials = new ArrayList();
        while (cursor.moveToNext()) {
            redeSocials.add(getRedeSocial(cursor));
        }
        return redeSocials;
    }
    public static ArrayList<RedeSocial> getRedeSocialById(Cursor cursor, Long id) {
        ArrayList<RedeSocial> values = new ArrayList<>();
        while(cursor.moveToNext()){
            if (cursor.getLong(cursor.getColumnIndex(IDCONTATO)) == id){
                values.add(getRedeSocial(cursor));
            }
        }
        return values;
    }
}
