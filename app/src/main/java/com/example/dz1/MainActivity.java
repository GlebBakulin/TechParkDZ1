package com.example.dz1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment flList = getSupportFragmentManager().findFragmentById(R.id.flList);
        if (flList == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.flList, new ListFragment()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(int num) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ItemFragment.KEY_NUM, num);
        itemFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flList, itemFragment);
        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.commit();
    }
}
