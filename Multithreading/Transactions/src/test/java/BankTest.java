
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {
    private final long amount = 50000;
    private Account fromAccount = new Account("1", 0);
    private Account toAccount = new Account("2", 0);
    Bank bank;

    @BeforeEach
    void setUp() {

        HashMap<String, Account> accounts = new HashMap<>();
        accounts.put(fromAccount.getAccNumber(), fromAccount);
        accounts.put(toAccount.getAccNumber(), toAccount);
        bank = new Bank(accounts);
    }

    @Test
    public void testTransfer() throws InterruptedException {

        fromAccount.clearAccount(amount);
        toAccount.clearAccount(0L);
        bank.transfer("1", "2", amount);

        assertEquals(0, fromAccount.getMoney());
        assertEquals(amount, toAccount.getMoney());
    }

    @Test
    public void testTransferBlocked() throws InterruptedException {

        fromAccount.clearAccount(amount);
        fromAccount.setBlocked(true);
        toAccount.clearAccount(0L);

        bank.transfer("1", "2", amount);

        assertEquals(amount, fromAccount.getMoney());
        assertEquals(0, toAccount.getMoney());
    }

    @Test
    public void testTransferExcess() throws InterruptedException {

        long balance = 1;

        fromAccount.clearAccount(balance);
        toAccount.clearAccount(0L);

        bank.transfer("1", "2", amount);

        assertEquals(balance, fromAccount.getMoney());
        assertEquals(0, toAccount.getMoney());
    }

    @Test
    public void testGetBalance() {

        fromAccount.clearAccount(amount);
        toAccount.clearAccount(amount);
        toAccount.setBlocked(true);

        assertEquals(amount, bank.getBalance("1"));
        assertEquals(-1, bank.getBalance("2"));
    }

    @Test
    public void testGetSumAllAccounts() {

        fromAccount.clearAccount(amount);
        toAccount.clearAccount(amount);

        assertEquals(100000, bank.getSumAllAccounts());
    }
}
