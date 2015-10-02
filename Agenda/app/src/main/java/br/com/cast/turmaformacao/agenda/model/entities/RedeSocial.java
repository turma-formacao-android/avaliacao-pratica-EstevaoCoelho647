package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 02/10/2015.
 */
public class RedeSocial implements Parcelable {
    private Long id;
    private String redeSocial;
    private Long idContact;

    public RedeSocial() {
    }

    public String getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.redeSocial== null ? "" : this.redeSocial);
        dest.writeValue(this.idContact== null ? -1 : this.idContact);
    }

    protected RedeSocial(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.redeSocial = in.readString();
        this.idContact = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<RedeSocial> CREATOR = new Creator<RedeSocial>() {
        public RedeSocial createFromParcel(Parcel source) {
            return new RedeSocial(source);
        }

        public RedeSocial[] newArray(int size) {
            return new RedeSocial[size];
        }
    };
}
