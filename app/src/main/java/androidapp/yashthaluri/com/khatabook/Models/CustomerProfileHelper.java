package androidapp.yashthaluri.com.khatabook.Models;

public class CustomerProfileHelper {

    private String mobileNo;
    private String name;
    private String money;
    private String uid;

    public CustomerProfileHelper()
    {

    }

    public  CustomerProfileHelper(String name, String mobileNo, String money, String uid)
    {
        this.mobileNo = mobileNo;
        this.name = name;
        this.money = money;
        this.uid = uid;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
