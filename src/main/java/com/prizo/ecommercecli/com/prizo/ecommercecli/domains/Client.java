package com.prizo.ecommercecli.domains;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * Objeto para o domínio Client
 *
 * @author Gabriela Prizo
 */
public class Client
{
    protected UUID clientId;
    protected String name;
    protected String email;
    protected String cellphone;
    protected String cpfOuCnpj;
    protected int documentType;
    protected Address address;
    protected ArrayList<Order> orders = new ArrayList<Order>();

    public static final Integer DOCUMENT_TYPE_UNDEFINED = 0;
    public static final Integer DOCUMENT_TYPE_CPF = 1;
    public static final Integer DOCUMENT_TYPE_CNPJ = 2;

    public static final String DOCUMENT_TYPE_DISCLOSED_UNDEFINED = "Indefinido";
    public static final String DOCUMENT_TYPE_DISCLOSED_CPF = "CPF";
    public static final String DOCUMENT_TYPE_DISCLOSED_CNPJ = "CNPJ";

    public Client() {
        this.clientId = UUID.randomUUID();
    }

    public UUID getClientID() {
        return this.clientId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCpfOuCnpj() {
        return this.cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getDocumentType() {
        if (this.documentType == 1) {
            return DOCUMENT_TYPE_DISCLOSED_CPF;
        }

        if (this.documentType == 2) {
            return DOCUMENT_TYPE_DISCLOSED_CNPJ;
        }

        return DOCUMENT_TYPE_DISCLOSED_UNDEFINED;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public void setDocumentAsCPF() {
        this.setDocumentType(DOCUMENT_TYPE_CPF);
    }

    public void setDocumentAsCNPJ() {
        this.setDocumentType(DOCUMENT_TYPE_CNPJ);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public Order getOrder(int orderIndex) {
        return this.orders.get(orderIndex);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public String toString() {
        StringJoiner string = new StringJoiner("\n");

        string.add("ID: " + this.getClientID().toString());
        string.add("Nome: " + this.getName());
        string.add("Email: " + this.getEmail());
        string.add("Celular: " + this.getCellphone());
        string.add("Documento: " + this.getCpfOuCnpj());
        string.add("Tipo do Documento: " + this.getDocumentType());
        string.add("Endereço: " + this.getAddress().toString());

        return string.toString();
    }
}