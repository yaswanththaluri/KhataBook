package androidapp.yashthaluri.com.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidapp.yashthaluri.com.khatabook.Models.ProfileHelper;
import androidapp.yashthaluri.com.khatabook.databinding.ActivityProfileBinding;

public class ProfileActivty extends AppCompatActivity {

    ActivityProfileBinding binding;
    private FirebaseAuth auth;
    private FirebaseUser user;

    private DatabaseReference reference;
    private FirebaseDatabase database;

    private ProfileHelper profileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("businesses").child(user.getUid());

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.fullName.getText().toString();
                String bName = binding.businessnameEdittext.getText().toString();

                if (!name.equals("") && !bName.equals(""))
                {
                    saveDetails(name, bName);
                }
                else
                {
                    Toast.makeText(ProfileActivty.this, "Details should not be empty", Toast.LENGTH_SHORT).show();
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


    public void saveDetails(String ownerName, String bussName)
    {
        profileData.setOwnerName(ownerName);
        profileData.setBusinessName(bussName);

        reference.setValue(profileData);

        Toast.makeText(ProfileActivty.this, "Details saved successfully", Toast.LENGTH_SHORT).show();
    }

    private void populateData()
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProfileHelper helper = dataSnapshot.getValue(ProfileHelper.class);
                profileData = helper;
                binding.fullName.setText(helper.getOwnerName());
                binding.businessnameEdittext.setText(helper.getBusinessName());
                binding.mobilenumber.setText(helper.getPhoneNumber());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateData();
    }
}
