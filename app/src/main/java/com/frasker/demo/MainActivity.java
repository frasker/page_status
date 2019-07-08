package com.frasker.demo;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.frasker.pagestatus.PageStatusAdapter;
import com.frasker.pagestatus.PageStatusManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout container = findViewById(R.id.container);
        final PageStatusManager statusManager = new PageStatusManager(container, new MyPageStatusAdapter());

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusManager.showNormalView();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusManager.showErrorView();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusManager.showLoadingView();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusManager.showEmptyView();
            }
        });

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




    }


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
}
