package in.co.sattamaster.ui.Homepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.co.sattamaster.R;
import in.co.sattamaster.SquareLayout;
import in.co.sattamaster.ui.AddCoins.AddCoinsActivity;
import in.co.sattamaster.ui.AddModerator.AddModeratorActivity;
import in.co.sattamaster.ui.AllUsers.AllUsersActivity;
import in.co.sattamaster.ui.Contact.ContactUsActivity;
import in.co.sattamaster.ui.History.HistoryActivity;
import in.co.sattamaster.ui.Location.AddLocationActivity;
import in.co.sattamaster.ui.Result.ResultActivity;
import in.co.sattamaster.ui.RevealNumber.RevealNumberActivity;
import in.co.sattamaster.ui.Withdraw.WithdrawActivity;
import in.co.sattamaster.ui.base.Constants;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter{
    private UserObject dataSet;

    public void addAll(UserObject moveResults) {
        dataSet = moveResults;
    }

    public static class Item{
        public String text;
        public int resId;
    }

    private List<Item> mItems = new ArrayList<Item>();
    private Context mContext;
    public GridAdapter(Context context) {

        Item object = new Item();
        object.text = "Add Moderator";
        mItems.add(object);
        notifyDataSetChanged();


        Item object02 = new Item();
        object02.text = "All Users";
        mItems.add(object02);
        notifyDataSetChanged();


        Item object03 = new Item();
        object03.text = "Add Coins";
        mItems.add(object03);
        notifyDataSetChanged();


        Item object04 = new Item();
        object04.text = "Send Money";
        mItems.add(object04);
        notifyDataSetChanged();

        Item object05 = new Item();
        object05.text = "Add Location";
        mItems.add(object05);
        notifyDataSetChanged();

        Item object06 = new Item();
        object06.text = "Reveal Number";
        mItems.add(object06);
        notifyDataSetChanged();

        Item object07 = new Item();
        object07.text = "Withdrawal Request";
        mItems.add(object07);
        notifyDataSetChanged();

        mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mainpage_item, null);
        }

        TextView location_id = (TextView) convertView.findViewById(R.id.location_id);

        SquareLayout box_back = (SquareLayout) convertView.findViewById(R.id.mainPageBack);

        location_id.setText(mItems.get(position).text);

        switch (position){
            case 0:

                box_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), AddModeratorActivity.class);
                        intent.putExtra(Constants.WALLET_BALANCE, dataSet.getUser().getProfile().getCoin_balance());

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 1:

                box_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), AllUsersActivity.class);
                        intent.putExtra(Constants.WALLET_BALANCE, dataSet.getUser().getProfile().getCoin_balance());

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 2:
                box_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), AddCoinsActivity.class);
                        intent.putExtra(Constants.WALLET_BALANCE, dataSet.getUser().getProfile().getCoin_balance());

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 3:
                box_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), HistoryActivity.class);
                        intent.putExtra(Constants.WALLET_BALANCE, dataSet.getUser().getProfile().getCoin_balance());

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 4:
                box_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), AddLocationActivity.class);
                        intent.putExtra(Constants.WALLET_BALANCE, dataSet.getUser().getProfile().getCoin_balance());

                        v.getContext().startActivity(intent);
                    }
                });

                break;
            case 5:
                box_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), RevealNumberActivity.class);
                        intent.putExtra(Constants.WALLET_BALANCE, dataSet.getUser().getProfile().getCoin_balance());

                        v.getContext().startActivity(intent);
                    }
                });

                break;
        }


      //  ImageView image = (ImageView) convertView.findViewById(R.id.icon);
      //  TextView text = (TextView) convertView.findViewById(R.id.text);
      //  Item item = (Item) getItem(position);
       // image.setImageResource(item.resId);
        // text.setText(item.text);
        return convertView;
    }
}
