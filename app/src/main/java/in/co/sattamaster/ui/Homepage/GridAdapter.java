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
import in.co.sattamaster.ui.Withdraw.WithdrawActivity;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter{
    private List<LocationPojo> dataSet = new ArrayList<>();


    public static class Item{
        public String text;
        public int resId;
    }

    public void addAll(List<LocationPojo> moveResults) {
        for (LocationPojo result : moveResults) {
            add(result);
            notifyDataSetChanged();
        }


    }

    public void add(LocationPojo r) {
        dataSet.add(r);

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

        if (position == 0){
            box_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AddModeratorActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        } else if (position == 1){
            box_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AllUsersActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        } else if (position == 2){
            box_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AddCoinsActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        } else if (position == 3){
            box_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), HistoryActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        } else if (position == 4){
            box_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AddLocationActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }


      //  ImageView image = (ImageView) convertView.findViewById(R.id.icon);
      //  TextView text = (TextView) convertView.findViewById(R.id.text);
      //  Item item = (Item) getItem(position);
       // image.setImageResource(item.resId);
        // text.setText(item.text);
        return convertView;
    }
}
