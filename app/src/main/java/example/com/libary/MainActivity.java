package example.com.libary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment;
    FrameLayout frame1,frame2;
    ViewFlipper viewflipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        int images[] = {R.drawable.lbm, R.drawable.li3,R.drawable.libr, R.drawable.lbm1,R.drawable.li5, R.drawable.lbm,R.drawable.lbm4};
        viewflipper = findViewById(R.id.viewflipper);
        flipperImages(images[1]);
        for (int image : images) {
            flipperImages(image);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        frame1=(FrameLayout)findViewById(R.id.ftlayout);
        frame2=(FrameLayout)findViewById(R.id.ftlayout1);
        frame1.setVisibility(View.VISIBLE);
        frame2.setVisibility(View.GONE);
    }
    public void flipperImages(int image) {
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(image);
        viewflipper.addView(imageView);
        viewflipper.setFlipInterval(1100);
        viewflipper.setAutoStart(true);
        viewflipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewflipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            fragment=new HomeFragement();
        } else if (id == R.id.nav_gallery) {
            fragment=new AboutAsFragement();

        } else if (id == R.id.nav_slideshow) {
            Intent intent=new Intent(this,AdminActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent=new Intent(this,StudentActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent sharelistener = new Intent();
            sharelistener.setAction(Intent.ACTION_SEND);
            sharelistener.putExtra(Intent.EXTRA_TEXT,"http://AndroidStudioProject/libary");
            sharelistener.setType("text/Plain");
            startActivity(Intent.createChooser(sharelistener,"share via"));

        } else if (id == R.id.nav_send) {
            fragment=new Feedback();


        }
        if(fragment!=null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            frame1.setVisibility(View.GONE);
            frame2.setVisibility(View.VISIBLE);

            ft.replace(R.id.ftlayout1, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
