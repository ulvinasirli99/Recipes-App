package devloper.game.NefisYemekTarifleri.AccountActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import devloper.game.NefisYemekTarifleri.MainActivity;
import devloper.game.NefisYemekTarifleri.R;


public class Register extends AppCompatActivity {
    CheckBox showpassword;
    private EditText inputEmail, inputPassword,username;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private TextView singnPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        showpassword=findViewById(R.id.chkGoster);
        singnPass=findViewById(R.id.LoginGet);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        username=findViewById(R.id.userName);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();




        singnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String userName = username.getText().toString().trim();

                if (TextUtils.isEmpty(email)&& TextUtils.isEmpty(password)&& TextUtils.isEmpty(userName)){
                    inputPassword.setError("Parolani girin");
                    inputEmail.setError("Email adressini girin");
                    username.setError("Kullanici adini girin");
                }

                if (TextUtils.isEmpty(email)){
                    inputEmail.setError("Email adressini girin");
                }

                if (TextUtils.isEmpty(password)) {
                    inputPassword.setError("Parolani girin");
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userName)){
                    username.setError("Kullanici adini girin");
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Register.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });

        LinearLayout linearLayout = findViewById(R.id.registerM);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1500);
        animationDrawable.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}

