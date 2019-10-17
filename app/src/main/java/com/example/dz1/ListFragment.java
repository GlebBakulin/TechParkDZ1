package com.example.dz1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragment extends Fragment implements DataAdapter.ItemClickListener {

    private static final String KEY_ELEMENTS = "ELEMENTS";
    ArrayList<Integer> elements = new ArrayList<>();
    OnFragmentInteractionListener onFragmentInteractionListener;
    View rootView;

    void setItems(ArrayList<Integer> arLst) {
        for (int i = 1; i < 101; i++) {
            arLst.add(i);
        }
    }

    void addItem(ArrayList<Integer> arLst) {
        arLst.add(arLst.get(arLst.size() - 1) + 1);
    }

    int numOfColum() {
//        switch (getResources().getConfiguration().orientation) {
//            case (Configuration.ORIENTATION_PORTRAIT):
//                return 3;
//            break;
//            case (Configuration.ORIENTATION_LANDSCAPE):
//                return 4;
//            break;
//            default:
//                return 1;
//            break;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return 3;
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return 4;
        else
            return 1;
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            if (rootView != null) {
                if ((ViewGroup) rootView.getParent() != null) {
                    ((ViewGroup) rootView.getParent()).removeView(rootView);
                }
                return rootView;
            }

            rootView = inflater.inflate(R.layout.fragment_list, container, false);
            if (savedInstanceState != null) {
                elements = savedInstanceState.getIntegerArrayList(KEY_ELEMENTS);
            } else setItems(elements);

            final RecyclerView recyclerView = rootView.findViewById(R.id.rvNumbers);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numOfColum()));

            final DataAdapter dataAdapter = new DataAdapter(elements, getActivity(), this);
            recyclerView.setAdapter(dataAdapter);

            rootView.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem(elements);
                    dataAdapter.notifyItemInserted(elements.size() - 1);
                }
            });
            return rootView;
        }

        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onSaveInstanceState (@NonNull Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putIntegerArrayList(KEY_ELEMENTS, elements);
        }

        @Override
        public void onItemClick ( int position){
            onFragmentInteractionListener = (OnFragmentInteractionListener) getActivity();
            onFragmentInteractionListener.onFragmentInteraction(elements.get(position));
        }

        interface OnFragmentInteractionListener {
            void onFragmentInteraction(int num);
        }
    }
