package com.prizo.ecommercecli;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.prizo.ecommercecli.domains.Address;
import com.prizo.ecommercecli.domains.Category;
import com.prizo.ecommercecli.domains.Client;
import com.prizo.ecommercecli.domains.Company;
import com.prizo.ecommercecli.domains.Item;
import com.prizo.ecommercecli.domains.Order;
import com.prizo.ecommercecli.utils.CLI;

public class ecommerceCLI extends CLI {
    protected static Boolean runApp = true;
    protected static Boolean keepSubMenu = true;

    protected static final String VERSION = "1.0.0";

    protected static ArrayList<Order> orders = new ArrayList<Order>();
    protected static ArrayList<Client> clients = new ArrayList<Client>();
    protected static ArrayList<Category> categories = new ArrayList<Category>();
    protected static ArrayList<Company> companies = new ArrayList<Company>();
    protected static ArrayList<Item> availableItems = new ArrayList<Item>();

    public static void main(String[] args) {
        coldStart();
        fillDummyData();

        do {
            runOptions();
        } while (runApp);

        command.close();
    }

    /**
     * Internals operations
     */
    protected static Address createAddress(
        String streetName,
        String streetNumber,
        String neighborhood,
        String additionalData,
        String zipcode,
        String references
    ) {
        Address address = new Address();
        address.setStreet(streetName);
        address.setNumber(streetNumber);
        address.setNeighborhood(neighborhood);
        address.setAdditionalData(additionalData);
        address.setZipcode(zipcode);
        address.setReferences(references);

        return address;
    }

    protected static Order createOrder(
        Client client,
        Float shippingCost,
        Float discount,
        Float totalCost,
        Date orderDate,
        Integer paymentType,
        List<Item> items,
        Company company,
        Address address
    ) {
        Order order = new Order();
        order.setClient(client);
        order.setShippingCost(shippingCost);
        order.setDiscount(discount);
        order.setTotalCost(totalCost);
        order.setPaymentType(paymentType);
        order.setOrderStatus(Order.STATUS_PENDING);
        order.setCompany(company);
        order.setAddress(address);
        order.setOrderDate(orderDate);

        ListIterator<Item> iterator = items.listIterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            order.addItem(item);
        }

