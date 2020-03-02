package androidapp.yashthaluri.com.khatabook.Models;

public class TransactionDetails {
   String Date;
   String YouGave,YouGot;

    public TransactionDetails(String date, String youGave, String youGot) {
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

    public String getYouGave() {
        return YouGave;
    }

    public void setYouGave(String youGave) {
        YouGave = youGave;
    }

    public String getYouGot() {
        return YouGot;
    }

    public void setYouGot(String youGot) {
        YouGot = youGot;
    }
}
