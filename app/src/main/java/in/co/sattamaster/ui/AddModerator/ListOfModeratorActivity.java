package in.co.sattamaster.ui.AddModerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.History.HistoryPojo;
import in.co.sattamaster.ui.base.BaseActivity;

public class ListOfModeratorActivity extends BaseActivity implements ListOfModeratorMvpView, ListOfModeratorAdapter.ItemClickListener {

    @Inject
    ListOfModeratorMvpPresenter<ListOfModeratorMvpView> mPresenter;

    ListOfModeratorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_moderator);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(ListOfModeratorActivity.this);

        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Moderator List");

        // data to populate the RecyclerView with
        List<HistoryPojo> animalNames = new ArrayList<>();
        animalNames.add(new HistoryPojo("Faridabad", "Andar", "17-07-2019", "1,2,3,4,5", "1000"));
        animalNames.add(new HistoryPojo("Faridabad", "Andar", "17-07-2019", "1,2,3,4,5", "1000"));
        animalNames.add(new HistoryPojo("Faridabad", "Andar", "17-07-2019", "1,2,3,4,5", "1000"));
        animalNames.add(new HistoryPojo("Faridabad", "Andar", "17-07-2019", "1,2,3,4,5", "1000"));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.moderator_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListOfModeratorAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
