package in.co.sattamaster.ui.AllUsers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.co.sattamaster.R;
import in.co.sattamaster.ui.History.HistoryPojo;
import in.co.sattamaster.ui.base.BaseActivity;

public class AllUsersActivity extends BaseActivity implements AllUsersMvpView, AllUsersAdapter.ItemClickListener {

    @Inject
    AllUsersMvpPresenter<AllUsersMvpView> mPresenter;

    private Button search_bid_button;
    private AllUsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("All Users");

        List<HistoryPojo> animalNames = new ArrayList<>();
        animalNames.add(new HistoryPojo("Faridabad", "Andar", "17-07-2019", "1,2,3,4,5", "1000"));
        animalNames.add(new HistoryPojo("Faridabad", "Andar", "17-07-2019", "1,2,3,4,5", "1000"));
        animalNames.add(new HistoryPojo("Faridabad", "Andar", "17-07-2019", "1,2,3,4,5", "1000"));
        animalNames.add(new HistoryPojo("Faridabad", "Andar", "17-07-2019", "1,2,3,4,5", "1000"));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.allusers_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllUsersAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setUp() {

    }

    private List<ResultPojo> getMovieList() {
        List<ResultPojo> movieList = new ArrayList<>();
        // src Wikipedia
        movieList.add(new ResultPojo("harish", "8800128259", "1000"));
        movieList.add(new ResultPojo("Ravi Rao", "8800128259", "1000"));
        movieList.add(new ResultPojo("Devesh", "8800128259", "1000"));


        return movieList;
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
