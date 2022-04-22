package com.omega.truecaller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.omega.truecaller.fragment.Blocking;
import com.omega.truecaller.fragment.Calls;
import com.omega.truecaller.fragment.Contacts;
import com.omega.truecaller.fragment.Messages;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayoutMain;
    private BottomNavigationView navigationViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        navigationViewMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_calls: {
                        loadFragment(new Calls());
                        return true;
                    }
                    case R.id.item_messages: {
                        loadFragment(new Messages());
                        return true;
                    }
                    case R.id.item_contacts: {
                        loadFragment(new Contacts());
                        return true;
                    }
                    case R.id.item_blocking: {
                        loadFragment(new Blocking());
                        return true;
                    }

                }
                return false;
            }
        });
        loadFragment(new Calls());
    }

    private void initData() {
        frameLayoutMain = findViewById(R.id.fragment);
        navigationViewMain = findViewById(R.id.bottomnavigation);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}