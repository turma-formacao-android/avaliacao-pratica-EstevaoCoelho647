package br.com.cast.turmaformacao.agenda.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Contact implements Parcelable {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String redeSocial;
    private Adress adress;

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", redeSocial='" + redeSocial + '\'' +
                ", adress=" + adress +
                '}';
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
        return !(adress != null ? !adress.equals(contact.adress) : contact.adress != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (redeSocial != null ? redeSocial.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id == null ? -1 : this.id);
        dest.writeString(this.nome == null ? "" : this.nome);
        dest.writeString(this.telefone== null ? "" : this.telefone);
        dest.writeString(this.email== null ? "" : this.email);
        dest.writeString(this.redeSocial== null ? "" : this.redeSocial);
        dest.writeParcelable((Parcelable) this.adress, flags);
    }

    protected Contact(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.nome = in.readString();
        this.telefone = in.readString();
        this.email = in.readString();
        this.redeSocial = in.readString();
        this.adress = in.readParcelable(Adress.class.getClassLoader());
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
