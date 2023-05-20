package es.uca.dss.creditcard;

public class CreditCardServiceImpl implements CreditCardService {
    
    private void checkCreditCardExists(CreditCard creditCard) {
        if (creditCard == null) {
            throw new IllegalArgumentException("Credit card cannot be null");
        }
    }
    
    private void checkCreditCardValid(CreditCard creditCard) {

        checkCreditCardExists(creditCard);

        // comprobamos que todos los campos sean validos
        if (creditCard.getCardNumber() == null || creditCard.getCardNumber().isEmpty()) {
            throw new IllegalArgumentException("Credit card number cannot be null or empty");
        }
        if (creditCard.getCardHolderName() == null || creditCard.getCardHolderName().isEmpty()) {
            throw new IllegalArgumentException("Credit card holder name cannot be null or empty");
        }
        if (creditCard.getExpiryDate() == null || creditCard.getExpiryDate().isEmpty()) {
            throw new IllegalArgumentException("Credit card expiry date cannot be null or empty");
        }
        if (creditCard.getCvv() <= 0) {
            throw new IllegalArgumentException("Credit card CVV cannot be less than or equal to 0");
        }
        if (creditCard.getBalance() < 0) {
            throw new IllegalArgumentException("Credit card balance cannot be less than 0");
        }

    }
    
    private void checkCreditCard(CreditCard creditCard) {
        checkCreditCardExists(creditCard);
        checkCreditCardValid(creditCard);
        if (!creditCard.isActive()) {
            throw new IllegalStateException("Credit card is not active");
        }
    }

    @Override
    public void activate(CreditCard creditCard) {

        checkCreditCardExists(creditCard);
        if (creditCard.isActive()) {
            throw new IllegalStateException("Credit card is already active");
        }
        creditCard.setActive(true);
    }

    @Override
    public void deactivate(CreditCard creditCard) {

        checkCreditCardExists(creditCard);
        if (!creditCard.isActive()) {
            throw new IllegalStateException("Credit card is already inactive");
        }
        creditCard.setActive(false);
    }

    @Override
    public double getBalance(CreditCard creditCard) {

        checkCreditCardValid(creditCard);
        return creditCard.getBalance();
    }

    @Override
    public boolean isActive(CreditCard creditCard) {

        checkCreditCardExists(creditCard);
        return creditCard.isActive();
    }

    private void checkTransaction(CreditCard creditCard, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be less than or equal to 0");
        }
        if (creditCard.getBalance() < amount) {
            throw new IllegalStateException("Credit card balance is less than amount");
        }
    }

    private void makeTransaction(CreditCard creditCard, double amount) {
        creditCard.setBalance(creditCard.getBalance() + amount);
    }

    @Override
    public void pay(CreditCard creditCard, double amount) {
        checkCreditCard(creditCard);
        checkTransaction(creditCard, amount);
        makeTransaction(creditCard, amount * -1);
    }

    @Override
    public void withdraw(CreditCard creditCard, double amount) {
        checkCreditCard(creditCard);
        checkTransaction(creditCard, amount);
        makeTransaction(creditCard, amount * -1);
    }

    @Override
    public void transfer(CreditCard creditCard, CreditCard creditCard2, double amount) {
        checkCreditCard(creditCard);
        checkCreditCard(creditCard2);
        checkTransaction(creditCard, amount);
        creditCard.setBalance(creditCard.getBalance() - amount);
        creditCard2.setBalance(creditCard2.getBalance() + amount);
    }

    @Override
    public void deposit(CreditCard creditCard, double amount) {
        checkCreditCard(creditCard);
        makeTransaction(creditCard, amount);
    }

}
