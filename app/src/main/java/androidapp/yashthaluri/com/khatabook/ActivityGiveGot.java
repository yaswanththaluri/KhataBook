package androidapp.yashthaluri.com.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidapp.yashthaluri.com.khatabook.Models.CustomerProfileHelper;
import androidapp.yashthaluri.com.khatabook.Models.CustomerTransactionsHelper;
import androidapp.yashthaluri.com.khatabook.Models.ProfileHelper;
import androidapp.yashthaluri.com.khatabook.databinding.ActivityGiveGotBinding;

public class ActivityGiveGot extends AppCompatActivity {

    ActivityGiveGotBinding binding;
    private FirebaseAuth auth;
    private FirebaseUser user;

    private DatabaseReference reference;
    private FirebaseDatabase database;

    private String status;
    private String custId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_give_got);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        custId = getIntent().getStringExtra("customerUID");
        status = getIntent().getStringExtra("transType");

        binding.calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = binding.amount.getText().toString();
                String  intrest = binding.intrest.getText().toString();

                Integer p = Integer.parseInt(amount);
                Integer t = 1;
                Integer r = Integer.parseInt(intrest);

                String finalAmount = "Total Amount : "+(p+(p*t*r));

                binding.totAmount.setText(finalAmount);
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = binding.amount.getText().toString();
                String  intrest = binding.intrest.getText().toString();
                String desc = binding.billdetails.getText().toString();

                Integer p = Integer.parseInt(amount);
                Integer t = 1;
                Integer r = Integer.parseInt(intrest);

                pushDataToDatabase(""+(p+(p*t*r)), desc, intrest);
            }
        });
    }


    public void pushDataToDatabase(final String amount, String desc, String  intrest)
    {
        final CustomerTransactionsHelper helper = new CustomerTransactionsHelper(amount, status, desc, intrest);
        reference.child("CustomerTransactions").child(user.getUid()).child(custId).push().setValue(helper);
        final int[] temp1 = {0, 0};

        reference.child("khatas").child(user.getUid()).child(custId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CustomerProfileHelper helper1 = dataSnapshot.getValue(CustomerProfileHelper.class);
                Integer prevAmount = Integer.parseInt(helper1.getMoney());
                if (status.equals("GAVE"))
                {
                    prevAmount -= Integer.parseInt(amount);
                }
                else
                {
                    prevAmount += Integer.parseInt(amount);
                }
                String fAmount = ""+prevAmount;
                helper1.setMoney(fAmount);
                if (temp1[0] == 0)
                reference.child("khatas").child(user.getUid()).child(custId).setValue(helper1);
                temp1[0]++;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.child("businesses").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProfileHelper profileHelper = dataSnapshot.getValue(ProfileHelper.class);
                Integer prevAmount = Integer.parseInt(profileHelper.getMoney());
                if (status.equals("GAVE"))
                {
                    prevAmount -= Integer.parseInt(amount);
                }
                else
                {
                    prevAmount += Integer.parseInt(amount);
                }
                String fAmount = ""+prevAmount;
                profileHelper.setMoney(fAmount);
                if (temp1[1] == 0)
                    reference.child("businesses").child(user.getUid()).setValue(profileHelper);
                temp1[1]++;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
