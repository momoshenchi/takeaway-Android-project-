package cn.momoshenchi.takeout.bean;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import cn.momoshenchi.takeout.DataChangeListener;
import cn.momoshenchi.takeout.GoodsListActivity;
import cn.momoshenchi.takeout.adapter.GoodsRecyclerAdapter;

/**
 * @version : 1.0
 * @author: momoshenchi
 * @date: 2021/1/14 - 16:07
 */
public class BeanCart
{

    private List<BeanCartItem> goods;
    private  double mailPrice;
    private  int totalNum;
    private  double totalPrice;
    private DataChangeListener dataChangeListener;

    public DataChangeListener getDataChangeListener()
    {
        return dataChangeListener;
    }

    public int getTotalNum()
    {
        return totalNum;
    }

    public void setTotalNum(int totalNum)
    {
        this.totalNum = totalNum;
    }

    public void setDataChangeListener(DataChangeListener dataChangeListener)
    {
        this.dataChangeListener = dataChangeListener;
    }

    public List<BeanCartItem> getGoods()
    {
        return goods;
    }

    public int getGoodsNum(BeanGoods b)
    {
        for (int i = 0; i <goods.size() ; i++)
        {
            if(goods.get(i).getBeanGoods().equals(b))
            {
                return goods.get(i).getNum();
            }
        }
        return 0;
    }
    private  int getGoodsIndex(BeanGoods b)
    {
        for (int i = 0; i <goods.size() ; i++)
        {
            if(goods.get(i).getBeanGoods().equals(b))
            {
              return i;
            }
        }
        return -1;
    }
    public  boolean isEmpty()
    {
        return  goods.isEmpty();
    }
    public BeanCart()
    {
        goods = new ArrayList<>();
        totalPrice=0;
        totalNum=0;

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addGoods(BeanGoods b)
    {
        int num=getGoodsIndex(b);

        if(num!=-1)
        {
           int i=goods.get(num).getNum();
            goods.get(num).setNum(i+1);
            GoodsRecyclerAdapter.dataChangeListener.change(goods.get(num).getNum());
        }
        else
        {
            goods.add(new BeanCartItem(b,1));
            GoodsRecyclerAdapter.dataChangeListener.change(1);
        }
        totalNum+=1;
        totalPrice+=Double.parseDouble(b.getPrice());
        dataChangeListener.change(totalNum);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void subGoods(BeanGoods b)
    {
        int num=getGoodsIndex(b);
        if(num!=-1)
        {
            int i = goods.get(num).getNum();

            int value;
            if (i == 1)
            {
                goods.remove(num);
                GoodsRecyclerAdapter.dataChangeListener.change(0);
            }
            else
            {
                goods.get(num).setNum(i - 1);
                GoodsRecyclerAdapter.dataChangeListener.change(goods.get(num).getNum());
            }
            totalNum -= 1;
            totalPrice -= Double.parseDouble(b.getPrice());
            dataChangeListener.change(totalNum);

        }
    }
    public  boolean containsGoods(BeanGoods b)
    {
        return  getGoodsIndex(b)!=-1;
    }

    public double getMailPrice()
    {
        return mailPrice;
    }

    public void setMailPrice(double mailPrice)
    {
        this.mailPrice = mailPrice;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }
}
