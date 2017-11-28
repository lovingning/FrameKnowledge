package com.knowledge.mnlin.frame.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.knowledge.mnlin.collapselayout.CollapseLayout;
import com.knowledge.mnlin.frame.R;
import com.knowledge.mnlin.frame.base.BaseActivity;
import com.knowledge.mnlin.frame.contract.HelpContract;
import com.knowledge.mnlin.frame.presenter.HelpPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;

@Route(path = "/activity/HelpActivity")
public class HelpActivity extends BaseActivity<HelpPresenter> implements HelpContract.View {

    @BindView(R.id.lv_help)
    ListView mLvHelp;
    @BindView(R.id.parent)
    LinearLayout mParent;
    @BindView(R.id.rv_help)
    RecyclerView mRvHelp;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_help;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add("1");
        temp.add("2");
        temp.add("3");
        temp.add("4");
        temp.add("5");
        temp.add("6");
        temp.add("7");
        temp.add("8");
        temp.add("9");
        temp.add("10");
        mLvHelp.setAdapter(new Adapter(this, temp));
        mLvHelp.setOnItemClickListener((parent, view, position, id) -> Logger.d("ListView:" + parent + view + position));

        mRvHelp.setLayoutManager(new LinearLayoutManager(this));
        mRvHelp.setAdapter(new RecAdapter(this,temp));
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    public class Adapter extends BaseAdapter {
        private ArrayList<String> data;
        private Context context;

        public Adapter(Context context, ArrayList<String> data) {
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                CollapseLayout item = new CollapseLayout(parent, context);
                convertView = item;
            }
            ((CollapseLayout) convertView).setTitleAndContent("title:" + data.get(position), "content:" + data.get(position));
            return convertView;
        }
    }

    public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

        private Context context;
        private ArrayList<String> datas;

        public RecAdapter(Context context, ArrayList<String> datas) {
            this.context = context;
            this.datas = datas;
        }

        /**
         * 负责为item创建视图
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(new CollapseLayout(parent, context));
        }

        /**
         * 负责将数据绑定到item的视图上
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            ((CollapseLayout) holder.itemView).setTitleAndContent("titleRec:" + datas.get(position), "contentRec:" + datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}