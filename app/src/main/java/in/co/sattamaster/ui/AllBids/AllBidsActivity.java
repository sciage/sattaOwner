package in.co.sattamaster.ui.AllBids;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.Homepage.GetAllUsers;
import in.co.sattamaster.ui.InfiniteScrollProvider;
import in.co.sattamaster.ui.OnLoadMoreListener;
import in.co.sattamaster.ui.base.BaseActivity;


public class AllBidsActivity extends BaseActivity implements AllBidsMvpView, OnLoadMoreListener {

    @Inject
    AllBidsMvpPresenter<AllBidsMvpView> mPresenter;

    private AllBidsAdapter adapter;

    RecyclerView recyclerView;
    private AllBidsPojo response;

    @BindView(R.id.allbids_progressbar) View progressFrame;

    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bids);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(AllBidsActivity.this);

        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("All Bids");

        // set up the RecyclerView
        recyclerView = findViewById(R.id.allbids_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new AllBidsAdapter(this);

        recyclerView.setAdapter(adapter);
        InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView,this);


        try {
            progressFrame.setVisibility(View.VISIBLE);
            mPresenter.getAllBids(preferences, currentPage);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void getAllBids(AllBidsPojo response) {
        adapter.addAll(response.getData());
        this.response = response;

        progressFrame.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoadMore() {
        if (response.getTo().equalsIgnoreCase("20")){

            try {
                currentPage += 1;
                progressFrame.setVisibility(View.VISIBLE);

                mPresenter.getAllBids(preferences, currentPage);

                //  loadNextPage();
                //  isLoading = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
