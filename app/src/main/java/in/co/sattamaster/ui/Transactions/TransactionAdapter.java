package in.co.sattamaster.ui.Transactions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.co.sattamaster.R;
import in.co.sattamaster.ui.AllBids.BidsDetailsActivity;
import in.co.sattamaster.ui.base.Constants;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<TransactionData> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    TransactionAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();

    }

    public void addAll(List<TransactionData> moveResults) {
        for (TransactionData result : moveResults) {
            add(result);
        }


    }

    public void add(TransactionData r) {
        mData.add(r);
        notifyItemInserted(mData.size() - 1);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_transaction, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.date_trans.setText(String.valueOf("Date " +" : " + mData.get(position).getCreatedAt().toString()) );
        holder.trans_value.setText(String.valueOf( mData.get(position).getAmount()) );
        holder.trans_type.setText(String.valueOf("Type " + " : " + mData.get(position).getType() ) );

        if (mData.get(position).getSenderUser()!=null){
            holder.sender_name.setText(String.valueOf("Sender " + " : " + mData.get(position).getSenderUser().getName() ) );
            holder.moderator_name_trans.setText(String.valueOf("Moderator " +" : " + mData.get(position).getSenderUser().getProfile().getModerator().getName()) );
        } else {
            holder.sender_name.setText(String.valueOf("Sender " + " : "  +"Owner") );
            holder.moderator_name_trans.setText(String.valueOf("Moderator " +" : " + "owner") );
        }


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    // stores and recycles views as they are scrolled off screen

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView trans_value; // amount

        private TextView date_trans; // date

        private TextView moderator_name_trans; // moderator name

        private TextView trans_type; // transaction type

        private TextView sender_name; // sender_name

        ViewHolder(View itemView) {
            super(itemView);
            trans_value = itemView.findViewById(R.id.trans_value);
            date_trans = itemView.findViewById(R.id.date_trans);
            moderator_name_trans = itemView.findViewById(R.id.moderator_name_trans);
            trans_type = itemView.findViewById(R.id.trans_type);
            sender_name = itemView.findViewById(R.id.sender_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
          //  if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

            // Intent intent = new Intent(view.getContext(), BidsDetailsActivity.class);
           // intent.putExtra(Constants.BIDSET_ID, mData.get(getAdapterPosition()).getBidsetId());

          //  view.getContext().startActivity(intent);
        }
    }

    // convenience method for getting data at click position
  //  String getItem(int id) {
    //    return mData.get(id);
   // }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }
}
