import accounts.ChequingAccount;
import accounts.CreditCard;
import accounts.SavingsAccount;
import interfaces.IAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class BankingService {
    //Integer to represent unique cutsomer ID. ArrayList to store account numbers of Chequing Account, Savings, Credit Card. 
    private static Map<Integer, ArrayList<IAccount>> multiValueMap = new HashMap<Integer, ArrayList<IAccount>>();
    private IAccount chequingAccount;
    private IAccount savingsAccount;
    private IAccount creditCard;
    private Client client;

    public BankingService (Client client) {
        multiValueMap.put(client.getId(), new ArrayList<IAccount>());
    }

    //Creating a New Account
    public void createNewAccount (String type) {
        switch (type) {
            //Chequing Account Number begins with 1
            case "Chequing Account":
                this.chequingAccount = new ChequingAccount();
                multiValueMap.get(client.getId()).add(this.chequingAccount);
                break;
            //Savings Account Numbers begins with 2
            case "Savings Account":
                this.savingsAccount = new SavingsAccount();
                multiValueMap.get(client.getId()).add(this.savingsAccount);
                break;
            case "Credit Card":
                this.creditCard = new CreditCard();
                multiValueMap.get(client.getId()).add(this.creditCard);
            default:
                System.out.println("Invalid Entry");
        }
    }

    //Depositing money
    public void deposit (IAccount account, double depositAmount){
        account.deposit(depositAmount);
    }

    //Withdrawing money
    public void withdraw(IAccount account, double withdrawAmount) {
        account.withdraw(withdrawAmount);
    }

    //Transfering money between two accounts
    public void transfer (IAccount fromAccount, IAccount toAccount, double transferAmount){
        //making sure there is enough money
        fromAccount.withdraw(transferAmount);
        toAccount.deposit(transferAmount);
    }

    //Displaying balance of all accounts. Accounts are distinguished by the first digit.
    public void displayBalances() {
        for (Map.Entry<Integer, ArrayList<IAccount>> entry: multiValueMap.entrySet()){
            for(IAccount account: entry.getValue()){
                System.out.println("Account Number: " + account.getAccountNumber() + " Account Balance: " + account.getBalance());
            }
        }
    }
}