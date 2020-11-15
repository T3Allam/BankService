package helpers;

import exceptions.InsufficientFundsException;
import interfaces.IAccount;

public class SufficientFundsValidator {
    public static void validateTransaction(IAccount account, double amountWithdraw) throws InsufficientFundsException {
        if (amountWithdraw > account.getBalance()){
            throw new InsufficientFundsException("Insufficient Funds");
        }
    }

    public static void validateCreditCardTransaction(IAccount account, double amountWithdraw) throws InsufficientFundsException {
        if (amountWithdraw > account.getBalance()){
            throw new InsufficientFundsException("Insufficient Funds");
        }
    }

}
