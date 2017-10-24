package com.touyuanren.mvpsample.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.touyuanren.mvpsample.R;
import com.touyuanren.mvpsample.presenter.BookPresenter;
import com.touyuanren.mvpsample.presenter.contract.BookInfoContract;

import butterknife.BindView;

public class MainActivity extends MvpActivity<BookPresenter> implements BookInfoContract.IView {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindClick();
    }

    @Override
    protected BookPresenter createPresenter() {
        return new BookPresenter(this);
    }

    private void bindClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开始请求
                mvpPresenter.getMsg("人间失格", null, 0, 1);
//                LogUtil.e("mvpPresenter","mvpPresenter执行了");
            }
        });
    }


    @Override
    public void showError(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(String msg) {
        text.setText(msg);
    }

}
