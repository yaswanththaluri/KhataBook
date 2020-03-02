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

import androidapp.yashthaluri.com.khatabook.Models.CutsomerDetails;
import androidapp.yashthaluri.com.khatabook.Models.ProfileHelper;
import androidapp.yashthaluri.com.khatabook.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    ArrayList<CutsomerDetails> cutsomerDetails = new ArrayList<CutsomerDetails>();
    CustomerHomeAdapter customerHomeAdapter;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        auth = FirebaseAuth.getInstance();
//        user = auth.getCurrentUser();
//
//        database = FirebaseDatabase.getInstance();
//        reference = database.getReference();

        binding= DataBindingUtil.setContentView(this,R.layout.activity_home);
        binding.MoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutHome.setVisibility(View.INVISIBLE);
                binding.layoutMore.setVisibility(View.VISIBLE);
            }
        });
        binding.HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutMore.setVisibility(View.INVISIBLE);
                binding.layoutHome.setVisibility(View.VISIBLE);



            }
        });
        binding.AddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this,AddCustomerActivity.class);
                startActivity(intent);
            }
        });

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ProfileActivty.class);
                startActivity(i);
            }
        });

        binding.detailRecyclerview.setHasFixedSize(true);
        customerHomeAdapter=new CustomerHomeAdapter(this, cutsomerDetails);
        binding.detailRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.detailRecyclerview.setAdapter(customerHomeAdapter);
        data();


    }

    private void data() {
      cutsomerDetails.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"1000","Manikyapavan","40 Minutes ago"));

        }

//    public void populateProfileData()
//    {
//        reference.child("businesses").child(user.getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ProfileHelper helper = dataSnapshot.getValue(ProfileHelper.class);
//                Integer money = Integer.parseInt(helper.getMoney());
//                if (money>=0)
//                {
//                    String m = ""+(money);
//                    binding.moneyGive.setText(m);
//                }
//                else
//                {
//                    String m = ""+(-1*money);
//                    binding.moneyGet.setText(m);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        populateProfileData();
//    }
}
