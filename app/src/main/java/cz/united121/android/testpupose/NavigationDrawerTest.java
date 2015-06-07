package cz.united121.android.testpupose;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class NavigationDrawerTest extends ActionBarActivity  {

    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;


    private BlankFragment blankFragment;
    private BlankFragment2 blankFragment2;

    private android.support.v4.app.Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_test);

        mListView = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        populateListView(new String[]{"Petr", "Pavel", "Sasa"});

        blankFragment = (BlankFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        blankFragment2 = (BlankFragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        getSupportFragmentManager().beginTransaction()
                .hide(blankFragment2)
                .commit();
        currentFragment = blankFragment;
    }

    private void switchFragmet(android.support.v4.app.Fragment fragmentSwitchTo, String name){
        if(fragmentSwitchTo.isVisible() || fragmentSwitchTo == currentFragment){
            return;
        }
        //Make sure that current is on the to of other
        fragmentSwitchTo.getView().bringToFront();
        currentFragment.getView().bringToFront();


        getSupportFragmentManager().beginTransaction()
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navigation_drawer_test, menu);
        return super.onCreateOptionsMenu(menu);
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
}
