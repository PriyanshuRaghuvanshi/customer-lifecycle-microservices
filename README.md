# customer-lifecycle-microservices
# Customer Activation, Shopping, and Notification Microservices

This repository contains a practice project that showcases a microservices architecture designed to manage a customer's journey from activation to shopping and receiving notifications(Future prospect). The project includes various services that communicate using RabbitMQ, are built with Spring Boot, and use PostgreSQL for data storage. Apache Camel is used for data transformation and routing.

## Services Included

- **ActivationService**: Manages customer activation or registration.
- **NotificationService**: Sends notifications to customers.
- **VerificationService**: Verifies customer information.
- **OrderToContractService**: Converts order data to contract data using Apache Camel.
- **ProductCatalogService**: Manages the product catalog.
- **ShoppingCartService**: Handles shopping cart operations.

## Technologies Used

- **Java**
- **Spring Boot**
- **RabbitMQ**
- **PostgreSQL**
- **Apache Camel**
- **RESTful Web Services**

## High-Level Design

1. **Customer Activation**: Customers register or onboard into the system using the ActivationService.
2. **Product Catalog**: Customers browse and select products from the ProductCatalogService.
3. **Shopping Cart**: Customers add products to their cart and proceed to checkout using the ShoppingCartService.
4. **Order Processing**: Orders are converted to contracts using the OrderToContractService, which leverages Apache Camel for data transformation.
5. **Notifications**: The NotificationService sends notifications to customers regarding their orders and other updates.



