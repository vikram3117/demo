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
        ArrayList<User> arr = new ArrayList<User>();
        arr.add(new User(1, "Aryan Khanna"));
        arr.add(new User(2, "Vikram"));

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, R.layout.my_layout,R.id.text, getstringarray(arr));
        li.setAdapter(ad);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String input = (String) li.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, input, Toast.LENGTH_LONG).show();
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

