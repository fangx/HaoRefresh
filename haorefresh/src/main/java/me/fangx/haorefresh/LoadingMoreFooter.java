package me.fangx.haorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoadingMoreFooter extends LinearLayout {

    private Context context;
    private LinearLayout loading_view_layout;
    private LinearLayout end_layout;

    public LoadingMoreFooter(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context) {
        setGravity(Gravity.CENTER);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.footer_layout,
                null);
        loading_view_layout = (LinearLayout) view.findViewById(R.id.loading_view_layout);
        end_layout = (LinearLayout) view.findViewById(R.id.end_layout);


        addFootLoadingView(new ProgressBar(context, null, android.R.attr.progressBarStyle));

        TextView textView = new TextView(context);
        textView.setText("已经到底啦~");
        addFootEndView(textView);

        addView(view);
    }


    //设置底部加载中效果
    public void addFootLoadingView(View view) {
        loading_view_layout.removeAllViews();
        loading_view_layout.addView(view);
    }

    //设置底部到底了布局
    public void addFootEndView(View view) {
        end_layout.removeAllViews();
        end_layout.addView(view);
    }


    //设置已经没有更多数据
    public void setEnd() {
        setVisibility(VISIBLE);
        loading_view_layout.setVisibility(GONE);
        end_layout.setVisibility(VISIBLE);
    }


    public void setVisible(){
        setVisibility(VISIBLE);
        loading_view_layout.setVisibility(VISIBLE);
        end_layout.setVisibility(GONE);
    }


    public void setGone(){
        setVisibility(GONE);
    }


}
