package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Email;

/**
 * Created by Administrador on 02/10/2015.
 */
public class EmailContract {

    public static String TABLE = "email";
    public static String ID = "id";
    public static String EMAIL = "email";
    public static String IDCONTATO = "idContato";


    public static final String[] COLUNS = {ID, EMAIL,IDCONTATO};

    private EmailContract() {
    }

    public static String getCreateTableScript() {


        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(EMAIL + " TEXT,");
        create.append(IDCONTATO + " INTEGER NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Email email) {
        ContentValues values = new ContentValues();
        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.EMAIL, email.getEmail());
        values.put(EmailContract.IDCONTATO, email.getIdContact());
        return values;
    }

    private static Email getEmail(Cursor cursor) {
        Email email = new Email();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            email.setId(cursor.getLong(cursor.getColumnIndex(EmailContract.ID)));
            email.setEmail(cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL)));
            email.setIdContact(cursor.getLong(cursor.getColumnIndex(EmailContract.IDCONTATO)));

            return email;
        }
        return null;
    }

    public static List getEmails(Cursor cursor) {
        ArrayList<Email> emails = new ArrayList();
        while (cursor.moveToNext()) {
            emails.add(getEmail(cursor));
        }
        return emails;
    }

    public static ArrayList<Email> getEmailById(Cursor cursor, Long id) {
        ArrayList<Email> values = new ArrayList<>();
        while(cursor.moveToNext()){
            if (cursor.getLong(cursor.getColumnIndex(IDCONTATO)) == id){
               values.add(getEmail(cursor));
            }
        }
        return values;
    }
}
