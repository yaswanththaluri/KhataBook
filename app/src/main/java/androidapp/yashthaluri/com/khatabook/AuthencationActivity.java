package androidapp.yashthaluri.com.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import androidapp.yashthaluri.com.khatabook.databinding.ActivityAuthencationBinding;

public class AuthencationActivity extends AppCompatActivity {
    ActivityAuthencationBinding binding;
    private String mVerificationId;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_authencation);

       mAuth = FirebaseAuth.getInstance();
       binding.start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               binding.outerMobilelayout.setVisibility(View.VISIBLE);
               binding.relative1.setVisibility(View.INVISIBLE);
           }
       });
       progressDialog =  new ProgressDialog(this);
       progressDialog.setCanceledOnTouchOutside(false);

       database = FirebaseDatabase.getInstance();
       databaseReference = database.getReference();

       binding.get.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String mobileNo = binding.authMobileNo.getText().toString();
               if (mobileNo.length()==10)
               {
                   binding.otpText.setText("Code sent to "+mobileNo);
                   progressDialog.setMessage("Sending OTP..Please wait!");
                   progressDialog.show();
                   binding.layoutMobile.setVisibility(View.INVISIBLE);
                   binding.layoutMobile1.setVisibility(View.VISIBLE);
                   sendVeificationMessage(mobileNo);
               }
               else
               {
                   Toast.makeText(AuthencationActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
               }


           }
       });
       binding.submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               verifyVerificationCode(binding.authOtp.getText().toString());
           }
       });

    }

    public void sendVeificationMessage(String mobileno)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobileno,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                progressDialog.setMessage("Verifying OTP..Please wait!");
                progressDialog.show();
                binding.authOtp.setText(code);
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            progressDialog.cancel();
            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {
        //creating the credential
        progressDialog.setMessage("Verifying code...!");
        progressDialog.show();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(AuthencationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.cancel();
                            //verification successful we will start the profile activity
                            Toast.makeText(AuthencationActivity.this, "Auth Success", Toast.LENGTH_SHORT).show();


                            Intent i = new Intent(AuthencationActivity.this, MainActivity.class);
                            startActivity(i);

                        } else {

                            //verification unsuccessful.. display an error message
                            progressDialog.cancel();
                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Toast.makeText(AuthencationActivity.this, "Wrong code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
