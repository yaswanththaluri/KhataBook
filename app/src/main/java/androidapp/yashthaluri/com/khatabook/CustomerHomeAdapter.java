package androidapp.yashthaluri.com.khatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidapp.yashthaluri.com.khatabook.Models.CutsomerDetails;

public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.CustomerViewHolder> {
    Context context;
        ArrayList<CutsomerDetails> cutsomerDetails;

    public CustomerHomeAdapter(Context context, ArrayList<CutsomerDetails> cutsomerDetails) {
        this.context = context;
        this.cutsomerDetails = cutsomerDetails;
    }

    @NonNull
    @Override
    public CustomerHomeAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_transactionlist,parent,false);
        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHomeAdapter.CustomerViewHolder holder, int position) {
        holder.PersonImage.setImageResource(cutsomerDetails.get(position).getBitmap());
        holder.personName.setText(cutsomerDetails.get(position).getName());
        holder.time.setText(cutsomerDetails.get(position).getTime());
         holder.Amount.setText(cutsomerDetails.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return cutsomerDetails.size();
    }
    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        ImageView PersonImage;
        TextView personName,time,Amount;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            personName=itemView.findViewById(R.id.digital_accountname);
            PersonImage = itemView.findViewById(R.id.detail_user_image);
            time =itemView.findViewById(R.id.time);
            Amount=itemView.findViewById(R.id.amountAdded);
        }
    }
}
