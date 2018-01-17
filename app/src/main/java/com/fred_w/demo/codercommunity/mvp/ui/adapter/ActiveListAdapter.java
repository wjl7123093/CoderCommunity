package com.fred_w.demo.codercommunity.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.utils.TextUtils;
import com.fred_w.demo.codercommunity.R;
import com.fred_w.demo.codercommunity.app.utils.ImageLoader;
import com.fred_w.demo.codercommunity.mvp.model.entity.Active;

import org.raphets.roundimageview.RoundImageView;

import java.util.List;

import static com.fred_w.demo.codercommunity.app.utils.DateUtils.timeLogic;

/**
 * 动态列表 Adapter
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-17
 * @update
 */
public class ActiveListAdapter extends RecyclerView.Adapter<ActiveListAdapter.ViewHolder> {

    private List<Active> activeList;
    /**
     * 事件回调监听
     */
    private OnItemClickListener onItemClickListener;

    public ActiveListAdapter(List<Active> activeList) {
        this.activeList = activeList;
    }

    @Override
    public ActiveListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv_active,
                parent, false);
        ActiveListAdapter.ViewHolder vh = new ActiveListAdapter.ViewHolder(view);

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

        // 单击项
        holder.itemView.setOnClickListener(v -> {
            if(onItemClickListener != null) {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemClick(holder.itemView, pos);
            }
        });
        // 长按项
        holder.itemView.setOnLongClickListener(v -> {
            if(onItemClickListener != null) {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemLongClick(holder.itemView, pos);
            }
            //表示此事件已经消费，不会触发单击事件
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return null == activeList ? 0 : activeList.size();
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

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    /**
     * 设置回调监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
