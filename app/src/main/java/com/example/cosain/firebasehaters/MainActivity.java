package com.example.cosain.firebasehaters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

   EditText editLogtime;
    EditText editLog;
    Button saveButton , listviewButton;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editLog = (EditText) findViewById(R.id.log);
        editLogtime = (EditText) findViewById(R.id.logtime);
        saveButton = (Button) findViewById(R.id.button2);
        listviewButton = (Button) findViewById(R.id.button);

        Firebase.setAndroidContext(this);


        listviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Listview Activity",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ListData.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Toast.makeText(MainActivity.this, "Data saved",Toast.LENGTH_LONG).show();
                Firebase ref = new Firebase(Config.FIREBASE_URL);
                //DatabaseReference databasePerson = FirebaseDatabase.getInstance().getReference("Person");

                DatabaseReference databaseLog = FirebaseDatabase.getInstance().getReference("Log");
                String log = editLog.getText().toString().trim();
                String timestamp = editLogtime.getText().toString().trim();
                LogDB logDB = new LogDB();
                logDB.setTag_content(log);
                logDB.setTime_stamp(timestamp);
                String userLog = databaseLog.push().getKey();
                databaseLog.child(userLog).setValue(logDB);


            }
        });
    }
}
