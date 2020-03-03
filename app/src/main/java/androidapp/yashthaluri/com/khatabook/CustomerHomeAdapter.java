package androidapp.yashthaluri.com.khatabook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidapp.yashthaluri.com.khatabook.Models.CutsomerDetails;

public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.CustomerViewHolder> {
    Context context;
    ArrayList<CutsomerDetails> customerdetailsModel;

    public CustomerHomeAdapter(Context context, ArrayList<CutsomerDetails> customerdetailsModel) {
        this.context = context;
        this.customerdetailsModel = customerdetailsModel;
    }

    @NonNull
    @Override
    public CustomerHomeAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_transactionlist,parent,false);
        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHomeAdapter.CustomerViewHolder holder, final int position) {
        Log.i("position", ""+position);
        Log.i("List", ""+customerdetailsModel);
        holder.PersonImage.setImageResource(customerdetailsModel.get(position).getBitmap());

        int amountTot = Integer.parseInt(customerdetailsModel.get(position).getAmount());

        holder.personName.setText(customerdetailsModel.get(position).getName());
        holder.time.setText(customerdetailsModel.get(position).getTime());

        if (amountTot>=0)
        {
            holder.Amount.setText(customerdetailsModel.get(position).getAmount());
            holder.AmountRed.setVisibility(View.INVISIBLE);
            holder.Amount.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.AmountRed.setText(""+(-1*amountTot));
            holder.Amount.setVisibility(View.INVISIBLE);
            holder.AmountRed.setVisibility(View.VISIBLE);
        }


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, TrasactionAddActivity.class);
                i.putExtra("customerUID", customerdetailsModel.get(position).getUid());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerdetailsModel.size();
    }
    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        ImageView PersonImage;
        TextView personName,time,Amount, AmountRed;
        LinearLayout item;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            personName=itemView.findViewById(R.id.digital_accountname);
            PersonImage = itemView.findViewById(R.id.detail_user_image);
            time =itemView.findViewById(R.id.time);
            Amount=itemView.findViewById(R.id.amount);
            item = itemView.findViewById(R.id.custItem);
            AmountRed = itemView.findViewById(R.id.amountRed);
        }
    }
}
