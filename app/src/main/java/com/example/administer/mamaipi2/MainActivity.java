package com.example.administer.mamaipi2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.administer.mamaipi2.menu.TabFragment;
import com.example.administer.mamaipi2.menu.UserFragment;
import com.example.administer.mamaipi2.menu.library.TabItem;
import com.example.administer.mamaipi2.menu.library.XFragmentTabHost;

/**
 * Created by zhh on 2017/1/18.
 * 个人
 * csdn网站：http://blog.csdn.net/zhh_csdn_ard
 * devstore网站：http://www.devstore.cn/user_home/zhanghai_ardapp.html
 */
public class MainActivity extends AppCompatActivity {
    private long exitTime = 0;

    //基于FragmentTabHost的控件
    private XFragmentTabHost mTabHost;

    String mTabTitle = "搜索";
    String mUserTitle = "搜索记录";
    int mImageResId = R.drawable.sel_tab_home;//放入图片id

    //这是你要用到的Fragment
    Class mFragClass = TabFragment.class;//一页有两个分页
    Class mUserClass = UserFragment.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_clip);
        initTabHost();

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initTabHost() {
        //展开样式数组
        Drawable drawables = ContextCompat.getDrawable(this, R.mipmap.ic_bg1);//根据id获得图片

        mTabHost = (XFragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.relate_tab_content);
        mTabHost.setTabMode(XFragmentTabHost.TabMode.ClipDrawable);


        Bundle bundle = new Bundle();
        bundle.putString(TabFragment.FRAG_KEY, mTabTitle);
        mTabHost.addTabItem(new TabItem(mTabTitle, drawables, mImageResId),
                    mFragClass, bundle);
        Bundle bundle2 = new Bundle();
        bundle.putString(UserFragment.FRAG_KEY, mUserTitle);
        mTabHost.addTabItem(new TabItem(mUserTitle, drawables, mImageResId),
                mUserClass, bundle2);

    }
    @Override
    public void onPause(){
        super.onPause();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
