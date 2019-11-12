package com.example.birdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonGoToSearch, buttonSubmit;
    EditText editTextZipCode, editTextPersonName, editTextBirdName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToSearch = findViewById(R.id.buttonGoToSearch);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        editTextZipCode = findViewById(R.id.editTextZipCode);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextBirdName = findViewById(R.id.editTextBirdName);

        buttonSubmit.setOnClickListener(this);
        buttonGoToSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Bird");

        if (buttonSubmit == v) {

            String birdName = editTextBirdName.getText().toString();
            String personName = editTextPersonName.getText().toString();
            int zipCode = Integer.parseInt(editTextZipCode.getText().toString());

            Bird myBird = new Bird(birdName, zipCode, personName);

            myRef.push().setValue(myBird);

            Toast.makeText(this, "Bird Submitted", Toast.LENGTH_SHORT).show();

        }

        else if (buttonGoToSearch == v){
            Intent searchPage = new Intent(this, Searchpage.class);
            startActivity(searchPage);

        }



    }
}
