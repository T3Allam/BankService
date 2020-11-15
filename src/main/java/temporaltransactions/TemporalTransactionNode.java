package temporaltransactions;

import java.time.LocalDate;

public class TemporalTransactionNode {

    private LocalDate date;
    //for transactions in the savings account vendor will be null, for transactions in the credit card account we will have a vendor
    private String vendor;
    private double  dollarAmount;
    TemporalTransactionNode next;
    TemporalTransactionNode prev;

    public TemporalTransactionNode(LocalDate date, String vendor, double dollarAmount) {
        this.date = date;
        this.vendor = vendor;
        this.dollarAmount = dollarAmount;
    }

    public TemporalTransactionNode getNext() {
        return next;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getVendor() {
        return vendor;
    }

    public double getDollarAmount() {
        return dollarAmount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDollarAmount(double dollarAmount) {
        this.dollarAmount = dollarAmount;
    }
}
