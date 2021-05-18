package com.prizo.ecommercecli.domains;

import java.util.StringJoiner;
import java.util.UUID;

/**
 * Objeto para o domínio Address
 *
 * @author Gabriela Prizo
 */
public class Address
{
    protected String additionalData;
    protected UUID addressId;
    protected String neighborhood;
    protected String city;
    protected String state;
    protected String country;
    protected String number;
    protected String references;
    protected String street;
    protected String zipcode;

    public Address() {
        this.addressId = UUID.randomUUID();
    }

    public UUID getAddressId() {
        return this.addressId;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street.trim();
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number.trim();
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood.trim();
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state.trim();
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country.trim();
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city.trim();
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode.trim();
    }

    public String getAdditionalData() {
        return this.additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData.trim();
    }

    public String getReferences() {
        return this.references;
    }

    public void setReferences(String references) {
        this.references = references.trim();
    }

    @Override
    public String toString() {
        StringJoiner string = new StringJoiner(", ");

        if(this.getStreet() != null) {
            string.add(this.getStreet());
        }

        if(this.getNumber() != null) {
            string.add(this.getNumber());
        }

        if(this.getAdditionalData() != null) {
            string.add("Complemento: " + this.getAdditionalData());
        }

        if(this.getZipcode() != null) {
            string.add("CEP: " + this.getZipcode());
        }

        if(this.getNeighborhood() != null) {
            string.add("Bairro: " + this.getNeighborhood());
        }

        if(this.getCity() != null) {
            string.add("Cidade: " + this.getCity());
        }

        if(this.getState() != null) {
            string.add("Estado: " + this.getState());
        }

        if(this.getCountry() != null) {
            string.add("País: " + this.getCountry());
        }

        if(this.getReferences() != null) {
            string.add("Referências: " + this.getReferences());
        }

        return string.toString();
    }
}