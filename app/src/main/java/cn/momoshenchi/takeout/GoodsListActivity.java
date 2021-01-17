package cn.momoshenchi.takeout;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import cn.momoshenchi.takeout.bean.BeanCart;
import cn.momoshenchi.takeout.adapter.CartContentRecyclerAdapter;
import cn.momoshenchi.takeout.components.MyNumImageView;

public class GoodsListActivity extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    public  static BeanCart beanCarts;

    private Button btn;
    private MyNumImageView eleme;
    private TextView total_price;

    private  View bottom;
    private Fragment business_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beanCarts =new BeanCart();

        appBarLayout = findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
                int color = Color.argb(200, 0, 0, 0);
                collapsingToolbar.setCollapsedTitleTextColor(color);
                ImageView imageView = findViewById(R.id.bgc_img);
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) { // 折叠状态
                    //重新设置标题栏
                    imageView.setVisibility(View.GONE);
                } else {
                    // 非折叠状态
                    //恢复标题栏
                    collapsingToolbar.setTitle("");
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });



        eleme=findViewById(R.id.eleme);
        total_price=findViewById(R.id.total_price);
        btn=findViewById(R.id.btn_checkout);
        total_price.setText("未选购商品");


//        goods=findViewById(R.id.);
        btn.setBackgroundColor(Color.rgb(230,230,230));

//        btn.setBackground(R.drawable.blue_shape);

        eleme.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!beanCarts.isEmpty())
                {
                    appear();
                }
            }
        });
        DataChangeListener dataChangeListener =new DataChangeListener()
        {
            @Override
            public void change(int data)
            {
                if(data==0)
                {
                    eleme.setNum(data);
                    total_price.setText("未选购商品");
                    btn.setText("¥20起送");
                    btn.setBackgroundColor(Color.rgb(230,230,230));
                }
                else
                {
                    eleme.setNum(data);
                    btn.setText("去结算");
                    total_price.setText(String.valueOf(beanCarts.getTotalPrice()));
                    btn.setBackgroundColor(Color.rgb(0,0,230));
                }
            }
        };
        beanCarts.setDataChangeListener(dataChangeListener);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.business_toolbar_menu, menu);
        return true;
    }
    public  void appear()
    {
        final Dialog dialog = new Dialog(this, R.style.bottom_anim_theme);
        View view = View.inflate(this, R.layout.goods_content_bottom, null);
        RecyclerView recyclerView=view.findViewById(R.id.cart_content_recycler);

        dialog.setContentView(view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        CartContentRecyclerAdapter cartContentRecyclerAdapter=new CartContentRecyclerAdapter(beanCarts);
        recyclerView.setAdapter(cartContentRecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);

        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        dialog.findViewById(R.id.chosen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}