package com.prizo.ecommercecli.domains;

import java.util.StringJoiner;
import java.util.UUID;

/**
 * Objeto para o domínio Item
 *
 * @author Gabriela Prizo
 */
public class Item
{
    protected UUID itemId;
    protected String description;
    protected Category category;
    protected int quantity;
    protected float cost;
    protected float discount;

    public Item() {
        this.itemId = UUID.randomUUID();
    }

    public UUID getItemId() {
        return this.itemId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return this.cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getDiscount() {
        return this.discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        StringJoiner string = new StringJoiner("\n");

        string.add("ID: " + this.getItemId().toString());
        string.add("Descrição: " + this.getDescription());
        string.add("Categoria: " + this.getCategory().getName());
        string.add("Quantidade: " + this.getQuantity());
        string.add("Custo: " + this.getCost());
        string.add("Desconto: " + this.getDiscount());

        return string.toString();
    }
}