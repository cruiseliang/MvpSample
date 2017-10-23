package com.touyuanren.mvpsample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.touyuanren.mvpsample.R;
import com.touyuanren.mvpsample.presenter.BookPresenter;
import com.touyuanren.mvpsample.presenter.contract.BookInfoContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements BookInfoContract.IView {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button)
    Button button;
    private Unbinder unbinder;
    private BookPresenter mBookPreseter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mBookPreseter = new BookPresenter(this);
        bindClick();
    }

    private void bindClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开始请求
                mBookPreseter.getMsg("人间失格", null, 0, 1);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(String msg) {
        text.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
        }
    }
}
