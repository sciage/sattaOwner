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

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.AddModerator.ListOfModeratorActivity;
import in.co.sattamaster.ui.AddModerator.ListOfModeratorAdapter;
import in.co.sattamaster.ui.History.HistoryPojo;
import in.co.sattamaster.ui.Homepage.GetAllUsers;
import in.co.sattamaster.ui.Homepage.ModeratorProfile;
import in.co.sattamaster.ui.InfiniteScrollProvider;
import in.co.sattamaster.ui.OnLoadMoreListener;
import in.co.sattamaster.ui.base.BaseActivity;
import in.co.sattamaster.ui.login.UserProfile;

public class AllUsersActivity extends BaseActivity implements AllUsersMvpView, AllUsersAdapter.ItemClickListener, OnLoadMoreListener {

    @Inject
    AllUsersMvpPresenter<AllUsersMvpView> mPresenter;

    private Button search_bid_button;
    private AllUsersAdapter adapter;

    RecyclerView recyclerView;

    @BindView(R.id.listUser_progressbar) View progressFrame;
    private GetAllUsers response;



    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(AllUsersActivity.this);

        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("All Users");

        // set up the RecyclerView
        recyclerView = findViewById(R.id.allusers_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new AllUsersAdapter(this);

        recyclerView.setAdapter(adapter);
        InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView,this);

        adapter.setClickListener(this);


        try {
            progressFrame.setVisibility(View.VISIBLE);
            mPresenter.getAllUsers(preferences, currentPage);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void getAllUsers(GetAllUsers response) {
        this.response = response;

        adapter.addAll(response.getData());

        progressFrame.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onLoadMore() {
        if (response.getTo().equalsIgnoreCase("20")){

            try {
                currentPage += 1;
                progressFrame.setVisibility(View.VISIBLE);

                mPresenter.getAllUsers(preferences, currentPage);

                //  loadNextPage();
                //  isLoading = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
