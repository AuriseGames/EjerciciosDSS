package es.uca.dss.creditcard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreditCardTest {
    
    private CreditCardServiceImpl creditCardService;
    private CreditCard creditCard;

    @Before
    public void setUp() {
        creditCardService = new CreditCardServiceImpl();
        creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", 123, 100.0);
    }

    @Test
    public void testActivate() {
        assertFalse(creditCard.isActive());
        creditCardService.activate(creditCard);
        assertTrue(creditCard.isActive());
    }

    @Test(expected = IllegalStateException.class)
    public void testActivateAlreadyActive() {
        creditCard.setActive(true);
        creditCardService.activate(creditCard);
    }

    @Test
    public void testDeactivate() {
        creditCard.setActive(true);
        creditCardService.deactivate(creditCard);
        assertFalse(creditCard.isActive());
    }

    @Test(expected = IllegalStateException.class)
    public void testDeactivateAlreadyInactive() {
        creditCardService.deactivate(creditCard);
    }

    @Test
    public void testGetBalance() {
        double balance = creditCardService.getBalance(creditCard);
        assertEquals(100.0, balance, 0.001);
    }

    @Test
    public void testIsActive() {
        assertFalse(creditCardService.isActive(creditCard));
        creditCard.setActive(true);
        assertTrue(creditCardService.isActive(creditCard));
    }

    @Test
    public void testPay() {
        creditCard.setActive(true);
        creditCardService.pay(creditCard, 50.0);
        double balance = creditCardService.getBalance(creditCard);
        assertEquals(50.0, balance, 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void testPayInsufficientBalance() {
        creditCardService.pay(creditCard, 200.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testPayInactiveCard() {
        creditCardService.pay(creditCard, 50.0);
    }

    @Test
    public void testWithdraw() {
        creditCard.setActive(true);
        creditCardService.withdraw(creditCard, 50.0);
        double balance = creditCardService.getBalance(creditCard);
        assertEquals(50.0, balance, 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void testWithdrawInsufficientBalance() {
        creditCardService.withdraw(creditCard, 200.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testWithdrawInactiveCard() {
        creditCardService.withdraw(creditCard, 50.0);
    }

    @Test
    public void testTransfer() {
        CreditCard creditCard2 = new CreditCard("9876543210987654", "Jane Smith", "12/23", 456, 50.0);
        creditCard.setActive(true);
        creditCard2.setActive(true);
        creditCardService.transfer(creditCard, creditCard2, 30.0);
        double balance1 = creditCardService.getBalance(creditCard);
        double balance2 = creditCardService.getBalance(creditCard2);
        assertEquals(70.0, balance1, 0.001);
        assertEquals(80.0, balance2, 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void testTransferInsufficientBalance() {
        CreditCard creditCard2 = new CreditCard("9876543210987654", "Jane Smith", "12/23", 456, 50.0);
        creditCard.setActive(true);
        creditCard2.setActive(true);
        creditCardService.transfer(creditCard, creditCard2, 200.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testTransferInactiveCard() {
        CreditCard creditCard2 = new CreditCard("9876543210987654", "Jane Smith", "12/23", 456, 50.0);
        creditCard.setActive(true);
        creditCardService.transfer(creditCard, creditCard2, 30.0);
    }

    @Test
    public void testDeposit() {
        creditCard.setActive(true);
        creditCardService.deposit(creditCard, 50.0);
        double balance = creditCardService.getBalance(creditCard);
        assertEquals(150.0, balance, 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void testDepositInactiveCard() {
        creditCardService.deposit(creditCard, 50.0);
    }
}
