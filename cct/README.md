# Ejercicio 1 - Credit Card Test

En este ejercicio realizaremos tests unitarios utilizando Junit 4. Para ello se han creado las clases siguientes:

## CreditCard
Clase que representa una tarjeta de crédito. Contiene los atributos:

- private String cardNumber;
- private String cardHolderName;
- private String expiryDate;
- private int cvv;
- private double balance;
- private boolean isActive;

Además, contiene los métodos getter y setter de cada atributo. Por defecto, la tarjeta está desactivada. Recibe como parámetros en el constructor el número de tarjeta, el nombre del titular, la fecha de caducidad, el cvv y el saldo inicial.

## CreditCardService
Clase que sirve de interfaz para el uso de la clase CreditCard. Contiene los métodos:

- public void activate(CreditCard creditCard);
- public void deactivate(CreditCard creditCard);
- public double getBalance(CreditCard creditCard);
- public boolean isActive(CreditCard creditCard);
- public void pay(CreditCard creditCard, double amount);
- public void withdraw(CreditCard creditCard, double amount);
- public void transfer(CreditCard creditCard, CreditCard creditCard2, double amount);
- public void deposit(CreditCard creditCard, double amount);

## CreditCardServiceImpl
Clase que implementa la interfaz CreditCardService. Contiene los métodos anteriores, más los siguientes:

- private void checkCreditCardExists(CreditCard creditCard);

    Comprueba que la tarjeta existe. En caso contrario, lanza una excepción.

- private void checkCreditCardValid(CreditCard creditCard);

    Llama a la anterior y además comprueba que la tarjeta es válida. En caso contrario, lanza una excepción.

- private void checkCreditCard(CreditCard creditCard);

    Llama a la anterior y además comprueba que la tarjeta está activa. En caso contrario, lanza una excepción.

- private void checkTransaction(CreditCard creditCard, double amount);

    Comprueba que la cuenta dispone de al menos amount de saldo. En caso contrario, lanza una excepción.

- private void makeTransaction(CreditCard creditCard, double amount);

    Realiza la transacción añadiendo a creditCard el valor amount si es positivo, o restando el valor amount si es negativo. Por eso, cuando se quiere retirar dinero, se llama a esta función con un valor negativo.

## CreditCardTest

Clase que contiene los tests de la clase CreditCardServiceImpl. Contiene los siguientes tests:

- testActivate();
- testActivateAlreadyActive();
- testDeactivate();
- testDeactivateAlreadyInactive();
- testGetBalance();
- testIsActive();
- testPay();
- testPayInsufficientBalance();
- testPayInactiveCard();
- testWithdraw();
- testWithdrawInsufficientBalance();
- testWithdrawInactiveCard();
- testTransfer();
- testTransferInsufficientBalance();
- testTransferInactiveCard();
- testDeposit();
- testDepositInactiveCard();