package androidapp.yashthaluri.com.khatabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

import androidapp.yashthaluri.com.khatabook.Models.TransactionDetails;
import androidapp.yashthaluri.com.khatabook.databinding.ActivityTrasactionAddBinding;

public class TrasactionAddActivity extends AppCompatActivity {
    ArrayList<TransactionDetails> transactionDetails=new ArrayList<TransactionDetails>();
    TransactionDetailsAdapter transactionDetailsAdapter;
    ActivityTrasactionAddBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_trasaction_add);
        binding.transactionDetailsrecyclerview.setHasFixedSize(true);
        transactionDetailsAdapter=new TransactionDetailsAdapter(transactionDetails,this);
        binding.transactionDetailsrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.transactionDetailsrecyclerview.setAdapter(transactionDetailsAdapter);
        data();

    }

    private void data() {
        transactionDetails.add(new TransactionDetails("01:03:2020","0","10000"));

    }
}
