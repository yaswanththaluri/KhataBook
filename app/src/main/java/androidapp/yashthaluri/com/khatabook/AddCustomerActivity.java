package androidapp.yashthaluri.com.khatabook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
