package cz.united121.android.testpupose;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewAnimator;

import cz.united121.android.testpupose.Helpers.ImageAdapterGridView;


public class MainActivity extends ActionBarActivity {

    private Button m_button1;
    private Button m_button2;


    private Button m_with_interpolar;
    private Button m_without_interpolar;

    private GridView m_image_view_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_button1 = (Button) findViewById(R.id.button1);
        m_button2 = (Button) findViewById(R.id.button2);

        m_with_interpolar = (Button) findViewById(R.id.with_interpolar);
        m_without_interpolar = (Button) findViewById(R.id.without_interpolar);

        m_image_view_group = (GridView) findViewById(R.id.image_grid_view);

        //first button
        setupAnimationFromFile(m_button1,m_button2, R.anim.anim_fade_in, R.anim.anim_fade_out);

        //second button
        AlphaAnimation alpha_in = new AlphaAnimation(0,1);
        alpha_in.setDuration(1000);
        AlphaAnimation alpha_out = new AlphaAnimation(1,0);
        alpha_out.setDuration(1000);
        RotateAnimation rotate = new RotateAnimation(360,0,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF , .5f);
        rotate.setDuration(1000);

        AnimationSet set_of_animation = new AnimationSet(true);
        set_of_animation.addAnimation(alpha_in);
        set_of_animation.addAnimation(rotate);

        setupAnimationFromParametr(m_button2, m_button1, set_of_animation, alpha_out);


        setInterpolarToButton(m_with_interpolar,m_without_interpolar,R.anim.anim_fade_out_interpolator,R.anim.anim_fade_out);

        m_image_view_group.setAdapter(new ImageAdapterGridView(MainActivity.this,R.anim.anim_image_button));
        setImageViewGroupAnimation(m_image_view_group, R.anim.anim_image_button);
    }

    private void setImageViewGroupAnimation(final View view, final int animation_ID){
        ((GridView) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,animation_ID));
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setInterpolarToButton(final View with, final View without, final int animation_ID_with, final int animation_ID_without){
        with.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                with.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, animation_ID_with));
                without.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,animation_ID_without));
            }
        });
    }

    private void setupAnimationFromFile(View view, final View second_view, final int animationID_in, final int animationID_out){
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,animationID_out));
                v.setVisibility(View.INVISIBLE);
                second_view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, animationID_in));
                second_view.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupAnimationFromParametr(View view, final View second_view, final Animation anim_in, final Animation anim_out){
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.startAnimation(anim_out);
                v.setVisibility(View.INVISIBLE);
                second_view.startAnimation(anim_in);
                second_view.setVisibility(View.VISIBLE);
            }
        });
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
        if (id == R.id.action_drawer) {
            Intent toNavigationDrawer = new Intent(MainActivity.this,NavigationDrawerTest.class);
            startActivity(toNavigationDrawer);
            return true;
        }else if (id == R.id.action_fragment) {
            FragmentManager fragmentManager = getFragmentManager();
            Fragment fragment = (Fragment) fragmentManager.findFragmentById(R.id.fragment1);
        }

        return super.onOptionsItemSelected(item);
    }
}
