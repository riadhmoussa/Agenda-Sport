package com.pfeisims.riadh.notessport;

import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static DBManager dbManager;
    ArrayList<AdapterAct> listnewsDataAct = new ArrayList<AdapterAct>();
    MyCustomAdapterAct myadapterAct;


    ArrayList<AdapterIMC>    listnewsData = new ArrayList<AdapterIMC>();
    MyCustomAdapterIMC myadapter;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fm=getFragmentManager();
                choix c=new choix();
                c.show(fm,"show Fragment");
            }
        });

        dbManager=new DBManager(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void LoadACT(){


        listnewsDataAct.clear();
        Cursor cursor=dbManager.queryAct(null,null,null,DBManager.ColJour);
        if(cursor.moveToFirst()){

            do{

                listnewsDataAct.add(new AdapterAct(cursor.getString(cursor.getColumnIndex(DBManager.ColID)),cursor.getString(cursor.getColumnIndex(DBManager.ColName)),cursor.getString(cursor.getColumnIndex(DBManager.ColJour)),cursor.getString(cursor.getColumnIndex(DBManager.ColHeure))));

            }while (cursor.moveToNext());

        }

        myadapterAct=new MyCustomAdapterAct(listnewsDataAct);
        ListView listact=(ListView)findViewById(R.id.listact);
        listact.setAdapter(myadapterAct);//intisal with data
    }

    public void RefreshA(View view) {
        LoadACT();
    }



    public void refimc(View view) {
        listnewsData.clear();
        Cursor cursor=dbManager.queryIMC(null,null,null,DBManager.ColDate);
        if(cursor.moveToFirst()){

            do{

                listnewsData.add(new AdapterIMC(cursor.getString(cursor.getColumnIndex(DBManager.ColI)),
                        cursor.getString(cursor.getColumnIndex(DBManager.ColDate)),
                        cursor.getString(cursor.getColumnIndex(DBManager.ColPoids)),
                        cursor.getString(cursor.getColumnIndex(DBManager.ColTaille)),
                        cursor.getString(cursor.getColumnIndex(DBManager.ColCalImc))));

            }while (cursor.moveToNext());

        }
        myadapter=new MyCustomAdapterIMC(listnewsData);
        ListView  lsNews=(ListView)findViewById(R.id.listimc);
        lsNews.setAdapter(myadapter);//intisal with data



    }

    public void Cancel(View view) {
        this.finish();
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Listact tab1=new Listact();
                    return tab1;
                case 1:
                    Listimc tab2=new Listimc();
                    return tab2;
                case 2:
                    GPS tab3=new GPS();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }



    void RefreshAct(){

    }

    private class MyCustomAdapterAct extends BaseAdapter {
        public ArrayList<AdapterAct> listnewsDataAdpater ;

        public MyCustomAdapterAct(ArrayList<AdapterAct>  listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.ticket_act, null);

            final   AdapterAct s = listnewsDataAdpater.get(position);

            TextView tvActName=( TextView)myView.findViewById(R.id.tvActName);
            tvActName.setText(s.Name);
            TextView tcActDate=(TextView)myView.findViewById(R.id.tcActDate);
            tcActDate.setText(s.Jour+" "+s.Heure);
            ImageView ivDelete=(ImageView)myView.findViewById(R.id.ivDelete);
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] SelectionArgs={s.ID};
                    int count=dbManager.DeleteAct("ID=?",SelectionArgs);
                    if(count>0){
                        LoadACT();
                    }else{
                        Toast.makeText(view.getContext(),"Cannot Delete",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return myView;
        }

    }

    private class MyCustomAdapterIMC extends BaseAdapter {
        public  ArrayList<AdapterIMC>  listnewsDataAdpater ;

        public MyCustomAdapterIMC(ArrayList<AdapterIMC>  listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.ticket_imc, null);

            final   AdapterIMC s = listnewsDataAdpater.get(position);

            TextView tvDate=( TextView)myView.findViewById(R.id.tvDate);
            tvDate.setText(s.Date);
            TextView tvIMC=( TextView)myView.findViewById(R.id.tvIMC);
            tvIMC.setText(s.Poids+","+s.Taille+"=>"+s.CalImc);

            return myView;
        }

    }
}