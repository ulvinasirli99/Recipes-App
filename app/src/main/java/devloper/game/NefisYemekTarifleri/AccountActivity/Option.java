package devloper.game.NefisYemekTarifleri.AccountActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import devloper.game.NefisYemekTarifleri.MainActivity;
import devloper.game.NefisYemekTarifleri.R;

public class Option extends AppCompatActivity {
    private static final String Tag = "Choose Page";
    private static final int Request_Code = 1;
    Button btnadla, btnkec;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        verifyPerimissions();
        LinearLayout linearLayout = findViewById(R.id.opitonSplash);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1500);
        animationDrawable.start();

        btnadla = findViewById(R.id.btnadla);
        btnkec = findViewById(R.id.btnkec);

        btnadla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, Register.class);
                startActivity(intent);
                finish();
            }
        });


        boolean count = false;
        if (count == false) {
            btnkec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Option.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "Lütfən birini seçiniz...", Toast.LENGTH_SHORT).show();

        }


    }

    private void verifyPerimissions() {
        Log.d(Tag, "verifyPerimissions asking user for permissions");
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1]) ==
                        PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[2]) ==
                        PackageManager.PERMISSION_GRANTED) {
            setupViewPager();

        } else {
            ActivityCompat.requestPermissions(Option.this, permissions, Request_Code);
        }

    }

    private void setupViewPager() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verifyPerimissions();
    }


}
