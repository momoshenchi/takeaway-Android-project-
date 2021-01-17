package cn.momoshenchi.takeout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.momoshenchi.takeout.bean.BeanComment;
import cn.momoshenchi.takeout.bean.BeanGoods;
import cn.momoshenchi.takeout.bean.BeanMenu;
import cn.momoshenchi.takeout.adapter.BusinessFragmentAdapter;
import cn.momoshenchi.takeout.R;

public class TabFragment extends Fragment {
    private ViewPager pager;
    private BusinessFragmentAdapter businessFragmentAdapter;
    private String [] title={"点单","评价","商家"};
    private TabLayout tabLayout;

    private CommentFragment commentFragment;
    private OrderFragment orderFragment;
    private RetailerFragment retailerFragment;

    public   static  BeanMenu[]beanMenus;
    public   static  BeanComment[]beanComments;
    public  static   BeanGoods[]beanGoods;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_tablayout,container,false);
        pager=view.findViewById(R.id.viewpager);
        tabLayout=view.findViewById(R.id.tab);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
    commentFragment=new CommentFragment(beanComments);
    orderFragment=new OrderFragment(beanMenus,beanGoods);
    retailerFragment =new RetailerFragment();

        businessFragmentAdapter=new BusinessFragmentAdapter(getActivity().getSupportFragmentManager(),title,commentFragment,orderFragment,retailerFragment);
        pager.setAdapter(businessFragmentAdapter);
        tabLayout.setupWithViewPager(pager);//与ViewPage建立关系
    }

    private  void init()
    {
        beanMenus=new BeanMenu[3];
        beanComments=new BeanComment[10];
        beanGoods=new BeanGoods[10];

        beanMenus[0]=new BeanMenu();
        beanMenus[1]=new BeanMenu();
        beanMenus[2]=new BeanMenu();
        beanMenus[0].setMenuname("刚刚看过");
        List<BeanGoods>list=new ArrayList<>();
//        beanMenus[0].isSelected=true;
        beanMenus[1].setMenuname("必选品");
        beanMenus[2].setMenuname("超享折扣");
        for (int i = 0; i < 5; i++) {
            BeanGoods b=new BeanGoods();
            b.setDes("羊肉串2串+牛肉串2串+骨肉相连2串");
            b.setName("超级福满多套餐");
            b.setNum("月售102");
            b.setPrice("68");
            b.setVipprice("品牌会员价￥31.9");
            b.setImg(R.drawable.glgs);
            b.setTitlename("刚刚看过");
            b.setTag(""+i);
            beanGoods[i]=b;
            list.add(b);
        }
        beanMenus[0].setCata(list);
        list=new ArrayList<>();
        for (int i = 5; i < 9; i++) {
            BeanGoods b=new BeanGoods();
            b.setDes("羊肉串2串");
            b.setName("羊肉串");
            b.setNum("月售1020");
            b.setPrice("15");
            b.setVipprice("品牌会员价￥11.9");
            b.setImg(R.drawable.eye_img);
            b.setTitlename("必选品");
            b.setTag(""+i);
            beanGoods[i]=b;
            list.add(b);
        }
        beanMenus[1].setCata(list);
        BeanGoods b=new BeanGoods();
        b.setDes("羊肉串2串+牛肉串2串+骨肉相连2串");
        b.setName("超级福满多套餐");
        b.setNum("月售102");
        b.setPrice("68");
        b.setVipprice("品牌会员价￥31.9");
        b.setImg(R.drawable.glgs);
        b.setTitlename("超享折扣");
        b.setTag(""+9);
        beanGoods[9]=b;
        list=new ArrayList<>();
        list.add(b);
        beanMenus[2].setCata(list);
        for (int i = 0; i < 10; i++) {
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
