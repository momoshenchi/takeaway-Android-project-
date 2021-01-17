package cn.momoshenchi.takeout.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.momoshenchi.takeout.DataChangeListener;
import cn.momoshenchi.takeout.GoodsDetailActivity;
import cn.momoshenchi.takeout.bean.BeanComment;
import cn.momoshenchi.takeout.bean.BeanGoods;
import cn.momoshenchi.takeout.GoodsListActivity;
import cn.momoshenchi.takeout.R;
import cn.momoshenchi.takeout.fragment.TabFragment;

public abstract class GoodsRecyclerAdapter extends RecyclerView.Adapter<GoodsRecyclerAdapter.VH>
{

    private BeanGoods[] list;
    private Context mContext;
    public static DataChangeListener dataChangeListener;

    public GoodsRecyclerAdapter(BeanGoods[] list)
    {
        this.list = list;
    }

    private ImageButton currentsub;
    private TextView currentnumber;

    static class VH extends RecyclerView.ViewHolder
    {

        public final TextView name;
        public final TextView des;
        public final TextView num;
        public final ImageView img;
        public final TextView price;
        public final TextView vipprice;
        public final ImageButton add;

        public final TextView goodsnumber;
        public final ImageButton sub;

        public VH(View v)
        {
            super(v);
            name = v.findViewById(R.id.goods_name);
            des = v.findViewById(R.id.goods_des);
            num = v.findViewById(R.id.goods_num);
            img = v.findViewById(R.id.goods_img);
            price = v.findViewById(R.id.goods_price);
            vipprice = v.findViewById(R.id.goods_vipprice);
            add = v.findViewById(R.id.goods_add);

            goodsnumber = v.findViewById(R.id.goods_number);
            sub = v.findViewById(R.id.goods_sub);

        }
    }

    @NonNull
    @Override
    public GoodsRecyclerAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        if (mContext == null)
        {
            mContext = parent.getContext();
        }
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_goods, parent, false);
        final VH vh = new VH(v);
        v.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = vh.getAdapterPosition();
                currentsub=v.findViewById(R.id.goods_sub);
                currentnumber=v.findViewById(R.id.goods_number);
                appear(position);
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodsRecyclerAdapter.VH holder, final int position)
    {
        holder.name.setText(list[position].getName());
        holder.des.setText(list[position].getDes());
        holder.num.setText(list[position].getNum());
        holder.price.setText(list[position].getPrice());
        holder.vipprice.setText(list[position].getVipprice());
        holder.img.setImageResource(list[position].getImg());
        holder.sub.setImageResource(R.mipmap.jianqu);
        holder.add.setImageResource(R.mipmap.tianjia);
        dataChangeListener = new DataChangeListener()
        {
            @Override
            public void change(int data)
            {
                if (data == 0)
                {
                    currentsub.setVisibility(View.INVISIBLE);
                    currentnumber.setVisibility(View.INVISIBLE);
                }
                else
                {
                    currentsub.setVisibility(View.VISIBLE);
                    currentnumber.setVisibility(View.VISIBLE);
                    currentnumber.setText(String.valueOf(data));
                }
            }
        };
        holder.add.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                System.out.println(v);
                currentsub = holder.sub;
                currentnumber = holder.goodsnumber;
                GoodsListActivity.beanCarts.addGoods(list[position]);
            }

        });
        holder.sub.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                System.out.println(v);
                currentsub = holder.sub;
                currentnumber = holder.goodsnumber;
                GoodsListActivity.beanCarts.subGoods(list[position]);
            }
        });
    }

    public void appear(int position)
    {
        final Dialog dialog = new Dialog(mContext, R.style.bottom_anim_theme);
        View view = View.inflate(dialog.getContext(), R.layout.activity_goods_detail, null);
        BeanGoods[] goods = TabFragment.beanGoods;
        final BeanGoods beanGoods = goods[position];
        System.out.println(position);

        BeanComment[] beanComments = new BeanComment[3];
        for (int i = 0; i < 3; i++)
        {
            BeanComment bc = new BeanComment();
            bc.setName("momoshenchi");
            bc.setAvator(R.drawable.avator);
            bc.setContent("太好吃了,宝宝已经去医院抢救好几次了");
            bc.setDate("2021-01-09");
            bc.setImg(new int[]{});
            beanComments[i] = bc;
        }
        RecyclerView recyclerView = view.findViewById(R.id.goods_detail_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GoodsDetailAdapter commentRecyclerAdapter = new GoodsDetailAdapter(beanComments);
        recyclerView.setAdapter(commentRecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);


        ImageButton add = view.findViewById(R.id.goods_detail_add);
        final ImageButton sub = view.findViewById(R.id.goods_detail_sub);
        final TextView number = view.findViewById(R.id.goods_detail_number);
        if (GoodsListActivity.beanCarts.containsGoods(beanGoods))
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
                int num = GoodsListActivity.beanCarts.getGoodsNum(beanGoods);
                GoodsListActivity.beanCarts.subGoods(beanGoods);
                if (num == 1)
                {
                    number.setVisibility(View.INVISIBLE);
                    sub.setVisibility(View.INVISIBLE);
                }
                else
                {
                    number.setText(String.valueOf(GoodsListActivity.beanCarts.getGoodsNum(beanGoods)));
                }
            }
        });


        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);

        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.findViewById(R.id.goods_detail_back).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();
            }
        });
    }

    public abstract void start(int position);

    @Override
    public int getItemCount()
    {
        return list.length;
    }
}
