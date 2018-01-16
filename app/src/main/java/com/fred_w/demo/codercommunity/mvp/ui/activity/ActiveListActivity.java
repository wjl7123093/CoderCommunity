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
import com.alibaba.android.arouter.utils.TextUtils;
import com.fred_w.demo.codercommunity.app.ARoutePath;
import com.fred_w.demo.codercommunity.app.SharepreferenceKey;
import com.fred_w.demo.codercommunity.app.utils.ImageLoader;
import com.fred_w.demo.codercommunity.mvp.model.entity.Active;
import com.fred_w.demo.codercommunity.mvp.model.entity.LoginUser;
import com.fred_w.demo.codercommunity.mvp.model.entity.MyInfo;
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


import org.raphets.roundimageview.RoundImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import static com.fred_w.demo.codercommunity.app.utils.DateUtils.timeLogic;
import static com.jess.arms.utils.Preconditions.checkNotNull;

@Route(path = ARoutePath.PATH_ACTIVE_LIST)
public class ActiveListActivity extends BaseActivity<ActiveListPresenter> implements ActiveListContract.View {

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
    private ActiveListAdapter mAdapter;

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
        // pageSize > 15 该接口会报错
        mPresenter.callMethodOfGetActiveList(DataHelper.getStringSF(ActiveListActivity.this,
                SharepreferenceKey.KEY_ACCESS_TOKEN), 0,
                (int)((MyInfo) DataHelper.getDeviceData(ActiveListActivity.this, SharepreferenceKey.KEY_MY_INFO)).getUid(), 1, 10);
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
        // 设置布局管理器
        mLayoutManager = new LinearLayoutManager(ActiveListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                ActiveListActivity.this, DividerItemDecoration.HORIZONTAL));
        // 设置 Adapter
        mAdapter = new ActiveListAdapter(activeList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class ActiveListAdapter extends RecyclerView.Adapter<ActiveListAdapter.ViewHolder> {

        private List<Active> activeList;

        public ActiveListAdapter(List<Active> activeList) {
            this.activeList = activeList;
        }

        @Override
        public ActiveListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv_active,
                    parent, false);
            ViewHolder vh = new ViewHolder(view);

            return vh;
        }

        @Override
        public void onBindViewHolder(ActiveListAdapter.ViewHolder holder, int position) {
            ImageLoader.getInstance().showImage(holder.roundImageView, activeList.get(position)
                    .getPortrait());
            holder.mTvUsername.setText(activeList.get(position).getAuthor());
            holder.mTvPubdateAndAppClient.setText(timeLogic(activeList.get(position).getPubDate())
                    + " 来自 " + getAppClient(activeList.get(position).getAppClient()));
            holder.mTvMessage.setText(activeList.get(position).getMessage());

            if (activeList.get(position).getCatalog() == 3
                    && null != activeList.get(position).getTweetImage()
                    && !TextUtils.isEmpty(activeList.get(position).getTweetImage())) {   // catalog == 3 -> 动弹
                ImageLoader.getInstance().showImage(holder.mIvImage, activeList.get(position)
                        .getTweetImage());
                holder.mIvImage.setVisibility(View.VISIBLE);
            } else {
                holder.mIvImage.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return activeList.size();
        }

        /**
         * 获取 发布平台描述
         * @param appClient 发布平台代码
         * @return
         */
        private String getAppClient(int appClient) {
            String client = "";
            switch (appClient) {
                case 1:
                    client = "WEB";
                    break;
                case 2:
                    client = "WAP";
                    break;
                case 3:
                    client = "Android";
                    break;
                case 4:
                    client = "iOS";
                    break;
                case 5:
                    client = "WP";
                    break;
            }
            return client;
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public RoundImageView roundImageView;
            public TextView mTvUsername;
            public TextView mTvPubdateAndAppClient;
            public TextView mTvMessage;
            public ImageView mIvImage;

            public ViewHolder(View view) {
                super(view);
                roundImageView = (RoundImageView)view.findViewById(R.id.iv_header);
                mTvUsername = (TextView)view.findViewById(R.id.tv_username);
                mTvPubdateAndAppClient = (TextView)view.findViewById(R.id.tv_pubdate_appclient);
                mTvMessage = (TextView)view.findViewById(R.id.tv_message);
                mIvImage = (ImageView)view.findViewById(R.id.iv_content);
            }
        }



    }


}
