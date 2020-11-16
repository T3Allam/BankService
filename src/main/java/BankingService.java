import accounts.ChequingAccount;
import accounts.CreditCard;
import accounts.SavingsAccount;
import interfaces.IAccount;


/*
    This is class is a point of entry into the banking system
 */

public class BankingService {
    //Integer to represent unique cutsomer ID. ArrayList to store account numbers of Chequing Account, Savings, Credit Card.
    private ChequingAccount chequingAccount;
    private SavingsAccount savingsAccount;
    private CreditCard creditCard;

    public BankingService () {
        this.chequingAccount = new ChequingAccount();
        this.savingsAccount = new SavingsAccount();
        this.creditCard = new CreditCard();
    }

    public ChequingAccount getChequingAccount() {
        return chequingAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    //Displaying balance of all accounts. Accounts are distinguished by the first digit.
    public void displayBalances() {
        System.out.println("chequingAccount balance: " + chequingAccount.getBalance());
        System.out.println("savingsAccount balance: " + chequingAccount.getBalance());
        System.out.println("creditCard balance: " + chequingAccount.getBalance());
    }

}