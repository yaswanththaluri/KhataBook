package androidapp.yashthaluri.com.khatabook.Models;

public class CustomerTransactionsHelper
{
    private String amount;
    private String transType;
    private String desc;
    private String intrest;

    public CustomerTransactionsHelper()
    {

    }

    public CustomerTransactionsHelper(String amount, String transType, String desc, String intrest)
    {
        this.amount = amount;
        this.transType = transType;
        this.desc = desc;
        this.intrest = intrest;
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
}
