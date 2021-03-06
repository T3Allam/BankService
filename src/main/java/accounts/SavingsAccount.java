package accounts;

import exceptions.InsufficientFundsException;
import helpers.SufficientFundsValidator;
import interfaces.IAccount;
import temporaltransactions.TemporalTransactionLinkedList;
import temporaltransactions.TemporalTransactionNode;

import java.lang.Math;
import java.time.*;

import java.time.temporal.ChronoUnit;

public class SavingsAccount implements IAccount {
    private double interestRate;
    //Linked list data structure to hold all temporal transactions (account will grow with time based on interest interest)
    private TemporalTransactionLinkedList temporalTransactionLinkedList;

    public SavingsAccount(){
        this.temporalTransactionLinkedList = new TemporalTransactionLinkedList();
        this.interestRate = 0.02;
    }

    //Deposting money - all deposits are time stamped so that interest could be calculated to find End_Investment = principal + interest
    public void deposit (double depositAmount) {
        //keeping vendor null. Vendor will be not be null only in credit card transactions
        TemporalTransactionNode temporalTransactionNode = new TemporalTransactionNode(LocalDate.now(), null, depositAmount);
        this.temporalTransactionLinkedList.append(temporalTransactionNode);
    }

    public double getBalance() {
        //daily interest rate
        double rn = 1+ interestRate/365;
        double balance = 0;
        TemporalTransactionNode iterator = temporalTransactionLinkedList.getHead();
        while (iterator.getNext().getDate() != null){
            //calculating number of days between time of transaction and today
            long period = ChronoUnit.DAYS.between(iterator.getNext().getDate(), LocalDate.now());
            //applying interest rate to original dollar amount
            balance += iterator.getNext().getDollarAmount() * Math.pow(rn, period);
            iterator = iterator.getNext();
        }
        return balance;
    }

    //Withdrawing money
    public void withdraw(double amountWithdraw) {
        //making amount negative
        double amount = -amountWithdraw;
        //Checking if there is enough money into account
        try {
            SufficientFundsValidator.validateTransaction(this, amountWithdraw);
            TemporalTransactionNode newNode = new TemporalTransactionNode(LocalDate.now(), null, amount);
            this.temporalTransactionLinkedList.append(newNode);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printTransactions () {
        this.temporalTransactionLinkedList.print();
    }
}