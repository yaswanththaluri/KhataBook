package androidapp.yashthaluri.com.khatabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    private Bitmap bitmap;

    private String customerId;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_trasaction_add);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

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

        binding.generatePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = loadBitmapFromView(binding.parent, binding.parent.getWidth(), binding.parent.getHeight());
                createPdf();
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

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }



    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "pdffromlayout.pdf";
        File filePath;
        filePath = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), targetPdf);
        uri = Uri.fromFile(filePath);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();


        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("application/pdf");
        startActivity(Intent.createChooser(shareIntent, "share via"));

    }

    private void openGeneratedPDF(Uri uri){
        File file = new File("pdffromlayout.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(TrasactionAddActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }

}
