package androidapp.yashthaluri.com.khatabook.Models;

public class CutsomerDetails {
    int Bitmap;
    String name,time, amount, uid;

    public CutsomerDetails()
    {

    }

    public CutsomerDetails(int bitmap, String amount, String name, String time, String uid) {
        Bitmap = bitmap;
        this.amount = amount;
        this.name = name;
        this.time = time;
        this.uid = uid;
    }

    public int getBitmap() {
        return Bitmap;
    }

    public void setBitmap(int bitmap) {
        Bitmap = bitmap;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
