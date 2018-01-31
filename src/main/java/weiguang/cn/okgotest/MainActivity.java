package weiguang.cn.okgotest;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private Button mButton;
    private Handler mHandler = new Handler();

    @Override
    protected int setLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mButton = (Button) findViewById(R.id.home_bt);
        mButton.setOnClickListener(this);
    }

    private int count = 5;

    @Override
    protected void initData() {
        mHandler.postDelayed(mRunnable, 1000);

    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, 1000);
            count--;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mButton.setText("跳过" + count);
                    mButton.setTextColor(getResources().getColor(R.color.RED));
                    if (count == 0) {
                        mHandler.removeCallbacks(this);
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    };

    @Override
    public void onClick(View v) {
        mHandler.removeCallbacks(mRunnable);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
