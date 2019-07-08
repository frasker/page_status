package com.frasker.pagestatus;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: created by lvmo on 2019-07-08
 * email: lvmo@baidu.com
 */
public abstract class PageStatusAdapter {

    public abstract @NonNull View onCreateView(ViewGroup parent, PageStatusManager.PageStatus status);
}
