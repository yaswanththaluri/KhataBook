package androidapp.yashthaluri.com.khatabook.Models;

public class ProfileHelper
{
    private String businessName;
    private String phoneNumber;
    private String uniqueId;
    private String ownerName;
    private String imageUrl;
    private String money;

    public ProfileHelper()
    {

    }

    public ProfileHelper(String phoneNumber, String businessName, String  uniqueId, String ownerName, String imageUrl, String  money)
    {
        this.phoneNumber = phoneNumber;
        this.businessName = businessName;
        this.uniqueId = uniqueId;
        this.ownerName = ownerName;
        this.imageUrl = imageUrl;
        this.money = money;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
