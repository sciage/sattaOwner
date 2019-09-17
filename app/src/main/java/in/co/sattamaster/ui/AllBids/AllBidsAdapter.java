package in.co.sattamaster.ui.AllBids;

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
import in.co.sattamaster.ui.Withdraw.WithdrawActivity;
import in.co.sattamaster.ui.base.Constants;


public class AllBidsAdapter extends RecyclerView.Adapter<AllBidsAdapter.ViewHolder> {
    private List<BidsData> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    AllBidsAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();

    }

    public void addAll(List<BidsData> moveResults) {
        for (BidsData result : moveResults) {
            add(result);
        }


    }

    public void add(BidsData r) {
        mData.add(r);
        notifyItemInserted(mData.size() - 1);


    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bid_location.setText(String.valueOf("Bid Type " +" : " + getLocationName(mData.get(position).getType())) );
        holder.bid_type.setText(String.valueOf("User Win " +" : " + getDidWin(mData.get(position).getDidWin())) );
        holder.bid_value.setText(String.valueOf( mData.get(position).getCoinBalanceCost()) );
        holder.bid_number.setText(String.valueOf("Bid Time " + " : " + mData.get(position).getCreatedAt() ) );
        holder.bid_time.setText(String.valueOf("Bid ID " + " : " + mData.get(position).getId() ) );
    }

    private String getLocationName(String locationName){
        if (locationName.equalsIgnoreCase("JODI")){
            return "COMBINATION";
        } else return locationName;
    }

    private String getDidWin(String win){
        if (win==null){
            return "LOSS";
        } else if (win.equalsIgnoreCase("1")){
            return "WIN";

        } else {
            return "No Result";

        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView bid_location;
        private TextView bid_type;
        private TextView bid_time;
        private TextView bid_number;
        private TextView bid_value;

        ViewHolder(View itemView) {
            super(itemView);
            bid_location = itemView.findViewById(R.id.bid_location);
            bid_type = itemView.findViewById(R.id.bid_type);
            bid_time = itemView.findViewById(R.id.bid_time);
            bid_number = itemView.findViewById(R.id.bid_number);
            bid_value = itemView.findViewById(R.id.bid_value);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

            Intent intent = new Intent(view.getContext(), BidsDetailsActivity.class);
            intent.putExtra(Constants.BIDSET_ID, mData.get(getAdapterPosition()).getBidsetId());

            view.getContext().startActivity(intent);
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
