package me.fangx.haorefresh;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import me.fangx.library.HaoRecyclerView;
import me.fangx.library.LoadMoreListener;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swiperefresh;
    private HaoRecyclerView hao_recycleview;
    private MyAdapter mAdapter;
    private ArrayList<String> listData = new ArrayList<>();
    private int limit = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        initData();
                        //注意此处
                        hao_recycleview.refreshComplete();
                        swiperefresh.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 3000);

            }
        });

        hao_recycleview = (HaoRecyclerView) findViewById(R.id.hao_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hao_recycleview.setLayoutManager(layoutManager);

        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(this);
        progressView.setIndicatorId(ProgressView.BallPulse);
        progressView.setIndicatorColor(0xff69b3e0);
        hao_recycleview.setFootLoadingView(progressView);

        TextView textView = new TextView(this);
        textView.setText("已经到底啦~");
        hao_recycleview.setFootEndView(textView);

        hao_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        if (listData.size() >= 3 * limit) {
                            hao_recycleview.loadMoreEnd();
                            return;
                        }

                        for (int i = 0; i < limit; i++) {
                            listData.add(i + "");
                        }
                        mAdapter.notifyDataSetChanged();
                        hao_recycleview.loadMoreComplete();

                    }
                }, 2000);
            }
        });

        initData();
        mAdapter = new MyAdapter(listData);
        hao_recycleview.setAdapter(mAdapter);


        hao_recycleview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "click-----position" + i, Toast.LENGTH_SHORT).show();
            }
        });


        hao_recycleview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "long click------position" + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }


    private void initData() {
        listData.clear();
        for (int i = 0; i < limit; i++) {
            listData.add(i + "");
        }
    }


}
