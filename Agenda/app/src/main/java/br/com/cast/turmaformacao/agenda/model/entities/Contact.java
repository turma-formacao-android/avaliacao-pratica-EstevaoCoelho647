package br.com.cast.turmaformacao.agenda.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Contact implements Parcelable {
    private Long id;
    private String nome;
    private ArrayList<Telefone> telefone;
    private ArrayList<Email> email;
    private ArrayList<RedeSocial> redeSocial;
    private Address address;

    public Contact() {
        this.address = new Address();
        this.telefone = new ArrayList<>();
        this.email = new ArrayList<>();
        this.redeSocial = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (nome != null ? !nome.equals(contact.nome) : contact.nome != null) return false;
        if (telefone != null ? !telefone.equals(contact.telefone) : contact.telefone != null)
            return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (redeSocial != null ? !redeSocial.equals(contact.redeSocial) : contact.redeSocial != null)
            return false;
        return !(address != null ? !address.equals(contact.address) : contact.address != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (redeSocial != null ? redeSocial.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(ArrayList<Telefone> telefone) {
        this.telefone = telefone;
    }

    public ArrayList<Email> getEmail() {
        return email;
    }

    public void setEmail(ArrayList<Email> email) {
        this.email = email;
    }

    public ArrayList<RedeSocial> getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(ArrayList<RedeSocial> redeSocial) {
        this.redeSocial = redeSocial;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.nome == null ? "" : this.nome);
        dest.writeTypedList(telefone);
        dest.writeTypedList(email);
        dest.writeTypedList(redeSocial);
        dest.writeParcelable(this.address, 0);
    }

    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nome = in.readString();
        this.telefone = in.createTypedArrayList(Telefone.CREATOR);
        this.email = in.createTypedArrayList(Email.CREATOR);
        this.redeSocial = in.createTypedArrayList(RedeSocial.CREATOR);
        this.address = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
