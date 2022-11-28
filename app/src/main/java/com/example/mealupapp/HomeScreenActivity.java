package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {
    private Button button;
    private TextView displayUser;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //retrieves the user object when signed in from the login screen
        // or after user creates an account.
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");

        displayUser = findViewById(R.id.hUsername);
        displayUser.append(user.getUsername());

        button = findViewById(R.id.recipeButton);
        button.setOnClickListener(v -> openSearchActivity());

        btn = findViewById(R.id.calculatorButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), MacroCalculator.class);
                startActivity(intent);
            }
        });
    }

    public void openSearchActivity(){
        Intent intent = new Intent(this, SearchBarActivity.class);
        startActivity(intent);
    }
}