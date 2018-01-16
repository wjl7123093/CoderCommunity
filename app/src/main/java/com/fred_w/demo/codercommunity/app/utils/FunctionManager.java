package com.fred_w.demo.codercommunity.app.utils;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fred_w.demo.codercommunity.app.ARoutePath;
import com.fred_w.demo.codercommunity.mvp.model.entity.LvMineFunctionBean;

import java.util.List;

/**
 * FunctionManager xml功能管理类
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-1
 * @update
 */
public class FunctionManager {

	private static volatile FunctionManager instance = null;
	private Context mContext;
	private FunctionXMLReader<LvMineFunctionBean> mXmlReader;
	private List<LvMineFunctionBean> mBeans;
	
	private FunctionManager(Context context) {
		this.mContext = context;
	}
	
	/** 单例 */
	public static FunctionManager getInstance(Context context) {
		if (null == instance) {
			synchronized (FunctionManager.class) {
				if (null == instance) {
					instance = new FunctionManager(context);
				}
			}
		}
		return instance;
	}
	
	/** 初始化 */
	public void init() {
		mXmlReader = new FunctionXMLReader<LvMineFunctionBean>(
				mContext, LvMineFunctionBean.class);
		mXmlReader.parse();
		mBeans = mXmlReader.getBeans();
	}
	
	/** 得到 GvFunctionBean */
	public List<LvMineFunctionBean> getBeans() {
		return mBeans;
	}
	
	/** 
	 * 执行每一项功能
	 * @param position 项索引
	 * @throws ClassNotFoundException
	 */
	public void lanchFunction(int position)
			throws ClassNotFoundException {
		LvMineFunctionBean bean = mBeans.get(position);
		String path = bean.getPath();

		ARouter.getInstance().build(path).navigation();
	}
	
}
