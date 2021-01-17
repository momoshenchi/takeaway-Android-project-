package cn.momoshenchi.takeout.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.momoshenchi.takeout.bean.BeanMenu;
import cn.momoshenchi.takeout.R;

public abstract class MenuRecyclerAdapter extends  RecyclerView.Adapter<MenuRecyclerAdapter.VH> {
    private BeanMenu[] list;
    private Context mContext;



    private int mSelectedPosition;
    public void setSelectedPosition(int position) {
        list[mSelectedPosition].isSelected = false;
        notifyItemChanged(mSelectedPosition);
        list[position].isSelected = true;
        notifyItemChanged(position);
        mSelectedPosition = position;
    }


    public MenuRecyclerAdapter(BeanMenu[] list) {
        this.list = list;
    }
     class VH extends RecyclerView.ViewHolder {

        public final TextView menu;

        public VH(View v) {
            super(v);
            menu = v.findViewById(R.id.menu);

        }

    }
    @NonNull
    @Override
    public MenuRecyclerAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_menu, parent, false);
        final VH vh=new VH(v);
        v.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position=vh.getAdapterPosition();
                move(position);
                v.setBackgroundColor(Color.rgb(238,238,238));
                vh.menu.setBackgroundColor(Color.WHITE);
                vh.menu.setTextColor(Color.BLACK);
            }
        });
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuRecyclerAdapter.VH holder, final int position) {
        holder.menu.setText(list[position].getMenuname());

    }
    public abstract  void move(int position);



    @Override
    public int getItemCount() {
        return list.length;
    }
}
