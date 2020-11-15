package helpers;

import exceptions.InsufficientFundsException;
import interfaces.IAccount;

public final class SufficientFundsValidator {
    public static void validateTransaction(IAccount account, double amountWithdraw) throws InsufficientFundsException {
        if (amountWithdraw > account.getBalance()){
            throw new InsufficientFundsException("Insufficient Funds");
        }
    }

}
