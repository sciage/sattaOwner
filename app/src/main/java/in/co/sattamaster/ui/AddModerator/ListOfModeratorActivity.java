package in.co.sattamaster.ui.AddModerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.History.HistoryPojo;
import in.co.sattamaster.ui.InfiniteScrollProvider;
import in.co.sattamaster.ui.OnLoadMoreListener;
import in.co.sattamaster.ui.base.BaseActivity;
import in.co.sattamaster.ui.login.AllModerators;

public class ListOfModeratorActivity extends BaseActivity implements ListOfModeratorMvpView  {

    @Inject
    ListOfModeratorMvpPresenter<ListOfModeratorMvpView> mPresenter;

    ListOfModeratorAdapter adapter;

    @BindView(R.id.listModerator_progressbar) View progressFrame;


    RecyclerView recyclerView;


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

        // set up the RecyclerView
        recyclerView = findViewById(R.id.moderator_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new ListOfModeratorAdapter(this);

        recyclerView.setAdapter(adapter);


        try {
            progressFrame.setVisibility(View.VISIBLE);
            mPresenter.getAllModerator(preferences);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void getListAllGroups(List<AllModerators> response) {

        adapter.addAll(response);

        progressFrame.setVisibility(View.INVISIBLE);


    }
}
