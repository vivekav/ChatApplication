package vivek.felight.com.chatapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView tvsignup;
    EditText etloginmail, etloginpass;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvsignup=(TextView)findViewById(R.id.tvsignup);
        etloginmail=(EditText)findViewById(R.id.etloginmail);
        etloginpass=(EditText)findViewById(R.id.etloginpass);

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getBaseContext(),RegisterActivity.class));
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkInternet()){
                    final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(LoginActivity.this).create();
                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Internet not available, Check your internet connectivity and try again");
                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
                userLogin();
            }
        });
    }


    private void userLogin() {

        String loginmail=etloginmail.getText().toString().trim();
        String loginpass=etloginpass.getText().toString().trim();

        if(TextUtils.isEmpty(loginmail)){
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;}
        if(TextUtils.isEmpty(loginpass)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;}

    }


    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
