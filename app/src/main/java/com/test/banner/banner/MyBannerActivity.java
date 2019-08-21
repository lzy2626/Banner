package com.test.banner.banner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geek.banner.Banner;
import com.geek.banner.loader.BannerLoader;
import com.test.banner.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther :liuzhanyue
 * @date :2019/8/21 0021 16
 * @des :
 */
public class MyBannerActivity extends AppCompatActivity {

    @BindView(R.id.banner0)
    Banner banner0;
    @BindView(R.id.banner_text)
    TextView bannerText;

    @BindView(R.id.banner1)
    Banner banner1;
    private List<String> mURLs = new ArrayList<>();
    private List<Integer> mRes = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, MyBannerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_banner);
        ButterKnife.bind(this);
        mURLs.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        mURLs.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        mURLs.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        mURLs.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        mURLs.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        mRes.add(R.drawable.banner_1);
        mRes.add(R.drawable.banner_2);
        mRes.add(R.drawable.banner_3);
        mRes.add(R.drawable.banner_4);
        mRes.add(R.drawable.banner_5);

        mTitle.add("悯农~");
        mTitle.add("锄禾日当午~");
        mTitle.add("汗滴禾下土~");
        mTitle.add("谁知盘中餐~");
        mTitle.add("粒粒皆辛苦~");

        /**
         * 1.只显示一张图片，并且显示指示器
         */
        banner0.setBannerLoader(new BannerLoader<String, View>() {
            @Override
            public void loadView(Context context, String path, View view) {
                ImageView banner = view.findViewById(R.id.custom_iv);
                Glide.with(context)
                        .load(path)
                        .into(banner);
            }

            @Override
            public View createView(Context context) {
                View item = LayoutInflater.from(context).inflate(R.layout.my_banner_item, null);
                return item;
            }
        });
        if (mTitle.size() > 0) {
            bannerText.setText(mTitle.get(0));
        }
        banner0.setCornerRadius(10);
        banner0.loadImagePaths(mURLs.subList(0,1));


        banner1.setBannerLoader(new BannerLoader<String, View>() {
            @Override
            public void loadView(Context context, String path, View view) {
                ImageView banner = view.findViewById(R.id.custom_iv);
                Glide.with(context)
                        .load(path)
                        .into(banner);
            }

            @Override
            public View createView(Context context) {
                View pager = LayoutInflater.from(context).inflate(R.layout.my_banner_item, null);
                return pager;
            }
        });
        banner1.loadImagePaths(mURLs);
    }

}
