package com.example.autumn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerInfoActivity extends AppCompatActivity {
    private TextView textViewName;
    private TextView textViewAge;
    private TextView textViewAppearances;
    private TextView textViewGoals;
    private TextView textViewForm;
    private TextView textViewPosition;
    private ImageView imageView;
    private Button button;
    private Button home;
    private DatabaseHelper databaseHelper;

    private static final String POSITION_LW = "LW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_info);
        databaseHelper = new DatabaseHelper(this);

        imageView = findViewById(R.id.imageView2);
        button = findViewById(R.id.deleteButton);
        home = findViewById(R.id.button2);
        textViewName = findViewById(R.id.textViewTitle);
        textViewAge = findViewById(R.id.textViewAge);
        textViewAppearances = findViewById(R.id.textViewAppearances);
        textViewGoals = findViewById(R.id.textViewGoals);
        textViewForm = findViewById(R.id.textViewForm);
        textViewPosition = findViewById(R.id.textViewPosition);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        Integer rowIdToDelete = intent.getIntExtra("rowId", -1);
        String email = intent.getStringExtra("email");
        String position = intent.getStringExtra("position");
        String goals = intent.getStringExtra("goals");
        String form = intent.getStringExtra("form");
        String appearances = intent.getStringExtra("appearances");
        position.toLowerCase();


        if (position.equals("GK"))
            imageView.setImageDrawable(getDrawable(R.drawable.gk));
        else if (position.equals("RWB"))
            imageView.setImageDrawable(getDrawable(R.drawable.rwb));
        else if (position.equals("RB"))
            imageView.setImageDrawable(getDrawable(R.drawable.rb));
        else if (position.equals("CB"))
            imageView.setImageDrawable(getDrawable(R.drawable.cb));
        else if (position.equals("LB"))
            imageView.setImageDrawable(getDrawable(R.drawable.lb));
        else if (position.equals("LWB"))
            imageView.setImageDrawable(getDrawable(R.drawable.lwb));
        else if (position.equals("CDM"))
            imageView.setImageDrawable(getDrawable(R.drawable.cdm));
        else if (position.equals("CM"))
            imageView.setImageDrawable(getDrawable(R.drawable.cm));
        else if (position.equals("LM"))
            imageView.setImageDrawable(getDrawable(R.drawable.lm));
        else if (position.equals("RM"))
            imageView.setImageDrawable(getDrawable(R.drawable.rm));
        else if (position.equals("CAM"))
            imageView.setImageDrawable(getDrawable(R.drawable.cam));
        else if (position.equals("LW"))
            imageView.setImageDrawable(getDrawable(R.drawable.lw));
        else if (position.equals("RW"))
            imageView.setImageDrawable(getDrawable(R.drawable.rw));
        else if (position.equals("LF"))
            imageView.setImageDrawable(getDrawable(R.drawable.lf));
        else if (position.equals("RF"))
            imageView.setImageDrawable(getDrawable(R.drawable.rf));
        else if (position.equals("ST"))
            imageView.setImageDrawable(getDrawable(R.drawable.st));
        else
            imageView.setImageDrawable(getDrawable(R.drawable.arsenal));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name != null) {
                    // Perform the delete operation
                    boolean isDeleted = deleteRowFromDatabase(name);

                    if (isDeleted) {
                        Toast.makeText(PlayerInfoActivity.this, "Row deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PlayerInfoActivity.this, "Failed to delete row", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PlayerInfoActivity.this, "Row ID = -1", Toast.LENGTH_SHORT).show();
                }
                Intent intent1 = new Intent(PlayerInfoActivity.this, MainActivity.class);
                intent1.putExtra("email", email);
                startActivity(intent1);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PlayerInfoActivity.this, MainActivity.class);
                intent1.putExtra("email", email);
                startActivity(intent1);
            }
        });


        textViewAge.setText("Age | "+age);
        textViewName.setText(name);
        textViewGoals.setText("Goals | "+goals);
        textViewAppearances.setText("Appearances | "+appearances);
        textViewForm.setText("Form | "+form);
        textViewPosition.setText("Position | "+position);
    }
    private boolean deleteRowFromDatabase(String name) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int rowsAffected = db.delete("players",
                DatabaseHelper.COLUMN_NAME + " = ?",
                new String[]{String.valueOf(name)});
        db.close();

        return rowsAffected > 0;
    }
    public Drawable getDrawableResourceByName(String resourceName) {
        Context context = getApplicationContext(); // Replace this with your actual context

        // Get the resource ID based on the resource name (assuming the resource is in the "drawable" folder)
        int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());

        // If the resource ID is not found, return null
        if (resourceId == 0) {
            return null;
        }

        // Get the drawable using the resource ID
        return ContextCompat.getDrawable(context, resourceId);
    }
}