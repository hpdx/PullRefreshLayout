package com.yan.refreshloadlayouttest.testactivity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yan.pullrefreshlayout.PullRefreshLayout;
import com.yan.pullrefreshlayout.PullRefreshView;
import com.yan.refreshloadlayouttest.R;

public class CommonActivity3 extends Activity {
    private static final String TAG = "CommonActivity1";

    protected PullRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity3);
        initListView();
        initRefreshLayout();
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.autoRefresh();
            }
        }, 150);
    }

    private void initListView() {
        ListView listView = (ListView) findViewById(R.id.lv_data);
        listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{
                "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
                , "test"
        }));
    }

    private void initRefreshLayout() {
        refreshLayout = (PullRefreshLayout) findViewById(R.id.refreshLayout);
//        refreshLayout.setOverScrollDampingRatio(0.4f);
//        refreshLayout.setAdjustTwinkDuring(2);
//        refreshLayout.setPullTwinkEnable(false);
        refreshLayout.setLoadMoreEnable(true);
//        refreshLayout.setRefreshEnable(false);
//        refreshLayout.setAutoLoadingEnable(true);
//        refreshLayout.setDuringAdjustValue(10f);// 动画执行时间调节，越大动画执行越慢
        // 刷新或加载完成后回复动画执行时间，为-1时，根据setDuringAdjustValue（）方法实现
//        refreshLayout.setRefreshBackTime(300);
//        refreshLayout.setPullViewHeight(400);// 设置头部和底部的高度
//        refreshLayout.setDragDampingRatio(0.6f);// 阻尼系数
//        refreshLayout.setPullFlowHeight(400);// 拖拽最大范围，为-1时拖拽范围不受限制
//        refreshLayout.setRefreshEnable(false);
        refreshLayout.setHeaderView(new PullRefreshView(getBaseContext()) {
            TextView tv;

            @Override
            protected void initView() {
                tv = (TextView) findViewById(R.id.title);
            }

            @Override
            protected int contentView() {
                return R.layout.refresh_view;
            }

            @Override
            public void onPullChange(float percent) {
                Log.e(TAG, "onPullChange: refresh " + percent);
            }

            @Override
            public void onPullReset() {
                tv.setText("下拉");
            }

            @Override
            public void onPullHoldTrigger() {
                tv.setText("释放刷新");
            }

            @Override
            public void onPullHoldUnTrigger() {
                tv.setText("下拉");
            }

            @Override
            public void onPullHolding() {
                tv.setText("正在刷新");
            }

            @Override
            public void onPullFinish() {
                tv.setText("刷新完成");
            }
        });

        refreshLayout.setFooterView(new PullRefreshView(getBaseContext()) {
            TextView tv;

            @Override
            protected void initView() {
                tv = (TextView) findViewById(R.id.title);
            }

            @Override
            protected int contentView() {
                return R.layout.load_more_view;
            }

            @Override
            public void onPullChange(float percent) {
//                Log.e(TAG, "onPullChange: refresh " + percent);
            }

            @Override
            public void onPullReset() {
                tv.setText("上拉");
            }

            @Override
            public void onPullHoldTrigger() {
                tv.setText("释放加载");
            }

            @Override
            public void onPullHoldUnTrigger() {
                tv.setText("上拉");
            }

            @Override
            public void onPullHolding() {
                tv.setText("正在加载");
            }

            @Override
            public void onPullFinish() {
                tv.setText("加载完成");
            }
        });

        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "refreshLayout onRefresh: ");
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public void onLoading() {
                Log.e(TAG, "refreshLayout onLoading: ");
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.loadMoreComplete();
                    }
                }, 3000);
            }
        });
    }
}