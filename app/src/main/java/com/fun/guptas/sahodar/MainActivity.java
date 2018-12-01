package com.fun.guptas.sahodar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MyAdapter.Comm {

    //private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;

    //private List<ListItem> mListItems;
    //ArrayList<ListItem> mListItems = new ArrayList<>();

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private  MyFragment frag;
    private ListItemData mListItemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.my_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


//        MyFragment frag = new MyFragment();
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.my_layout, frag, "SahodarFragment" );
//        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if( id == R.id.home) {
            Toast.makeText(this, "HOME", Toast.LENGTH_LONG).show();
            Log.d("HOME", "HOME");
        }
        if( id == R.id.articles) {
            Toast.makeText(this, "HOME", Toast.LENGTH_LONG).show();
            Log.d("ARTICLES", "ARTICLES");

            frag = new MyFragment();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.frame_container, frag, "SahodarFragment" );
            transaction.commit();
            mDrawerLayout.closeDrawer(Gravity.START, false);
        }
        if( id == R.id.setting) {
            Toast.makeText(this, "SETTING", Toast.LENGTH_LONG).show();
            Log.d("SETTING", "SETTING");
        }
        if( id == R.id.log) {
            Toast.makeText(this, "LOG", Toast.LENGTH_LONG).show();
            Log.d("LOG", "LOG");
        }
        return false;
    }

    @Override
    public void getEachData(String url, String head) {
        mListItemData = new ListItemData(head, url, this);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(frag);
        transaction.add(R.id.frame_container, mListItemData, "EachFragment" );
        transaction.commit();
    }
}