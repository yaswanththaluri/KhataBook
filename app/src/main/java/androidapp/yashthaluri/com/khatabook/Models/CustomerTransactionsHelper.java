package androidapp.yashthaluri.com.khatabook.Models;

public class CustomerTransactionsHelper
{
    private String amount;
    private String transType;
    private String desc;
    private String intrest;
    private String days;
    private String date;

    public CustomerTransactionsHelper()
    {

    }

    public CustomerTransactionsHelper(String amount, String transType, String desc, String intrest, String days, String date)
    {
        this.amount = amount;
        this.transType = transType;
        this.desc = desc;
        this.intrest = intrest;
        this.date = date;
        this.days = days;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIntrest() {
        return intrest;
    }

    public void setIntrest(String intrest) {
        this.intrest = intrest;
    }

    public String getDate() {
        return date;
    }

    public String getDays() {
        return days;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
