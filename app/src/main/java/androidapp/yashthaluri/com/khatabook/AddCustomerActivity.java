package androidapp.yashthaluri.com.khatabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidapp.yashthaluri.com.khatabook.Models.CustomerProfileHelper;
import androidapp.yashthaluri.com.khatabook.databinding.ActivityAddCustomerBinding;

public class AddCustomerActivity extends AppCompatActivity {

    ActivityAddCustomerBinding binding;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_customer);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.cutomername.getText().toString();
                String mNo = binding.mobileNumber.getText().toString();

                if (!(name.equals("") || mNo.equals("")))
                {
                    addCustomerToDatabase(name, mNo);
                }
                else
                {
                    Toast.makeText(AddCustomerActivity.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void addCustomerToDatabase(String customerName, String mobileNumber)
    {
        reference = reference.child("khatas").child(user.getUid()).push();

        CustomerProfileHelper helper = new CustomerProfileHelper(customerName, mobileNumber, "0", reference.getKey());

        reference.setValue(helper);

        Toast.makeText(AddCustomerActivity.this, "Customer Added Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}
