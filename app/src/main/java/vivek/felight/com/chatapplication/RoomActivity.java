package vivek.felight.com.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RoomActivity extends AppCompatActivity {

    DatabaseReference dbrroot;
    EditText etchat;
    Button btnchat;
    TextView tvtext;
    String username,roomname,temp_key;
    String chat_message, chat_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        etchat =(EditText)findViewById(R.id.etchat);
        tvtext=(TextView)findViewById(R.id.tv);
        btnchat=(Button)findViewById(R.id.btnsend);
        username=getIntent().getExtras().get("user_name").toString();
        roomname=getIntent().getExtras().get("room_name").toString();
        setTitle("Room "+roomname);

        dbrroot= FirebaseDatabase.getInstance().getReference().child(roomname);
        Map<String,Object> map= new HashMap<String,Object>();
        temp_key = dbrroot.push().getKey();
        dbrroot.updateChildren(map);

        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference messages= dbrroot.child(temp_key);
                Map<String,Object> map2=new HashMap<String,Object>();
                map2.put("name",username);
                map2.put("messages", etchat.getText().toString());
                messages.updateChildren(map2);
                etchat.setText("");
                etchat.setHint("Type your message here");
            }
        });


        dbrroot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_data(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_data(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        
    }

    private void append_data(DataSnapshot dataSnapshot) {
        Iterator i= dataSnapshot.getChildren().iterator();
        while(i.hasNext()){
            chat_message=(String)((DataSnapshot)i.next()).getValue();
            chat_username=(String)((DataSnapshot)i.next()).getValue();
            tvtext.append(chat_username+": "+chat_message+" \n\n");
        }
        }
    }

