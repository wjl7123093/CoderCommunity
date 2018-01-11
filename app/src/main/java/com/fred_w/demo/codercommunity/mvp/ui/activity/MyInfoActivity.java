package com.fred_w.demo.codercommunity.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fred_w.demo.codercommunity.app.ARoutePath;
import com.fred_w.demo.codercommunity.app.SharepreferenceKey;
import com.fred_w.demo.codercommunity.app.utils.ImageLoader;
import com.fred_w.demo.codercommunity.mvp.model.entity.MyInfo;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.fred_w.demo.codercommunity.di.component.DaggerMyInfoComponent;
import com.fred_w.demo.codercommunity.di.module.MyInfoModule;
import com.fred_w.demo.codercommunity.mvp.contract.MyInfoContract;
import com.fred_w.demo.codercommunity.mvp.presenter.MyInfoPresenter;

import com.fred_w.demo.codercommunity.R;
import com.jess.arms.utils.DataHelper;


import org.raphets.roundimageview.RoundImageView;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

@Route(path = ARoutePath.PATH_MY_INFO)
public class MyInfoActivity extends BaseActivity<MyInfoPresenter> implements MyInfoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTvToolbarTitle;
    @BindView(R.id.toolbar_back)
    RelativeLayout mBtnToolbarBack;

//    @BindView(R.id.include_header)
    RelativeLayout mRlHeader;               // 头像
//    @BindView(R.id.include_name)
//    RelativeLayout mRlName;                 // 姓名
//    @BindView(R.id.include_gender)
//    RelativeLayout mRlGender;               // 性别
//    @BindView(R.id.include_province)
//    RelativeLayout mRlProvince;             // 省份
//    @BindView(R.id.include_city)
//    RelativeLayout mRlCity;                 // 城市
//    @BindView(R.id.include_platforms)
//    RelativeLayout mRlPlatforms;            // 开发平台
//    @BindView(R.id.include_expertise)
//    RelativeLayout mRlExpertise;            // 专长领域
//    @BindView(R.id.include_joinTime)
//    RelativeLayout mRlJointime;             // 加入时间
//    @BindView(R.id.include_lastLoginTime)
//    RelativeLayout mRlLastLoginTime;        // 最后登录时间


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMyInfoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .myInfoModule(new MyInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_my_info; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        mRlHeader = (RelativeLayout) MyInfoActivity.this.findViewById(R.id.include_header);

        mPresenter.callMethodOfGetMyInformation(DataHelper.getStringSF(MyInfoActivity.this,
                SharepreferenceKey.KEY_ACCESS_TOKEN));
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
    public void showMyInfo(MyInfo myInfo) {
        initLayout(mRlHeader, "头像", myInfo.getPortrait(), true);
//        initLayout(mRlName, "名称", myInfo.getName(), false);
//        initLayout(mRlGender, "性别", myInfo.getName(), false);
//        initLayout(mRlProvince, "省份", myInfo.getName(), false);
//        initLayout(mRlCity, "城市", myInfo.getName(), false);
//        initLayout(mRlPlatforms, "开发平台", myInfo.getName(), false);
//        initLayout(mRlExpertise, "专长领域", myInfo.getName(), false);
//        initLayout(mRlJointime, "加入时间", myInfo.getName(), false);
//        initLayout(mRlLastLoginTime, "最后登录时间", myInfo.getName(), false);
    }

    /**
     * 初始化信息项视图
     * @param layout 项视图
     * @param key 键
     * @param value 值
     * @param isHeader 是否头像
     */
    private void initLayout(RelativeLayout layout, String key, String value,
                            boolean isHeader) {
        TextView tvKey = (TextView) layout.findViewById(R.id.tv_key);
        TextView tvValue = (TextView)layout.findViewById(R.id.tv_value);
        RoundImageView imageView = (RoundImageView) layout.findViewById(R.id.iv_header);

        if (isHeader) {
            imageView.setVisibility(View.VISIBLE);
            tvValue.setVisibility(View.GONE);
            ImageLoader.getInstance().showImage(imageView, value);
        } else {
            imageView.setVisibility(View.GONE);
            tvValue.setVisibility(View.VISIBLE);
            tvValue.setText(value);
        }

        tvKey.setText(key);
    }
}
