package androidapp.yashthaluri.com.khatabook.Models;

public class CutsomerDetails {
    int Bitmap;
    String name,time, amount;

    public CutsomerDetails()
    {

    }

    public CutsomerDetails(int bitmap, String amount, String name, String time) {
        Bitmap = bitmap;
        this.amount = amount;
        this.name = name;
        this.time = time;
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
}
