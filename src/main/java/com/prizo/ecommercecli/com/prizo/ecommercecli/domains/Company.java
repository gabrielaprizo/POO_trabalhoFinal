package com.prizo.ecommercecli.domains;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.UUID;

import com.prizo.ecommercecli.utils.CLI;

/**
 * Objeto para o domínio Company
 *
 * @author Gabriela Prizo
 */
public class Company
{
    protected UUID companyId;
    protected String name;
    protected Address address;
    protected ArrayList<Order> orders = new ArrayList<Order>();

    public Company() {
        this.companyId = UUID.randomUUID();
    }

    public UUID getCompanyId() {
        return this.companyId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOrdersSum() {
        double value = (double) this.orders.stream()
        .mapToDouble(o -> o.getTotalCost())
        .sum();

        return CLI.castAsBRL(value);
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

        string.add("ID: " + this.getCompanyId().toString());
        string.add("Nome: " + this.getName());
        string.add("Total de Pedidos: " + this.orders.size());
        string.add("Valor dos Pedidos: " + this.getOrdersSum());
        string.add("Endereço: " + this.getAddress().toString());

        return string.toString();
    }
}