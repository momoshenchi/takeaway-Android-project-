package cn.momoshenchi.takeout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.momoshenchi.takeout.bean.BeanComment;
import cn.momoshenchi.takeout.adapter.CommentRecyclerAdapter;
import cn.momoshenchi.takeout.R;

public class CommentFragment extends Fragment {
    private RecyclerView comment;
    private Context mcontext;
    private  BeanComment[]list;


    public CommentFragment(BeanComment []list) {
        this.list = list;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        comment=view.findViewById(R.id.comments_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        CommentRecyclerAdapter commentRecyclerAdapter=new CommentRecyclerAdapter(list);

        comment.setAdapter(commentRecyclerAdapter);
        comment.setLayoutManager(layoutManager);

        return view;

    }
}
