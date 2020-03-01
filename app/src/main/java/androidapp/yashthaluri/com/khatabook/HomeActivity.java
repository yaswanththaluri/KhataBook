package androidapp.yashthaluri.com.khatabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import androidapp.yashthaluri.com.khatabook.Model.CutsomerDetails;
import androidapp.yashthaluri.com.khatabook.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    ArrayList<CutsomerDetails> customerdetailsModel;
    CustomerHomeAdapter customerHomeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        binding.detailRecyclerview.setHasFixedSize(true);
        binding.detailRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        customerHomeAdapter= new CustomerHomeAdapter(this, customerdetailsModel);
        binding.detailRecyclerview.setAdapter(customerHomeAdapter);
        data();

    }

    private void data() {
        customerdetailsModel.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"0","Manikyapavan,","30 Minutes ago"));
        customerdetailsModel.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"0","Manikyapavan,","30 Minutes ago"));
        customerdetailsModel.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"0","Manikyapavan,","30 Minutes ago"));
        customerdetailsModel.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"0","Manikyapavan,","30 Minutes ago"));
        customerdetailsModel.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"0","Manikyapavan,","30 Minutes ago"));
        customerdetailsModel.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"0","Manikyapavan,","30 Minutes ago"));
        customerdetailsModel.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"0","Manikyapavan,","30 Minutes ago"));
        customerdetailsModel.add(new CutsomerDetails(R.drawable.ic_home_black_16dp,"0","Manikyapavan,","30 Minutes ago"));

    }
}
