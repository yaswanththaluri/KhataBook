package androidapp.yashthaluri.com.khatabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidapp.yashthaluri.com.khatabook.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

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

    }
}
