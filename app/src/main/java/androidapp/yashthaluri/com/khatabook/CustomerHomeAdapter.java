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
    ArrayList<CutsomerDetails> customerdetailsModel;

    public CustomerHomeAdapter(Context context, ArrayList<CutsomerDetails> customerdetailsModel) {
        this.context = context;
        this.customerdetailsModel = customerdetailsModel;
    }

    @NonNull
    @Override
    public CustomerHomeAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_customer,parent,false);
        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHomeAdapter.CustomerViewHolder holder, int position) {
        holder.PersonImage.setImageResource(customerdetailsModel.get(position).getBitmap());
        holder.personName.setText(customerdetailsModel.get(position).getName());
        holder.time.setText(customerdetailsModel.get(position).getTime());
        holder.Amount.setText(customerdetailsModel.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return customerdetailsModel.size();
    }
    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        ImageView PersonImage;
        TextView personName,time,Amount;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            personName=itemView.findViewById(R.id.person_name);
            PersonImage = itemView.findViewById(R.id.detail_user_image);
            time =itemView.findViewById(R.id.time);
            Amount=itemView.findViewById(R.id.amount);
        }
    }
}
