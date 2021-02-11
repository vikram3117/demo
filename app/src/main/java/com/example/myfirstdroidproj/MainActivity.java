package com.example.myfirstdroidproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView li = findViewById(R.id.list);
        final EditText x=findViewById(R.id.entry);
        final ArrayList<User>[] arr = new ArrayList[]{new ArrayList<User>()};
        final Button jodo=findViewById(R.id.butt);
        FirebaseDatabase fire= FirebaseDatabase.getInstance();

        final DatabaseReference fire_ref= fire.getReference("this");
        jodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fire_ref.push().setValue(new User(1,x.getText().toString()));
                fire_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arr[0] =new ArrayList<User>();
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                            arr[0].add(dataSnapshot.getValue(User.class));
                        }
                        CustomAdapter ad = new CustomAdapter(MainActivity.this,arr[0]);
                        li.setAdapter(ad);
                        ad.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
            }
        });






    }
    public ArrayList<String> getstringarray(ArrayList<User> arr)
    {
        ArrayList<String> ans=new ArrayList<String>();
        for(User i:arr)
        {
            ans.add(i.name);
        }
        return ans;
    }
}

