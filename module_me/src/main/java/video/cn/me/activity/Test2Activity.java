package video.cn.me.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.utils.RouteUtils;
import video.cn.me.R;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.ME_TEST_2, group = "needLogin")
public class Test2Activity extends AppCompatActivity {

    /**
     * 数据接收页面
     */
    private TextView mTextView;
    /**
     * eventBus返回数据
     */
    private Button mBtnBackData;
    private String extra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        initData();
        initView();
    }

    private void initData() {
        extra = getIntent().getStringExtra("extra");

    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mBtnBackData = (Button) findViewById(R.id.btn_back_data);
        mBtnBackData.setText("finish");
        mBtnBackData.setVisibility(View.GONE);
        mTextView.setText("extra==>" + extra);
    }


}
