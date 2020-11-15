package helpers;

import java.util.Random;

public final class NumberGenerator {

    private static int id;
    private static int accountNumber;

    //Generating ID for client
    public static int generateId(){
        return ++id;
    }

    //Generating a random a unique eight digit account number. This has to be revisited if we have more than 100,000,000 clients.
    public static int generateUniqueAccountNumber () {
        accountNumber++;
        if (String.valueOf(accountNumber).length() < 8 ) {
            //left-pad with zeros
            String b= String.format("%08d" , accountNumber);
            accountNumber = Integer.parseInt(b);
        }
        return accountNumber;
    }

    public static String generateRandomWord() {
        //Generating random 4 letter word to represent vendor. [Source of this algorithm: Barry Burd dummies.com]
        Random r = new Random();
        String vendor = "" + (char) (r.nextInt(26) + 'A') +
                (char) (r.nextInt(26) + 'a') +
                (char) (r.nextInt(26) + 'a') +
                (char) (r.nextInt(26) + 'a');
        return vendor;
    }

}