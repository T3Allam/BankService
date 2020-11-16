package accounts;

import exceptions.InsufficientFundsException;
import helpers.SufficientFundsValidator;
import interfaces.IAccount;

public class ChequingAccount implements IAccount {
    private double balance;

    //Retrieving balance on account
    public double getBalance() {
        return this.balance;
    }

    //Depositng money
    public void deposit (double amountDeposit) {
        this.balance += amountDeposit;
    }

    //Withdrawing money
    public void withdraw(double amountWithdraw){
        //Checking if there is enough money into account
        try {
            SufficientFundsValidator.validateTransaction(this, amountWithdraw);
            this.balance -= amountWithdraw;
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}