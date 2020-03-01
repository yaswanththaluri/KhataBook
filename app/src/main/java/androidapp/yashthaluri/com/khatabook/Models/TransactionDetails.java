package androidapp.yashthaluri.com.khatabook.Models;

public class TransactionDetails {
   String Date;
   int YouGave,YouGot;

    public TransactionDetails(String date, int youGave, int youGot) {
        Date = date;
        YouGave = youGave;
        YouGot = youGot;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getYouGave() {
        return YouGave;
    }

    public void setYouGave(int youGave) {
        YouGave = youGave;
    }

    public int getYouGot() {
        return YouGot;
    }

    public void setYouGot(int youGot) {
        YouGot = youGot;
    }
}
