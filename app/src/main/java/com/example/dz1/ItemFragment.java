package com.example.dz1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemFragment extends Fragment {

    TextView textView;
    public static final String KEY_NUM = "NUM";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item, container, false);
        textView = (TextView) rootView.findViewById(R.id.fragTxt);
        setText(getNum());
        return rootView;
    }

    public void setText(int num) {
        ColorResolver.setColorAndNum(textView, num, (Context) getActivity());
    }

    public int getNum() {
        Bundle bundle = getArguments();
        int num = bundle != null ? bundle.getInt(KEY_NUM) : 0;
        return num;
    }
}
