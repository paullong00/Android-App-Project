package com.example.autumn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddPlayerActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextAppearances;
    private EditText editTextGoals;
    private EditText editTextForm;
    public EditText editTextPosition;
    private Button button;
    private Button home;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_club);

        databaseHelper = new DatabaseHelper(this);

        button = findViewById(R.id.button);
        home = findViewById(R.id.button3);

        String email = getIntent().getStringExtra("email");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cIntent = getIntent();
                String email = cIntent.getExtras().getString("email");

                editTextName = findViewById(R.id.editTextName);
                editTextAge = findViewById(R.id.editTextAge);
                editTextAppearances = findViewById(R.id.editTextAppearances);
                editTextGoals = findViewById(R.id.editTextGoals);
                editTextForm = findViewById(R.id.editTextForm);
                editTextPosition = findViewById(R.id.editTextPosition);



                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String appearances = editTextAppearances.getText().toString();
                String goals = editTextGoals.getText().toString();
                String form = editTextForm.getText().toString();
                String position = editTextPosition.getText().toString();

                Player player = new Player(name, age, appearances, goals, form, position);

                addPlayerToDatabase(player);

                Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPlayerActivity.this, MainActivity.class);
                intent.putExtra("email", email);

                startActivity(intent);
            }
        });

    }
    private boolean addPlayerToDatabase(Player player) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", (byte[]) null);
        contentValues.put("name", player.getName());
        contentValues.put("age", player.getAge());
        contentValues.put("appearances", player.getAppearances());
        contentValues.put("goals", player.getGoals());
        contentValues.put("form", player.getForm());
        contentValues.put("position", player.getPosition());

        db.insert("players", null, contentValues);

        db.close();

        return true;
    }
}