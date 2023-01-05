import java.util.concurrent.atomic.AtomicBoolean;

public class Account {

    private long money;
    private String accNumber;
    private volatile AtomicBoolean blocked = new AtomicBoolean();

    public Account(String accNumber, long money) {
        this.money = money;
        this.accNumber = accNumber;
        this.blocked.set(false);
    }

    public long getMoney() {
        return money;
    }

    public synchronized void setMoney(long money) {
        this.money += money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public boolean isBlocked() {
        return blocked.get();
    }

    public synchronized void setBlocked(boolean blocked) {
        this.blocked.set(blocked);
    }

    public void clearAccount(Long money){
        this.money = money;
        this.blocked.set(false);
    }

    @Override
    public String toString() {
        return "Аккаунт №" +
                accNumber +
                "\t-\t" +
                money +
                "; статус: " +
                (isBlocked() ? "заблокирован" : "доступен");
    }
}
