# page_status
Android 页面状态管理,统一管理页面状态切换

## 设置方式
PageStatusAdapter 需要关联到一个ViewGroup容器上，类似切换fragment常用的FrameLayout。同时，通过提供adapter的方式来初始化各种状态页面。
```
  private class MyPageStatusAdapter extends PageStatusAdapter {

        @NonNull
        @Override
        public View onCreateView(ViewGroup parent, PageStatusManager.PageStatus status) {
            View view = null;
            switch (status) {
                case EMPTY:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty, parent, false);
                    break;
                case ERROR:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.error, parent, false);
                    break;
                case NORMAL:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.normal, parent, false);
                    break;
                case LOADING:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading, parent, false);
                    break;
            }
            return view;
        }
    }
  final PageStatusManager statusManager = new PageStatusManager(container, new MyPageStatusAdapter());
}
```
## 页面切换
PageStatusManager 提供四个方法进行各页面状态切换。
```
    /**
     * 显示"正在加载"页面
     */
    statusManager.showLoadingView();

    /**
     * 显示"加载错误"页面
     */
    statusManager.showErrorView();

    /**
     * 显示"正常"页面
     */
    statusManager.showContentView();

    /**
     * 显示"空白"页面
     */
    statusManager.showEmptyView();
```
## 失败重试
通常空页面和错误页面都会有重试按钮，page_status内置了默认的ID。使用内置ID指定button，可以直接使用OnStatusClickListener进行点击监听。
```
  android:id="@id/empty_id"

  android:id="@id/error_id"
  
  statusManager.setOnStatusClickListener(new PageStatusManager.OnStatusClickListener() {
            @Override
            public void onEmptyClick(View view) {
                Toast.makeText(MainActivity.this,"empty click",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorClick(View view) {
                Toast.makeText(MainActivity.this,"error click",Toast.LENGTH_SHORT).show();
            }
        });

```
## 依赖

```
implementation 'com.frasker:pagestatus:0.0.1'
```
