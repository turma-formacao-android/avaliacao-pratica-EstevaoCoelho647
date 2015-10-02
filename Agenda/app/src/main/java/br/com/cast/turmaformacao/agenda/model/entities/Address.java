package br.com.cast.turmaformacao.agenda.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address implements Parcelable {
    @JsonProperty("cep")
    private String zipCode;
    @JsonProperty("tipoDeLogradouro")
    private String type;
    @JsonProperty("logradouro")
    private String street;
    @JsonProperty("bairro")
    private String neighborhood;
    @JsonProperty("cidade")
    private String city;
    @JsonProperty("estado")
    private String state;

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", type='" + type + '\'' +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null)
            return false;
        if (type != null ? !type.equals(address.type) : address.type != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (neighborhood != null ? !neighborhood.equals(address.neighborhood) : address.neighborhood != null)
            return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        return !(state != null ? !state.equals(address.state) : address.state != null);

    }

    @Override
    public int hashCode() {
        int result = zipCode != null ? zipCode.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (neighborhood != null ? neighborhood.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.zipCode == null ? "" : this.zipCode);
        dest.writeString(this.type == null ? "" : this.type);
        dest.writeString(this.street == null ? "" : this.street);
        dest.writeString(this.neighborhood == null ? "" : this.neighborhood);
        dest.writeString(this.city == null ? "" : this.city);
        dest.writeString(this.state == null ? "" : this.state);
    }

    protected Address(Parcel in) {
        this.zipCode = in.readString();
        this.type = in.readString();
        this.street = in.readString();
        this.neighborhood = in.readString();
        this.city = in.readString();
        this.state = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}