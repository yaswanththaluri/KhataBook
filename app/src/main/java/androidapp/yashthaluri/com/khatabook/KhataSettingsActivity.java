package androidapp.yashthaluri.com.khatabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import androidapp.yashthaluri.com.khatabook.databinding.ActivityKhataSettingsBinding;

public class KhataSettingsActivity extends AppCompatActivity {
    ActivityKhataSettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_khata_settings);
        binding.lienarlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.khataSettingslayout.setVisibility(View.INVISIBLE);
                binding.backupInfolayout.setVisibility(View.VISIBLE);
            }
        });
        binding.linearlayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.khataSettingslayout.setVisibility(View.INVISIBLE);
                binding.deleteKhatalayout.setVisibility(View.VISIBLE);
            }
        });

    }
}
