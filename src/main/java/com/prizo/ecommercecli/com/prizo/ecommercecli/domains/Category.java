package com.prizo.ecommercecli.domains;

import java.util.StringJoiner;
import java.util.UUID;

/**
 * Objeto para o domínio Category
 *
 * @author Gabriela Prizo
 */
public class Category
{
    protected UUID categoryId;
    protected String name;

    public Category() {
        this.categoryId = UUID.randomUUID();
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringJoiner string = new StringJoiner("\n");

        string.add("ID: " + this.getCategoryId().toString());
        string.add("Nome: " + this.getName());

        return string.toString();
    }
}