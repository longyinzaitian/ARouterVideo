package video.cn.home.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.husy.network.model.ItemList;

import java.util.List;

import video.cn.base.base.BaseAdapter;
import video.cn.base.base.BaseFragment;
import video.cn.base.base.AbstractCustomRecyclerScrollListener;
import video.cn.base.utils.RouteUtils;
import video.cn.home.R;
import video.cn.home.adapter.home.HomeFrAdapter;

/**
 * @author husy
 * @date 2019/9/1
 */
@Route(path = RouteUtils.HOME_FRAGMENT_MAIN)
public class HomeFragment extends BaseFragment implements HomeContract.MainView {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeContract.MainPresenter mHomePresenter;

    private HomeFrAdapter mHomeFrAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(@NonNull View view) {
        mRecyclerView = view.findViewById(R.id.home_recycler);
        mSwipeRefreshLayout = view.findViewById(R.id.home_swipe_refresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mHomePresenter != null) {
                    mHomePresenter.getHomeInfo();
                }
            }
        });

        mHomeFrAdapter = new HomeFrAdapter(getActivity());
        mRecyclerView.setAdapter(mHomeFrAdapter);

        mHomeFrAdapter.setListListener(new BaseAdapter.ListListener<ItemList>() {
            @Override
            public void onClickLoadMore() {
                loadMore();
            }

            @Override
            public void onItemClick(ItemList itemList) {

            }
        });

        mRecyclerView.setOnScrollListener(new AbstractCustomRecyclerScrollListener() {
            @Override
            public void onLoadMore() {
                HomeFragment.this.loadMore();
            }
        });
    }

    private void loadMore() {
        if (mHomePresenter != null) {
            mHomePresenter.getHomeInfoMore();
        }
    }

    private void initData() {
        mHomePresenter = new HomeFrPresenter(this);
        mHomePresenter.getHomeInfo();
    }

    @Override
    public void setData(List<ItemList> itemLists) {
        mSwipeRefreshLayout.setRefreshing(false);
        mHomeFrAdapter.setData(itemLists);
    }

    @Override
    public void addData(List<ItemList> itemLists) {
        mHomeFrAdapter.addData(itemLists);
    }

    @Override
    public void setBanner(List<String> images) {
        mHomeFrAdapter.setBanner(images);
    }
}
