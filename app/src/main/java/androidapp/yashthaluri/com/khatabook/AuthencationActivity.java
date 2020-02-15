package androidapp.yashthaluri.com.khatabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import androidapp.yashthaluri.com.khatabook.databinding.ActivityAuthencationBinding;

public class AuthencationActivity extends AppCompatActivity {
    ActivityAuthencationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_authencation);
       binding.start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               binding.outerMobilelayout.setVisibility(View.VISIBLE);
               binding.relative1.setVisibility(View.INVISIBLE);
           }
       });
       binding.get.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               binding.layoutMobile.setVisibility(View.INVISIBLE);
               binding.layoutMobile1.setVisibility(View.VISIBLE);


           }
       });
       binding.submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent= new Intent(AuthencationActivity.this,MainActivity.class);
               startActivity(intent);
           }
       });

    }
}
