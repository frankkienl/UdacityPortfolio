package nl.frankkie.portfolio;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Formatter;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private CharSequence mTitle;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //I like to keep my onCreate short,
        //move all UI-creation code to separate methods.
        initToolbar();
        initUI();
    }

    public void initToolbar() {
        mTitle = getTitle();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);
    }

    public void initUI(){
        findViewById(R.id.btn_build_it_bigger).setOnClickListener(this);
        findViewById(R.id.btn_capstone).setOnClickListener(this);
        findViewById(R.id.btn_spotify_streamer).setOnClickListener(this);
        findViewById(R.id.btn_super_duo_library).setOnClickListener(this);
        findViewById(R.id.btn_super_duo_scores).setOnClickListener(this);
        findViewById(R.id.btn_xyz_reader).setOnClickListener(this);
    }

    /**
     * NOTE: this method comes from the interface View.OnClickListener
     * @param v the View that has been clicked
     */
    @Override
    public void onClick(View v) {
        //As all buttons behave the same, lets reduce code-duplication
        String text = getString(R.string.button_not_found);
        String template = getString(R.string.toast_app_todo);
        switch (v.getId()){
            case R.id.btn_spotify_streamer: {
                PackageManager pm = getPackageManager();
                Intent spotifyStreamerIntent = pm.getLaunchIntentForPackage("nl.frankkie.spotifystreamer");
                if (spotifyStreamerIntent == null){
                    //not installed, send user to place to download apk
                    Intent downloadIntent = new Intent();
                    downloadIntent.setAction(Intent.ACTION_VIEW);
                    downloadIntent.setData(Uri.parse("https://github.com/frankkienl/SpotifyStreamer/releases"));
                    startActivity(downloadIntent);
                    text = String.format(getString(R.string.app_not_installed), getString(R.string.spotify_streamer));
                } else {
                    //Start Spotify Streamer
                    text = String.format(getString(R.string.opening_app), getString(R.string.spotify_streamer));
                    startActivity(spotifyStreamerIntent);
                }
                break;
            }
            case R.id.btn_build_it_bigger: {
                //http://developer.android.com/guide/topics/resources/string-resource.html#FormattingAndStyling
                text = String.format(template,getString(R.string.build_it_bigger));
                break;
            }
            case R.id.btn_capstone: {
                text = String.format(template,getString(R.string.capstone));
                break;
            }
            case R.id.btn_super_duo_library: {
                text = String.format(template,getString(R.string.super_duo_library));
                break;
            }
            case R.id.btn_super_duo_scores: {
                text = String.format(template,getString(R.string.super_duo_scores));
                break;
            }
            case R.id.btn_xyz_reader: {
                text = String.format(template,getString(R.string.xyz_reader));
                break;
            }
        }
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
