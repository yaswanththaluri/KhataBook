package androidapp.yashthaluri.com.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidapp.yashthaluri.com.khatabook.Models.CustomerProfileHelper;
import androidapp.yashthaluri.com.khatabook.Models.CustomerTransactionsHelper;
import androidapp.yashthaluri.com.khatabook.Models.TransactionDetails;
import androidapp.yashthaluri.com.khatabook.databinding.ActivityTrasactionAddBinding;

public class TrasactionAddActivity extends AppCompatActivity {
    ArrayList<TransactionDetails> transactionDetails;
    TransactionDetailsAdapter transactionDetailsAdapter;
    ActivityTrasactionAddBinding binding;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    private String customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_trasaction_add);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        customerId = getIntent().getStringExtra("customerUID");

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        binding.transactionDetailsrecyclerview.setHasFixedSize(true);
        transactionDetails = new ArrayList<>();
        transactionDetailsAdapter=new TransactionDetailsAdapter(transactionDetails,this);
        binding.transactionDetailsrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.transactionDetailsrecyclerview.setAdapter(transactionDetailsAdapter);

        binding.yougaveLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrasactionAddActivity.this, ActivityGiveGot.class);
                i.putExtra("customerUID", customerId);
                i.putExtra("transType", "GAVE");
                startActivity(i);
            }
        });

        binding.yougotLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrasactionAddActivity.this, ActivityGiveGot.class);
                i.putExtra("customerUID", customerId);
                i.putExtra("transType", "GOT");
                startActivity(i);
            }
        });

    }

    private void data() {
        transactionDetails.add(new TransactionDetails("01:03:2020",""+0,""+10000));
        transactionDetails.add(new TransactionDetails("01:03:2020",""+0,""+10000));
        transactionDetails.add(new TransactionDetails("01:03:2020",""+0,""+10000));
        transactionDetails.add(new TransactionDetails("01:03:2020",""+0,""+10000));
        transactionDetails.add(new TransactionDetails("01:03:2020",""+0,""+10000));
        transactionDetails.add(new TransactionDetails("01:03:2020",""+0,""+10000));
    }


    public void populateData()
    {

        reference.child("khatas").child(user.getUid()).child(customerId).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        CustomerProfileHelper helper = dataSnapshot.getValue(CustomerProfileHelper.class);
                        binding.digitalAccountname.setText(helper.getName());
                        binding.personName.setText(helper.getMobileNo());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );

    }

    private void populateTransactionData()
    {
        reference.child("CustomerTransactions").child(user.getUid()).child(customerId).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot i : dataSnapshot.getChildren())
                        {
                            CustomerTransactionsHelper helper = i.getValue(CustomerTransactionsHelper.class);
                            transactionDetails.add(new TransactionDetails(helper.getDate(),helper.getTransType(),helper.getAmount()));
                        }
                        transactionDetailsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        populateData();
        populateTransactionData();
        transactionDetails.clear();
    }
}
