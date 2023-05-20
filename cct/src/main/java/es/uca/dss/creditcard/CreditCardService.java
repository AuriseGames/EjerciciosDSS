package es.uca.dss.creditcard;

public interface CreditCardService {

    public void activate(CreditCard creditCard);
    public void deactivate(CreditCard creditCard);
    public double getBalance(CreditCard creditCard);
    public boolean isActive(CreditCard creditCard);
    public void pay(CreditCard creditCard, double amount);
    public void withdraw(CreditCard creditCard, double amount);
    public void transfer(CreditCard creditCard, CreditCard creditCard2, double amount);
    public void deposit(CreditCard creditCard, double amount);

}
