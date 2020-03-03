package androidapp.yashthaluri.com.khatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidapp.yashthaluri.com.khatabook.Models.TransactionDetails;

public class TransactionDetailsAdapter extends RecyclerView.Adapter<TransactionDetailsAdapter.TransactionViewHolder> {
    ArrayList<TransactionDetails> transactionDetails;
    Context context;

    public TransactionDetailsAdapter(ArrayList<TransactionDetails> transactionDetails, Context context) {
        this.transactionDetails = transactionDetails;
        this.context = context;
    }

    @NonNull
    @Override

    public TransactionDetailsAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V= LayoutInflater.from(context).inflate(R.layout.list_transactiondetailspage,parent,false);

        return new TransactionViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionDetailsAdapter.TransactionViewHolder holder, int position) {
            holder.Date1.setText(transactionDetails.get(position).getDate());
            if (transactionDetails.get(position).getYouGave().equals("GAVE"))
            {
                holder.YouGave.setText(transactionDetails.get(position).getYouGot());
                holder.YouGot.setText("");
            }
            else
            {
                holder.YouGave.setText("");
                holder.YouGot.setText(transactionDetails.get(position).getYouGot());
            }


    }

    @Override
    public int getItemCount() {
        return transactionDetails.size();
    }
    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView Date1;
        TextView YouGave,YouGot;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            Date1 = itemView.findViewById(R.id.date);
            YouGave=itemView.findViewById(R.id.yougave);
            YouGot=itemView.findViewById(R.id.yougot);

        }
    }
}
