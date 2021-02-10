package com.example.myfirstdroidproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText x=findViewById(R.id.firstnum);
        final EditText y=findViewById(R.id.secondnum);
        final TextView z=findViewById(R.id.sum);
        Button yo=findViewById(R.id.button);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("values");
        yo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                int lol=Integer.parseInt(x.getText().toString())+Integer.parseInt(y.getText().toString());
//                Log.e("hiiiiiii!!!!",Integer.parseInt(x.getText().toString())+"");
////                z.setText(""+lol);
//                myRef.child("value1").setValue(x.getText().toString());
//                myRef.child("value2").setValue(y.getText().toString());
                myRef.setValue(new Values(Integer.parseInt(x.getText().toString()),Integer.parseInt(y.getText().toString())));

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Values val = snapshot.getValue(Values.class);
                        int bkl=val.val1+val.val2;
                        z.setText(""+bkl);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
}
