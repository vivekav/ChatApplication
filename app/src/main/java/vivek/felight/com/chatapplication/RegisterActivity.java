package vivek.felight.com.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText etemail,etpass;
    Button btnregister;
    TextView tvsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnregister=(Button)findViewById(R.id.btnregister);
        etemail=(EditText)findViewById(R.id.etemail);
        etpass=(EditText)findViewById(R.id.etpass);
        tvsignup=(TextView)findViewById(R.id.tvcheck);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    registerUser();
            }
        });

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void registerUser(){
        String email=etemail.getText().toString().trim();
        String pass=etpass.getText().toString().trim();
        if(TextUtils.isEmpty(email))
            
    }
}
