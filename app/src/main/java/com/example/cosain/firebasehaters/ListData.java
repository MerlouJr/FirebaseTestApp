package com.example.cosain.firebasehaters;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListData extends Activity {

    ListView lv;
    ArrayList<String> al = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
//    String key;
//    String value = null;
    final ArrayList<String> keyList = new ArrayList<>();
    final ArrayList<String> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        lv = (ListView) findViewById(R.id.list_item);

        myRef = FirebaseDatabase.getInstance().getReference("Log");
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,al);
        lv.setAdapter(adapter);
        Firebase.setAndroidContext(this);

        new Firebase("https://fir-haters.firebaseio.com/Log")
                .addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                        adapter.add((String)dataSnapshot.child("tag_content").getValue());

                    }

                    @Override
                    public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                        adapter.remove((String)dataSnapshot.child("tag_content").getValue());

                    }

                    @Override
                    public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {
                        adapter.remove((String)dataSnapshot.child("tag_content").getValue());

                    }

                    @Override
                    public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( final AdapterView<?> parent, View view, final int position, long id) {

                myRef.getRoot().child("Log")
                        .addListenerForSingleValueEvent(new ValueEventListener()  {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChildren()){
                                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                                    firstChild.getRef().removeValue();
                                }
                                Toast.makeText(ListData.this,"This item is deleted....",Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });



    }
    }