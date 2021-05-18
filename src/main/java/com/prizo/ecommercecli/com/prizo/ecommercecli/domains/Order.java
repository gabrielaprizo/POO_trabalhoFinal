package com.prizo.ecommercecli.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * Objeto para o domínio Order
 *
 * @author Gabriela Prizo
 */
public class Order
{
    public static final Integer PAYMENT_CREDIT = 1;
    public static final Integer PAYMENT_DEBIT = 2;
    public static final Integer PAYMENT_INVOICE = 3;
    public static final Integer PAYMENT_PIX = 4;
    public static final Integer PAYMENT_PICPAY = 5;
    public static final Integer PAYMENT_TEF = 6;

    public static final String PAYMENT_UNDEFINED_DISCLOSED = "Indefinido";
    public static final String PAYMENT_CREDIT_DISCLOSED = "Cartão de Crédito";
    public static final String PAYMENT_DEBIT_DISCLOSED = "Cartão de Débito";
    public static final String PAYMENT_INVOICE_DISCLOSED = "Boleto";
    public static final String PAYMENT_PIX_DISCLOSED = "PIX";
    public static final String PAYMENT_PICPAY_DISCLOSED = "PicPay";
    public static final String PAYMENT_TEF_DISCLOSED = "TEF";

    public static final Map<Integer, String> DISCLOSED_PAYMENTS = new HashMap<Integer, String>();

    public static final Integer STATUS_PENDING = 1;
    public static final Integer STATUS_PAID = 2;
    public static final Integer STATUS_CANCELLED = 3;

    protected UUID orderId;
    protected Date orderDate;
    protected Client client;
    protected ArrayList<Item> items = new ArrayList<Item>();
    protected float shippingCost;
    protected float totalCost;
    protected float discount;
    protected int paymentType;
    protected Company company;
    protected int orderStatus;
    protected Address address;

    public Order() {
        this.orderId = UUID.randomUUID();

        DISCLOSED_PAYMENTS.put(PAYMENT_CREDIT, PAYMENT_CREDIT_DISCLOSED);
        DISCLOSED_PAYMENTS.put(PAYMENT_DEBIT, PAYMENT_DEBIT_DISCLOSED);
        DISCLOSED_PAYMENTS.put(PAYMENT_INVOICE, PAYMENT_INVOICE_DISCLOSED);
        DISCLOSED_PAYMENTS.put(PAYMENT_PIX, PAYMENT_PIX_DISCLOSED);
        DISCLOSED_PAYMENTS.put(PAYMENT_PICPAY, PAYMENT_PICPAY_DISCLOSED);
        DISCLOSED_PAYMENTS.put(PAYMENT_TEF, PAYMENT_TEF_DISCLOSED);
    }

    public UUID getOrderId() {
        return this.orderId;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getShippingCost() {
        return this.shippingCost;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public float getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getDiscount() {
        return this.discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public Item getItem(int itemIndex) {
        return this.items.get(itemIndex);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static String getDisclosedPayment(int paymentType) {
        if (!DISCLOSED_PAYMENTS.containsKey(paymentType)) {
            return PAYMENT_UNDEFINED_DISCLOSED;
        }

        return DISCLOSED_PAYMENTS.get(paymentType);
    }

    @Override
    public String toString() {
        StringJoiner string = new StringJoiner("\n");

        string.add("ID: " + this.getOrderId().toString());
        string.add("Data: " + this.getOrderDate().toString());
        string.add("Cliente: " + this.getClient().toString());
        string.add("Frete: " + this.getShippingCost());
        string.add("Desconto: " + this.getDiscount());
        string.add("Total: " + this.getTotalCost());
        string.add("ID do Tipo de Pagamento: " + this.getPaymentType());
        string.add("Tipo de Pagamento: " + getDisclosedPayment(this.getPaymentType()));
        string.add("Estado do Pedido: " + this.getOrderStatus());
        string.add("Companhia: " + this.getCompany().toString());
        string.add("Itens: " + this.getItems().toString());
        string.add("Endereço: " + this.getAddress().toString());

        return string.toString();
    }
}