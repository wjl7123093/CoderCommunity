package com.fred_w.demo.codercommunity.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.fred_w.demo.codercommunity.app.ARoutePath;
import com.fred_w.demo.codercommunity.app.SharepreferenceKey;
import com.fred_w.demo.codercommunity.app.utils.ImageLoader;
import com.fred_w.demo.codercommunity.mvp.model.entity.Active;
import com.fred_w.demo.codercommunity.mvp.model.entity.LoginUser;
import com.fred_w.demo.codercommunity.mvp.model.entity.MyInfo;
import com.fred_w.demo.codercommunity.mvp.ui.adapter.ActiveListAdapter;
import com.fred_w.demo.codercommunity.mvp.ui.adapter.decoration.MDLinearRvDividerDecoration;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.fred_w.demo.codercommunity.di.component.DaggerActiveListComponent;
import com.fred_w.demo.codercommunity.di.module.ActiveListModule;
import com.fred_w.demo.codercommunity.mvp.contract.ActiveListContract;
import com.fred_w.demo.codercommunity.mvp.presenter.ActiveListPresenter;

import com.fred_w.demo.codercommunity.R;
import com.jess.arms.utils.DataHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import org.raphets.roundimageview.RoundImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import static com.fred_w.demo.codercommunity.app.utils.DateUtils.timeLogic;
import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 动态列表页面
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-1-16
 * @update 2017-1-17 增加 RecyclerView 间隔效果
 */
@Route(path = ARoutePath.PATH_ACTIVE_LIST)
public class ActiveListActivity extends BaseActivity<ActiveListPresenter> implements ActiveListContract.View {

    private final static int CONSTANT_PAGE_SIZE = 10;   // 每页条数

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTvToolbarTitle;
    @BindView(R.id.toolbar_back)
    RelativeLayout mBtnToolbarBack;

    @BindView(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemAnimator mItemAnimator;
    private List<Active> mActiveList;
    private ActiveListAdapter mAdapter;
    private int mPageIndex = 1;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerActiveListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .activeListModule(new ActiveListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_active_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mActiveList = new ArrayList<Active>();
        // 初始化 RecyclerView
        initRecyclerView();
        // 初始化 下拉刷新
        initRefresh();
        // 绑定数据
        setAdapter(mActiveList);
        // 设置 RecyclerView 点击项事件
        setItemClickListener();
        // post 获取动态列表
        doPostGetActiveList(mPageIndex);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void showActiveList(List<Active> activeList) {
        finishRefreshing();

        mActiveList.addAll(activeList);
        setAdapter(mActiveList);
    }

    @Override
    public void finishRefreshing() {
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.finishRefresh();
        else if (mRefreshLayout.isLoading())
            mRefreshLayout.finishLoadmore();
    }

    /**
     * post 获取动态列表
     * @param pageIndex
     */
    private void doPostGetActiveList(int pageIndex) {
        if (TextUtils.isEmpty(DataHelper.getStringSF(ActiveListActivity.this,
                SharepreferenceKey.KEY_ACCESS_TOKEN))) {    // 未登录 则跳转
            ARouter.getInstance().build(ARoutePath.PATH_WEBVIEW).navigation();
        } else {
            // pageSize > 15 该接口会报错
            mPresenter.callMethodOfGetActiveList(
                    DataHelper.getStringSF(ActiveListActivity.this,
                            SharepreferenceKey.KEY_ACCESS_TOKEN),
                    0,
                    (int) ((LoginUser) DataHelper.getDeviceData(ActiveListActivity.this,
                            SharepreferenceKey.KEY_LOGIN_USER)).getId(),
                    pageIndex,
                    CONSTANT_PAGE_SIZE);
        }
    }

    /**
     * 初始化下拉刷新
     */
    private void initRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败

                mPageIndex = 1;
                if (null != mActiveList) mActiveList.clear();
                doPostGetActiveList(mPageIndex);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败

                doPostGetActiveList(++mPageIndex);
            }
        });
    }

    /**
     * 初始化 RecyclerView
     */
    private void initRecyclerView() {
        // 设置布局管理器
        mLayoutManager = new LinearLayoutManager(ActiveListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 添加分割线
        mRecyclerView.addItemDecoration(new MDLinearRvDividerDecoration(this, LinearLayoutManager.VERTICAL));
    }

    /**
     * 绑定数据到 Adapter
     * @param activeList 当前获取到的 最新CONSTANT_PAGE_SIZE条数据
     */
    private void setAdapter(List<Active> activeList) {
        if (null == mAdapter) {
            // 设置 Adapter
            mAdapter = new ActiveListAdapter(mActiveList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置 RecyclerView 的点击项事件
     */
    private void setItemClickListener() {
        mAdapter.setOnItemClickListener(new ActiveListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ArmsUtils.snackbarText("单击了第" + position + "项");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ArmsUtils.snackbarText("长按了第" + position + "项");
            }
        });
    }

}
