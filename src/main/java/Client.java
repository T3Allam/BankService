import accounts.ChequingAccount;
import accounts.CreditCard;
import accounts.SavingsAccount;


import java.util.Map;

public class Client {

        public static void main (String[] args) {
            //Create a new chequing account, saving account, credit card
            BankingService bankingService = new BankingService();

            //Retrieving Accounts
            ChequingAccount chequingAccount = bankingService.getChequingAccount() ;
            SavingsAccount savingsAccount = bankingService.getSavingsAccount();
            CreditCard creditCard = bankingService.getCreditCard();

            //Testing Chequing Account
            /*
            chequingAccount.deposit(10);
            System.out.println(chequingAccount.getBalance());
            chequingAccount.withdraw(10);
            System.out.println(chequingAccount.getBalance());
            chequingAccount.withdraw(10);
            System.out.println(chequingAccount.getBalance());
            */

            //Testing Savings Account
            /*
            savingsAccount.deposit(10);
            savingsAccount.deposit(10);
            savingsAccount.deposit(10);
            System.out.println(savingsAccount.getBalance());
            savingsAccount.withdraw(30);
            savingsAccount.printTransactions();
            System.out.println(savingsAccount.getBalance());
            savingsAccount.withdraw(30);`
             */

            //Testing CreditCard
            /*
            creditCard.withdraw(10);
            creditCard.withdraw(10);
            creditCard.deposit(50);
            creditCard.withdraw(600);
            creditCard.printTransactions();
            */
        }
}