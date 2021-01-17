package cn.momoshenchi.takeout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.momoshenchi.takeout.bean.BeanComment;
import cn.momoshenchi.takeout.bean.BeanGoods;
import cn.momoshenchi.takeout.adapter.GoodsDetailAdapter;
import cn.momoshenchi.takeout.fragment.TabFragment;


public class GoodsDetailActivity extends AppCompatActivity
{
    private BottomNavigationView navView;
    private RecyclerView recyclerView;
    private  BeanComment[]beanComments;
    private BeanGoods[]goods;
    private  BeanGoods beanGoods;
    private  ImageButton add;
    private  ImageButton sub;
    private  TextView number;
    private  int pos;
    public GoodsDetailActivity(int  pos)
    {
        this.pos = pos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        init();
//        Intent intent = getIntent();
//        int index = intent.getIntExtra("index", 0);
        int index=pos;
        goods= TabFragment.beanGoods;
        beanGoods=goods[index];
        System.out.println(index);
        recyclerView = findViewById(R.id.goods_detail_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GoodsDetailAdapter commentRecyclerAdapter = new GoodsDetailAdapter(beanComments);
        recyclerView.setAdapter(commentRecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);


        add=findViewById(R.id.goods_detail_add);
        sub=findViewById(R.id.goods_detail_sub);
        number=findViewById(R.id.goods_detail_number);
        if(GoodsListActivity.beanCarts.containsGoods(beanGoods))
        {
            sub.setVisibility(View.VISIBLE);
            sub.setImageResource(R.mipmap.jianqu);
            number.setVisibility(View.VISIBLE);
            number.setText(String.valueOf(GoodsListActivity.beanCarts.getGoodsNum(beanGoods)));
        }
        add.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                System.out.println(v);
                GoodsListActivity.beanCarts.addGoods(beanGoods);
                sub.setVisibility(View.VISIBLE);
                sub.setImageResource(R.mipmap.jianqu);
                number.setVisibility(View.VISIBLE);
                number.setText(String.valueOf(GoodsListActivity.beanCarts.getGoodsNum(beanGoods)));

            }

        });
        sub.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                System.out.println(v);
                int num=GoodsListActivity.beanCarts.getGoodsNum(beanGoods);
                GoodsListActivity.beanCarts.subGoods(beanGoods);
                if (num==1)
                {
                    number.setVisibility(View.INVISIBLE);
                    sub.setVisibility(View.INVISIBLE);
                }else
                {
                    number.setText(String.valueOf(GoodsListActivity.beanCarts.getGoodsNum(beanGoods)));
                }
            }
        });
    }
//请求数据,假设
    public void init()
    {
        beanComments=new BeanComment[3];
        for (int i = 0; i < 3; i++) {
            BeanComment bc=new BeanComment();
            bc.setName("momoshenchi");
            bc.setAvator(R.drawable.avator);
            bc.setContent("太好吃了,宝宝已经去医院抢救好几次了");
            bc.setDate("2021-01-09");
            bc.setImg(new int[]{});
            beanComments[i]=bc;
        }

    }

}