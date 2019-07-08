package com.frasker.pagestatus;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public class PageStatusManager{
    private PageStatus mPageStatus = PageStatus.NORMAL;
    private ViewGroup mRootView;
    private View mLoadingView;
    private View mEmptyView;
    private View mErrorView;
    private View mContentView;
    private PageStatusAdapter mAdapter;
    private OnStatusClickListener mStatusClickListener;

    public PageStatusManager(@NonNull ViewGroup root, @NonNull PageStatusAdapter adapter) {
        this.mRootView = root;
        this.mAdapter = adapter;
    }

    public PageStatusManager(@NonNull ViewGroup root, @NonNull PageStatusAdapter adapter, OnStatusClickListener statusClickListener) {
        this.mRootView = root;
        this.mAdapter = adapter;
        this.mStatusClickListener = statusClickListener;
    }

    public void setOnStatusClickListener(OnStatusClickListener statusClickListener) {
        this.mStatusClickListener = statusClickListener;
    }

    public void showLoadingView() {
        changeStatus(PageStatus.LOADING);
    }


    public void showErrorView() {
        changeStatus(PageStatus.ERROR);
    }


    public void showNormalView() {
        changeStatus(PageStatus.NORMAL);
    }


    public void showEmptyView() {
        changeStatus(PageStatus.EMPTY);
    }

    private void changeStatus(PageStatus pageStatus) {
        if (mRootView == null
                || (mPageStatus == pageStatus && mRootView.getChildCount() > 0)) {
            return;
        }
        mRootView.removeAllViews();
        mPageStatus = pageStatus;
        switch (pageStatus) {
            case NORMAL:
                if (mContentView == null) {
                    mContentView = mAdapter.onCreateView(mRootView, PageStatus.NORMAL);
                }
                mRootView.addView(mContentView);
                break;
            case EMPTY:
                if (mEmptyView == null) {
                    mEmptyView = mAdapter.onCreateView(mRootView, PageStatus.EMPTY);
                }
                View emptyClickView = mEmptyView.findViewById(R.id.empty_id);
                if (emptyClickView != null && !emptyClickView.hasOnClickListeners()) {
                    emptyClickView.setClickable(true);
                    emptyClickView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mStatusClickListener != null) {
                                mStatusClickListener.onEmptyClick(v);
                            }
                        }
                    });
                }
                mRootView.addView(mEmptyView);
                break;
            case ERROR:
                if (mErrorView == null) {
                    mErrorView = mAdapter.onCreateView(mRootView, PageStatus.ERROR);
                }
                View errorClickView = mErrorView.findViewById(R.id.error_id);
                if (errorClickView != null && !errorClickView.hasOnClickListeners()) {
                    errorClickView.setClickable(true);
                    errorClickView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mStatusClickListener != null) {
                                mStatusClickListener.onErrorClick(v);
                            }
                        }
                    });
                }
                mRootView.addView(mErrorView);
                break;
            case LOADING:
                if (mLoadingView == null) {
                    mLoadingView = mAdapter.onCreateView(mRootView, PageStatus.LOADING);
                }
                mRootView.addView(mLoadingView);
                break;
            default:
                // ignore
                break;
        }
    }

    public enum PageStatus {
        NORMAL,
        EMPTY,
        ERROR,
        LOADING
    }

    public interface OnStatusClickListener {

        void onEmptyClick(View view);

        void onErrorClick(View view);
    }

    public abstract static class SimpleStatusClickListener implements OnStatusClickListener {

        @Override
        public void onEmptyClick(View view) {

        }

        @Override
        public void onErrorClick(View view) {

        }
    }

}