        return order;
    }

    protected static Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    protected static Item createItem(
        String description,
        Category category,
        int quantity,
        Float cost,
        Float discount
    ) {
        Item item = new Item();
        item.setDescription(description);
        item.setCategory(category);
        item.setQuantity(quantity);
        item.setCost(cost);
        item.setDiscount(discount);

        return item;
    }

    protected static Client createClient(
        String name,
        String email,
        String document,
        Integer documentType,
        Address address
    ) {
        Client client = new Client();

        client.setName(name);
        client.setEmail(email);
        client.setCpfOuCnpj(document);

        if (documentType.equals(Client.DOCUMENT_TYPE_CPF)) {
            client.setDocumentAsCPF();
        }

        if (documentType.equals(Client.DOCUMENT_TYPE_CNPJ)) {
            client.setDocumentAsCNPJ();
        }

        client.setAddress(address);

        return client;
    }

    /**
     * Filling dummy data
     */
    protected static void fillDummyData() {
        // Companies
        companyOne();
        companyTwo();
        companyThree();

        // Clients
        clientOne();
        clientTwo();
        clientThree();

        // Categories
        availableCategories();

        // Items
        availableItems();

        // Orders
        orderOne();
        orderTwo();
        orderThree();
        orderFour();
        orderFive();
    }

    protected static void companyOne() {
        // Company
        Address companyAddress = createAddress("Rua Francisco Sá", "214", "Várzea", "Início da Rua", "25953-010", "Em frente à Cacau Show.");

        Company company = new Company();
        company.setAddress(companyAddress);
        company.setName("Drogaria Pacheco");

        companies.add(company);
    }

    protected static void companyTwo() {
        Address companyAddress = createAddress("R. Pref. Sebastião Teixeira", "227", "Várzea", "Prédio na esquina", "25953-200", "Sério?");

        Company company = new Company();
        company.setAddress(companyAddress);
        company.setName("Alterdata");

        companies.add(company);
    }

    protected static void companyThree() {
        Address companyAddress = createAddress("Av. Oliveira Botelho", "617", "Alto", "Loja 1", "25961-147", "Perto da Feirinha do Alto");

        Company company = new Company();
        company.setAddress(companyAddress);
        company.setName("Viva Itália");

        companies.add(company);
    }

    protected static void clientOne() {
        Address clientAddress = createAddress("Avenida Feliciano Sodré", "675", "Várzea", "Sala 01", "25963-082", "Prefeitura");

        Client client = createClient("Adamastor Souza", "adamastor.souza@test.localhost", "410.137.910-61", Client.DOCUMENT_TYPE_CPF, clientAddress);

        clients.add(client);
    }

    protected static void clientTwo() {
        Address clientAddress = createAddress("Avenida Feliciano Sodré", "675", "Várzea", "Sala 02", "25963-082", "Prefeitura");

        Client client = createClient("Geraldo Bartholomeu Santos", "geraldo.santos@test.localhost", "594.448.610-40", Client.DOCUMENT_TYPE_CPF, clientAddress);

        clients.add(client);
    }

    protected static void clientThree() {
        Address clientAddress = createAddress("Avenida Feliciano Sodré", "675", "Várzea", "Segundo Andar", "25963-082", "Prefeitura");

        Client client = createClient("Prefeitura Municipal de Teresópolis", "pmt.contato@test.localhost", "29138369000147", Client.DOCUMENT_TYPE_CNPJ, clientAddress);

        clients.add(client);
    }

    protected static void availableCategories() {
        categories.add(createCategory("Nacionais"));
        categories.add(createCategory("Importados"));
        categories.add(createCategory("Promocao"));
        categories.add(createCategory("FreteGratis"));
    }

    protected static void availableItems() {
        availableItems.add(createItem("Bola Quadrada", categories.get(0), 1, (float) 50.00, (float)  0.00));
        availableItems.add(createItem("Tomate Azul", categories.get(1), 1, (float) 150.00, (float) 0.00));
        availableItems.add(createItem("Pneu Furado", categories.get(2), 1, (float) 70.00, (float) 60.00));
        availableItems.add(createItem("Use a cabeça!: Java", categories.get(3), 1, (float) 75.00, (float) 74.00));
        availableItems.add(createItem("Fusca 1955", categories.get(1), 1, (float) 6075.00, (float) 0.00));
        availableItems.add(createItem("Jantar para 2 no Viva Itália", categories.get(2), 1, (float) 400.00, (float) 200.00));
    }

    protected static void orderOne() {
        List<Item> items = new ArrayList<>();
        items.add(availableItems.get(0));
        items.add(availableItems.get(1));

        Date orderDate = Date.from(LocalDate.now().atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));

        Order order = createOrder(clients.get(0), (float) 10.00, (float) 0.00, (float) 210.00, orderDate, Order.PAYMENT_PICPAY, items, companies.get(0), clients.get(0).getAddress());
        orders.add(order);

        // Associating order to client
        clients.get(0).addOrder(order);

        // Associating order to company
        companies.get(0).addOrder(order);
    }

    protected static void orderTwo() {
        List<Item> items = new ArrayList<>();
        items.add(availableItems.get(2));

        Date orderDate = Date.from(LocalDate.now().atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));

        Order order = createOrder(clients.get(1), (float) 10.00, (float) 60.00, (float) 20.00, orderDate, Order.PAYMENT_PIX, items, companies.get(1), clients.get(1).getAddress());
        orders.add(order);

        // Associating order to client
        clients.get(1).addOrder(order);

        // Associating order to company
        companies.get(1).addOrder(order);
    }

    protected static void orderThree() {
        List<Item> items = new ArrayList<>();
        items.add(availableItems.get(5));

        Date orderDate = Date.from(LocalDate.now().atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));

        Order order = createOrder(clients.get(2), (float) 0.00, (float) 200.00, (float) 200.00, orderDate, Order.PAYMENT_TEF, items, companies.get(2), clients.get(2).getAddress());
        orders.add(order);

        // Associating order to client
        clients.get(2).addOrder(order);

        // Associating order to company
        companies.get(2).addOrder(order);
    }

    protected static void orderFour() {
        List<Item> items = new ArrayList<>();
        items.add(availableItems.get(4));

        Date orderDate = Date.from(LocalDate.now().atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));

        Order order = createOrder(clients.get(1), (float) 0.00, (float) 0.00, (float) 6075.00, orderDate, Order.PAYMENT_DEBIT, items, companies.get(1), clients.get(1).getAddress());
        orders.add(order);

        // Associating order to client
        clients.get(1).addOrder(order);

        // Associating order to company
        companies.get(1).addOrder(order);
    }

    protected static void orderFive() {
        List<Item> items = new ArrayList<>();
        items.add(availableItems.get(3));

        Date orderDate = Date.from(LocalDate.now().atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));

        Order order = createOrder(clients.get(0), (float) 0.00, (float) 74.00, (float) 1.00, orderDate, Order.PAYMENT_CREDIT, items, companies.get(0), clients.get(0).getAddress());
        orders.add(order);

        // Associating order to client
        clients.get(0).addOrder(order);

        // Associating order to company
        companies.get(0).addOrder(order);
    }

    /**
     * Menus
     */
    protected static void runOptions() {
        infoMessage("Escolha uma opção principal:");
        String option = command.nextLine().toLowerCase().trim();

        if (option == null || option.isEmpty()) {
            warningMessage("~> Escolha uma opção!");
            return;
        }

        switch (option) {
            case "menu":
                menu();
            break;
            case "companies":
                do {
                    companiesMenu();
                } while (keepSubMenu);
            break;
            case "exit":
                exit();
            break;
            default:
                echo(String.format(WHITE + RED_BACKGROUND + "%s" + RESET, "Não existe essa opção"));
                menu();
            break;
        }
    }

    protected static void coldStart() {
        systemAskMessage("Olá! Bem vindo ao gerenciador de e-commerce Prizo.");
        systemAskMessage("A versão corrente é a " + VERSION);

        menu();
    }

    protected static void menu() {
        StringJoiner optionsTable = new StringJoiner("");

        optionsTable.add(tableSeparator());
        optionsTable.add(tableLine("menu", "Exibe o menu principal"));
        optionsTable.add(tableLine("companies", "Exibe o menu de companhias"));
        optionsTable.add(tableLine("exit", "Encerra o programa"));
        optionsTable.add(tableSeparator());

        systemAskMessage("As opções principais são:");
        systemAskMessage(optionsTable.toString());
    }

    /**
     * Companies methods
     */
    protected static void companiesMenu() {
        StringJoiner optionsTable = new StringJoiner("");

        optionsTable.add(tableSeparator());
        optionsTable.add(tableLine("add", "Adiciona uma nova companhia"));
        optionsTable.add(tableLine("all", "Lista todas as companhias"));
        optionsTable.add(tableLine("show", "Exibe uma companhia"));
        optionsTable.add(tableLine("orders", "Exibe todos os pedidos de uma companhia"));
        optionsTable.add(tableLine("invoice", "Exibe a fatura de um pedido"));
        optionsTable.add(tableLine("total-revenue", "Exibe o faturamento total de uma companhia"));
        optionsTable.add(tableLine("most-common-payment", "Qual o método de pagamento mais usado"));
        optionsTable.add(tableLine("revenue-by-payment", "Faturamento separado por tipo"));
        optionsTable.add(tableLine("search-revenue", "Pesquisa o faturamento baseado em datas"));
        optionsTable.add(tableLine("go-back", "Volta para o menu principal"));
        optionsTable.add(tableSeparator());

        String subOptions = String.format(
            BLACK_BOLD_BRIGHT + "%s\n%s\n%s\n%s" + RESET,
            "~> Você está na opção companies",
            "Escolha uma sub-opção:",
            optionsTable.toString(),
            "->:"
        );

        echo(subOptions);

        String option = command.nextLine().toLowerCase().trim();

        if (option == null || option.isEmpty()) {
            warningMessage("~> Escolha uma opção!");
            return;
        }

        switch(option) {
            case "add":
                addCompany();
            break;
            case "all":
                listCompanies();
            break;
            case "show":
                showCompany();
            break;
            case "orders":
                displayOrders();
            break;
            case "invoice":
                generateInvoice();
            break;
            case "total-revenue":
                showOrdersTotal();
            break;
            case "search-revenue":
                searchRevenues();
            break;
            case "most-common-payment":
                mostCommonPayment();
            break;
            case "revenue-by-payment":
                revenueByPayment();
            break;
            case "go-back":
                keepSubMenu = false;
            break;
            default:
                errorMessage("Não existe essa opção!");
                echo(subOptions);
            break;
        }
    }

    protected static void listCompanies() {
        systemResponseMessage("Exibindo todas as companhias.");
        ListIterator<Company> iterator = companies.listIterator();

        StringJoiner companiesTable = new StringJoiner("");

        companiesTable.add(tableSeparator());
        companiesTable.add(tableLine("Nome", "ID"));
        companiesTable.add(tableSeparator());

        while (iterator.hasNext()) {
            Company currentCompany = iterator.next();
            companiesTable.add(tableLine(currentCompany.getName(), currentCompany.getCompanyId().toString()));
        }

        companiesTable.add(tableSeparator());

        systemResponseMessage(companiesTable.toString());
    }

    protected static void addCompany() {
        systemAskMessage("Vamos adicionar uma companhia!");
        systemAskMessage("Qual o nome da companhia?");
        String name = command.nextLine().trim();
        systemResponseMessage("Ótimo! O nome da companhia será: " + name);
        systemAskMessage("Vamos preencher o endereço dela agora.");
        systemAskMessage("Qual o nome da rua?");
        String streetName = command.nextLine().trim();
        systemAskMessage("Qual o número?");
        String streetNumber = command.nextLine().trim();
        systemAskMessage("Qual o complemento?");
        String additionalData = command.nextLine().trim();
        systemAskMessage("Qual o bairro?");
        String neighborhood = command.nextLine().trim();
        systemAskMessage("Qual o CEP?");
        String zipcode = command.nextLine().trim();
        systemAskMessage("Alguma referência?");
        String references = command.nextLine().trim();

        Address address = createAddress(streetName, streetNumber, neighborhood, additionalData, zipcode, references);

        Company newCompany = new Company();
        newCompany.setAddress(address);
        newCompany.setName(name);

        companies.add(newCompany);
        systemResponseMessage("Companhia cadastrada com sucesso!");
    }

    protected static void showCompany() {
        systemAskMessage("Qual o UUID da companhia?");
        String uuid = command.nextLine().trim();

        if (uuid == null) {
            errorMessage("Preencha o ID da companhia");
            return;
        }

        List<Company> filteredCompanies = companies
            .stream()
            .filter(c -> c.getCompanyId().toString().equals(uuid))
            .collect(Collectors.toList());

        if (filteredCompanies.size() == 0) {
            errorMessage("Não foi encontrada uma companhia com esse nome");
            return;
        }

        Company currentCompany = filteredCompanies.get(0);

        echo(BLACK_BOLD_BRIGHT + "Exibindo a companhia!" + RESET);
        systemResponseMessage(currentCompany.toString());
    }

    protected static void showOrdersTotal() {
        systemAskMessage("Qual o UUID da companhia?");
        String uuid = command.nextLine().trim();

        if (uuid == null) {
            errorMessage("Preencha o ID da companhia");
            return;
        }

        List<Order> filteredOrders = retrieveOrders(uuid);

        if (filteredOrders.size() == 0) {
            errorMessage("Essa companhia não tem pedidos!");
            return;
        }

        double sum = (double) filteredOrders.stream()
            .mapToDouble(o -> o.getTotalCost())
            .sum();

        systemResponseMessage(String.format("O valor total dos pedidos foi %s", castAsBRL(sum)));
    }

    protected static void searchRevenues() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        systemAskMessage("Qual o UUID da companhia?");
        String uuid = command.nextLine().trim();

        if (uuid == null || uuid == "" || uuid.isEmpty()) {
            errorMessage("Preencha o ID da companhia");
            return;
        }

        systemAskMessage("Qual a data inicial (formato dd/MM/YYYY)?");
        String rawStartDate = command.nextLine().trim();

        if (rawStartDate == null || rawStartDate == "" || rawStartDate.isEmpty()) {
            errorMessage("Preencha a data de início");
            return;
        }

        Date startDate = Date.from(LocalDate.parse(rawStartDate, formatter).atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));

        systemAskMessage("Qual a data final (formato dd/MM/YYYY)?");
        String rawEndDate = command.nextLine().trim();

        if (rawEndDate == null || rawEndDate == "" || rawEndDate.isEmpty()) {
            errorMessage("Preencha a data final");
            return;
        }

        Date endDate = Date.from(LocalDate.parse(rawEndDate, formatter).atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));

        List<Order> filteredOrders = orders
            .stream()
            .filter(o -> 
                o.getCompany().getCompanyId().toString().equals(uuid) &&
                o.getOrderDate() != null &&
                !(o.getOrderDate().before(startDate) || o.getOrderDate().after(endDate))
            )
            .collect(Collectors.toList());

        if (filteredOrders.size() == 0) {
            errorMessage("Essa companhia não teve faturamento nesse período.");
            return;
        }

        double sum = (double) filteredOrders.stream()
            .mapToDouble(o -> o.getTotalCost())
            .sum();

        systemResponseMessage(
            String.format("O valor total dos pedidos entre %s e %s foi de %s",
                new SimpleDateFormat("dd/MM/yyyy").format(startDate),
                new SimpleDateFormat("dd/MM/yyyy").format(endDate),
                castAsBRL(sum)
            )
        );
    }

    protected static void mostCommonPayment() {
        systemAskMessage("Qual o UUID da companhia?");
        String uuid = command.nextLine().trim();

        if (uuid == null) {
            errorMessage("Preencha o ID da companhia");
            return;
        }

        // Moda - Estatística com uso de Collectors - https://medium.com/@racc.costa/collectors-no-java-8-e-no-java-9-6a3ba8f3f42f
        Map<Object, Long> filteredOrders = orders
            .stream()
            .filter(o -> o.getCompany().getCompanyId().toString().equals(uuid))
            .collect(Collectors.groupingBy(order -> order.getPaymentType(), Collectors.counting()));

        if (filteredOrders.size() == 0) {
            errorMessage("Não foram encontrados pedidos para essa companhia");
            return;
        }

        // Extrair o maior valor de uma lista https://stackoverflow.com/a/30943415
        Integer maxUsed = (Integer) filteredOrders.values()
            .stream()
            .max(Long::compareTo)
            .map(maxValue -> filteredOrders.entrySet()
                .stream()
                .filter(entry -> maxValue.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .get(0)
            )
            .orElse(Collections.emptyList());

        systemResponseMessage("O método de pagamento mais utilizado foi " + Order.getDisclosedPayment(maxUsed));
    }

    protected static void revenueByPayment() {
        systemAskMessage("Qual o UUID da companhia?");
        String uuid = command.nextLine().trim();

        if (uuid == null) {
            errorMessage("Preencha o ID da companhia");
            return;
        }

        // Moda - Estatística
        Map<Object, List<Order>> filteredOrders = orders
            .stream()
            .filter(o -> o.getCompany().getCompanyId().toString().equals(uuid))
            .collect(Collectors.groupingBy(order -> order.getPaymentType()));

        if (filteredOrders.size() == 0) {
            errorMessage("Não foram encontrados pedidos para essa companhia");
            return;
        }

        StringJoiner paymentsRanking = new StringJoiner("");

        paymentsRanking.add(tableSeparator());
        paymentsRanking.add(tableLine("Método", "Valores"));
        paymentsRanking.add(tableSeparator());

        // https://stackoverflow.com/a/46908
        for (Map.Entry<Object, List<Order>>entry : filteredOrders.entrySet()) {
            double sum = (double) entry.getValue()
                .stream()
                .mapToDouble(o -> o.getTotalCost())
                .sum();

            paymentsRanking.add(tableLine(Order.getDisclosedPayment((Integer) entry.getKey()), castAsBRL(sum)));
        }

        paymentsRanking.add(tableSeparator());

        systemResponseMessage(paymentsRanking.toString());
    }

    protected static void displayOrders() {
        systemAskMessage("Qual o UUID da companhia?");
        String uuid = command.nextLine().trim();

        if (uuid == null) {
            errorMessage("Preencha o ID da companhia");
            return;
        }

        ListIterator<Order> filteredOrders = retrieveOrders(uuid).listIterator();

        StringJoiner ordersTable = new StringJoiner("");

        ordersTable.add(tableSeparator());
        ordersTable.add(tableLine("#", "Valor"));
        ordersTable.add(tableSeparator());

        while (filteredOrders.hasNext()) {
            Order order = filteredOrders.next();
            ordersTable.add(tableLine(order.getOrderId().toString(), castAsBRL(order.getTotalCost())));
        }

        ordersTable.add(tableSeparator());

        systemResponseMessage(ordersTable.toString());

    }

    protected static void generateInvoice() {
        systemAskMessage("Qual o UUID do pedido?");
        String uuid = command.nextLine().trim();

        if (uuid == null) {
            errorMessage("Preencha o UUID do pedido!");
            return;
        }

        Order order = retrieveOrder(uuid);

        Integer defaultSpace = 170;
        StringJoiner invoiceTable = new StringJoiner("");
        String asteriskDelimiter = "*".repeat(defaultSpace) + "\n";
        String underscoreDelimiter = "_".repeat(defaultSpace) + "\n";

        String spacerItems = "| %-40s | %-54s | %-20s | %-20s | %-20s |%n";

        Company company = order.getCompany();
        Client client = order.getClient();

        String companyName = company.getName();
        String centerTitleSize = "*".repeat(((defaultSpace - companyName.length()) / 2) - 1);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        invoiceTable.add(asteriskDelimiter);
        invoiceTable.add(String.format("%1$s %2$s %1$s\n", centerTitleSize, companyName));
        invoiceTable.add(asteriskDelimiter);
        invoiceTable.add(String.format("\nData e hora da geração: %s\n", formatter.format(date)));
        invoiceTable.add("\nCliente\n");
        invoiceTable.add(underscoreDelimiter);
        invoiceTable.add(String.format("Nome: %s\n", client.getName()));
        invoiceTable.add(String.format("Endereço: %s\n", client.getAddress().toString()));
        invoiceTable.add(underscoreDelimiter);
        invoiceTable.add(String.format(spacerItems, "Código", "Descrição", "Quantidade", "Valor Unitário", "Valor Total"));
        invoiceTable.add(underscoreDelimiter);

        ListIterator<Item> iterator = order.getItems().listIterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            invoiceTable.add(
                String.format(
                    spacerItems,
                    item.getItemId().toString(),
                    item.getDescription(),
                    item.getQuantity(),
                    item.getCost(),
                    item.getQuantity() * item.getCost()
                )
            );
        }

        invoiceTable.add(underscoreDelimiter);
        invoiceTable.add(String.format("Frete: %s\n", castAsBRL(order.getShippingCost())));
        invoiceTable.add(String.format("Desconto: %s\n", castAsBRL(order.getDiscount())));
        invoiceTable.add(String.format("Valor Total: %s\n", castAsBRL(order.getTotalCost())));

        invoiceTable.add(asteriskDelimiter);
        invoiceTable.add(asteriskDelimiter);

        systemResponseMessage(invoiceTable.toString());
    }

    protected static List<Order> retrieveOrders(String uuid) {
        return orders
            .stream()
            .filter(o -> o.getCompany().getCompanyId().toString().equals(uuid))
            .collect(Collectors.toList());
    }

    protected static Order retrieveOrder(String uuid) {
        return orders
            .stream()
            .filter(o -> o.getOrderId().toString().equals(uuid))
            .collect(Collectors.toList())
            .get(0);
    }

    /**
     * Software generic methods
     */
    protected static void exit() {
        runApp = false;
        infoMessage("Obrigado por usar o programa! Até a próxima.");
    }
}
