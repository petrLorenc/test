package cz.united121.android.testpupose;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import cz.united121.android.testpupose.Objects.CustomSmall;


public class NavigationDrawerTest extends ActionBarActivity  {

    private static String TAG = NavigationDrawerTest.class.getName();
    private static final String CUSTOM_SMALL = "CUSTOM_SMALL";

    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;


    private BlankFragment blankFragment;
    private BlankFragment2 blankFragment2;

    private Fragment currentFragment;

    // for hamburger menu
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");

        setContentView(R.layout.activity_navigation_drawer_test);

        mListView = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        populateListView(new String[]{"Petr", "Pavel", "Sasa"});

        blankFragment = (BlankFragment) getFragmentManager().findFragmentById(R.id.fragment1);
        blankFragment2 = (BlankFragment2) getFragmentManager().findFragmentById(R.id.fragment2);

        getFragmentManager().beginTransaction()
                .hide(blankFragment2)
                .commit();
        currentFragment = blankFragment;


        //for hamburger menu
        setActionBar();


        Bundle bundle = getIntent().getExtras();
        CustomSmall customSmall_1 = bundle.getParcelable(CUSTOM_SMALL + "1");
        //CustomSmall customSmall_2 = bundle.getParcelable(CUSTOM_SMALL + "2");
    }

    private void setActionBar(){

        mActivityTitle = getTitle().toString();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                NavigationDrawerTest.this,
                mDrawerLayout,
                R.string.action_bar_open,
                R.string.action_bar_close){

                /** Called when a drawer has settled in a completely closed state. */
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    getSupportActionBar().setTitle(mActivityTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu() - > onCreateOptionsMenu()
                }

                /** Called when a drawer has settled in a completely open state. */
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    getSupportActionBar().setTitle(getString(R.string.open_drawer_menu));
                    invalidateOptionsMenu();
                }
        };

        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

    }

    private void switchFragmet(Fragment fragmentSwitchTo, String name){
        if(fragmentSwitchTo.isVisible() || fragmentSwitchTo == currentFragment){
            return;
        }
        //Make sure that current is on the to of other
        fragmentSwitchTo.getView().bringToFront();
        currentFragment.getView().bringToFront();


        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.anim_frag_slide_in,R.anim.anim_frag_slide_out)
                .hide(currentFragment)
                .show(fragmentSwitchTo)
                .addToBackStack(name)
                .commit();
        mDrawerLayout.closeDrawers();
        currentFragment = fragmentSwitchTo;
    }


    private void populateListView(final String[] names){
        mArrayAdapter = new ArrayAdapter<String>(NavigationDrawerTest.this,android.R.layout.simple_list_item_1,names);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id){
                    case 0:
                        switchFragmet(blankFragment,blankFragment.getClass().getName());
                        break;
                    case 1:
                        switchFragmet(blankFragment2,blankFragment2.getClass().getName());
                        break;
                    default:
                        Toast.makeText(NavigationDrawerTest.this,"ID is " + id,Toast.LENGTH_SHORT).show();
                }
            }
        });

        mListView.setAdapter(mArrayAdapter);
    }
    //-------------------------   OVERRIDE METHOD -----------------------------------------------------


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "onPostCreate");
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG,"onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navigation_drawer_test, menu);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,"onOptionsItemSelected");

       if(mActionBarDrawerToggle.onOptionsItemSelected(item)){
           return true;
       }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
