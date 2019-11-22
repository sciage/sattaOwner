package in.co.sattamaster.ui.Transactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.co.sattamaster.R;
import in.co.sattamaster.ui.AllBids.AllBidsAdapter;
import in.co.sattamaster.ui.InfiniteScrollProvider;
import in.co.sattamaster.ui.OnLoadMoreListener;
import in.co.sattamaster.ui.RevealNumber.RevealNumberActivity;
import in.co.sattamaster.ui.RevealNumber.RevealNumberMvpPresenter;
import in.co.sattamaster.ui.RevealNumber.RevealNumberMvpView;
import in.co.sattamaster.ui.base.BaseActivity;

public class TransactionActivity extends BaseActivity implements TransactionMvpView, OnLoadMoreListener {

    @Inject
    TransactionMvpPresenter<TransactionMvpView> mPresenter;

    private TransactionAdapter adapter;

    @BindView(R.id.transaction_progressbar) View progressFrame;


    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;

    private BaseTransaction response;



    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(TransactionActivity.this);

        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Transactions");

        // set up the RecyclerView
        recyclerView = findViewById(R.id.transaction_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new TransactionAdapter(this);

        recyclerView.setAdapter(adapter);
        InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView,this);


        try {
            progressFrame.setVisibility(View.VISIBLE);
            mPresenter.getTransactions(preferences, currentPage);
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    protected void setUp() {

    }

    @Override
    public void getTransaction(BaseTransaction response) {

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

                mPresenter.getTransactions(preferences, currentPage);

                //  loadNextPage();
                //  isLoading = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
