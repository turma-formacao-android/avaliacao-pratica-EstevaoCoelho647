package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 02/10/2015.
 */
public class Telefone implements Parcelable {
    private Long id;
    private String telefone;
    private Long idContact;

    public Telefone() {
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", telefone='" + telefone + '\'' +
                ", idContact=" + idContact +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.telefone == null ? "" : this.telefone);
        dest.writeValue(this.idContact == null ? "" : this.idContact);
    }

    protected Telefone(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.telefone = in.readString();
        this.idContact = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<Telefone> CREATOR = new Parcelable.Creator<Telefone>() {
        public Telefone createFromParcel(Parcel source) {
            return new Telefone(source);
        }

        public Telefone[] newArray(int size) {
            return new Telefone[size];
        }
    };
}
