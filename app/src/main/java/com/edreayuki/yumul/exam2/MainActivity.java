package com.edreayuki.yumul.exam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    EditText eFname, eLname, eE1, eE2;
    TextView eAve;
    int index;
    ArrayList<String> keyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        root = db.getReference("Student");
        eFname = findViewById(R.id.eFname);
        eLname = findViewById(R.id.eLname);
        eE1 = findViewById(R.id.eE1);
        eE2 = findViewById(R.id.eE2);
        eAve = findViewById(R.id.txtAve);
        keyList = new ArrayList<>();
    }

    public void saveToFirebase (View v) {

        if (eFname.getText().toString().trim().length()<=0 || eLname.getText().toString().trim().length() <=0 || eE1.getText().toString().trim().length() <=0 || eE2.getText().toString().trim().length() <=0) {
            Toast.makeText(MainActivity.this, "Some items are empty. Please try again.", Toast.LENGTH_LONG).show();
        } else {
            String fname = eFname.getText().toString().trim();
            String lname = eLname.getText().toString().trim();
            Long e1 = Long.parseLong(eE1.getText().toString().trim());
            Long e2 = Long.parseLong(eE2.getText().toString().trim());
            Long ave = ((e1+e2)/2);
            Student studentAve = new Student(fname,lname,e1,e2,ave);
            String key = root.push().getKey();
            root.child(key).setValue(studentAve);
            Toast.makeText(this,"The record has been added to the database",Toast.LENGTH_LONG).show();
            Log.d("4itf", "Record was added to database");
            root.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ss: dataSnapshot.getChildren()) {
                        keyList.add(ss.getKey());
                    }
                    index = (int) dataSnapshot.getChildrenCount() - 1;
                    Log.d("4itf", "get index successful " + index);
                    Log.d("4itf", "key is " + dataSnapshot.child(keyList.get(index)));
                    Log.d("4itf", "value is " + dataSnapshot.child(keyList.get(index)).getValue(Student.class));
                    Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                    Log.d("4itf", "datasnapshot is " + stud);

                    eAve.setText(stud.getAve().toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

 /*   private void displayAve() {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss: dataSnapshot.getChildren()) {
                    keyList.add(ss.getKey());
                }
                index = (int) dataSnapshot.getChildrenCount() - 1;
                Log.d("4itf", "get index successful " + index);
                Log.d("4itf", "key is " + dataSnapshot.child(keyList.get(index)));
                Log.d("4itf", "value is " + dataSnapshot.child(keyList.get(index)).getValue(Student.class));
                Student stud = dataSnapshot.child(keyList.get(index)).getValue(Student.class);
                Log.d("4itf", "datasnapshot is " + stud);

                eAve.setText(stud.getAve().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
*/
}
