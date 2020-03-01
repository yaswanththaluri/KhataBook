package androidapp.yashthaluri.com.khatabook.Models;

public class CutsomerDetails {
    int Bitmap,amount;
    String name,time;

    public CutsomerDetails(int bitmap, int amount, String name, String time) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
