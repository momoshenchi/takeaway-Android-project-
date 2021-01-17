package cn.momoshenchi.takeout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import cn.momoshenchi.takeout.R;

/**
 * @version : 1.0
 * @author: momoshenchi
 * @date: 2021/1/15 - 18:32
 */
public class CardFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card, container,false);
    }
}
