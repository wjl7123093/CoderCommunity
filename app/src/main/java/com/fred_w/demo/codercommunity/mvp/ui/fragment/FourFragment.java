package com.fred_w.demo.codercommunity.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fred_w.demo.codercommunity.app.utils.FunctionManager;
import com.fred_w.demo.codercommunity.mvp.model.entity.LvMineFunctionBean;
import com.fred_w.demo.codercommunity.mvp.ui.adapter.CommonAdapter;
import com.fred_w.demo.codercommunity.mvp.ui.adapter.ViewHolder;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.fred_w.demo.codercommunity.di.component.DaggerFourComponent;
import com.fred_w.demo.codercommunity.di.module.FourModule;
import com.fred_w.demo.codercommunity.mvp.contract.FourContract;
import com.fred_w.demo.codercommunity.mvp.presenter.FourPresenter;

import com.fred_w.demo.codercommunity.R;

import org.raphets.roundimageview.RoundImageView;

import java.lang.reflect.Field;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 个人中心 Fragment
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-1
 * @update
 */
public class FourFragment extends BaseFragment<FourPresenter> implements FourContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTvToolbarTitle;
    @BindView(R.id.toolbar_back)
    RelativeLayout mBtnToolbarBack;

    @BindView(R.id.iv_header)
    RoundImageView mIvHeader;
    @BindView(R.id.tv_name)
    TextView mTvName;

    @BindView(R.id.lv_mine)
    ListView mLvMine;

    private FunctionManager mFuncManager;
    private CommonAdapter<LvMineFunctionBean> mAdapter;


    public static FourFragment newInstance() {
        FourFragment fragment = new FourFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerFourComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .fourModule(new FourModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mBtnToolbarBack.setVisibility(View.GONE);
        mTvToolbarTitle.setText("个人中心");

        initFunctionManager();
        bindGvFuncData();
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

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

    }

    /** 初始化 FunctionManager */
    private void initFunctionManager() {
        mFuncManager = FunctionManager.getInstance(getContext());
        mFuncManager.init();
    }

    /** 数据绑定 GridView */
    private void bindGvFuncData() {
        // 设置适配器
        mLvMine.setAdapter(mAdapter = new CommonAdapter<LvMineFunctionBean>(
                getContext(), mFuncManager.getBeans(), R.layout.item_lv_mine_function) {

            @Override
            public void convert(ViewHolder helper, LvMineFunctionBean item) {
                helper.setText(R.id.tv_func_name, item.getDisplayName());
                helper.setImageResource(R.id.iv_func_icon, getResourceIdMipmap(item.getIcon()));
            }

        });
        mLvMine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    mFuncManager.lanchFunction(position);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 根据 icon 名称获取图片资源 ID
     * @param imgName 图片名称
     * @return 资源 ID
     */
    private int getResourceIdMipmap(String imgName)
    {
        Field field;
        int resId = R.mipmap.ic_launcher;
        try {
            field = R.mipmap.class.getField(imgName);
            resId = (int) field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return resId;
    }

}
