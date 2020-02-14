package devloper.game.NefisYemekTarifleri;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import devloper.game.NefisYemekTarifleri.AccountActivity.Login;
import devloper.game.NefisYemekTarifleri.AccountActivity.Option;


public class SplashActivity extends AppCompatActivity {
    Context context;
    Button anaAc;
    private TextView tv;
    private ImageView iv;
    MediaPlayer music;
    Animation down, up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        music = MediaPlayer.create(this, R.raw.chame);
//        music.start();



        LinearLayout linearLayout = findViewById(R.id.Begin);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        tv = findViewById(R.id.Tv);
        iv = findViewById(R.id.Iv);

        final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        Animation mytran = AnimationUtils.loadAnimation(this, R.anim.mytran);
        tv.startAnimation(mytran);
        iv.startAnimation(mytran);
        down = AnimationUtils.loadAnimation(this, R.anim.down);
        up = AnimationUtils.loadAnimation(this, R.anim.up);
        tv.setAnimation(up);
        iv.setAnimation(down);

        context = this;
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5090);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (internetBaglantisi()){
                        Intent i=new Intent(context, Option.class);
                        startActivity(i);
                        finish();
                    }else {
                        Intent l=new Intent(context,Baglanti.class);
                        startActivity(l);
                        finish();
                    }
                }
            }
        };
        timer.start();

    }

    boolean internetBaglantisi() {
        ConnectivityManager conMgr=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo()!=null && conMgr.getActiveNetworkInfo().isAvailable() &&
                conMgr.getActiveNetworkInfo().isConnected()){
            return true;
        }else {
            return  false;
        }


    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        music.release();
//        finish();
//    }


}


