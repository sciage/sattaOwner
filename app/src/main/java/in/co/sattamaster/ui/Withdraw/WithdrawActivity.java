package in.co.sattamaster.ui.Withdraw;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.AllUsers.AllUsersAdapter;
import in.co.sattamaster.ui.InfiniteScrollProvider;
import in.co.sattamaster.ui.OnLoadMoreListener;
import in.co.sattamaster.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class WithdrawActivity extends BaseActivity implements WithdrawMvpView, WithdrawAdapter.ItemClickListener, OnLoadMoreListener {

    @Inject
    WithdrawMvpPresenter<WithdrawMvpView> mPresenter;

    private WithdrawAdapter adapter;

    RecyclerView recyclerView;
    private WithdrawPojo response;

    @BindView(R.id.withdraw_progressbar) View progressFrame;


    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(WithdrawActivity.this);


        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Withdraw Request");

        // set up the RecyclerView
        recyclerView = findViewById(R.id.withdraw_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new WithdrawAdapter(this);

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
    public void withdraw(WithdrawPojo response) {
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

                mPresenter.getAllUsers(preferences, currentPage);

                //  loadNextPage();
                //  isLoading = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
