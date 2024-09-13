package com.example.autumn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autumn.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseHelper databaseHelper;
    private Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");


        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        //get email address to display welcome message
        Intent intent = getIntent();
        String email = intent.getExtras().getString("email");

        initPlayers(email);

        String[] str = email.split("@", 2);

        TextView textView = (TextView)toolbar.findViewById(R.id.toolbarTextView);
        textView.setText("Welcome " + str[0]);



    }

    //initialise the list of players
    public void initPlayers(String email) {
        databaseHelper = new DatabaseHelper(this);

        List<Player>products = new ArrayList<>();

        List<Player> productList = databaseHelper.getAllPlayers();


        recyclerAdapter.updateProductList(productList);

        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, Integer position, String age, String appearances, String goals, String form, String position1) {

                //send data of selected item to new activity
                Intent intent = new Intent(MainActivity.this, PlayerInfoActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("rowId", position);
                intent.putExtra("email", email);
                intent.putExtra("age", age);
                intent.putExtra("appearances", appearances);
                intent.putExtra("goals", goals);
                intent.putExtra("form", form);
                intent.putExtra("position", position1);

                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addPlayer:
                Intent cIntent = getIntent();
                String email = cIntent.getExtras().getString("email");
                Intent intent1  = new Intent(getApplicationContext(), AddPlayerActivity.class);
                intent1.putExtra("email", email);
                startActivity(intent1);
                break;

            case R.id.logout:
                Intent intent2  = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent2);
                Toast.makeText(MainActivity.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(MainActivity.this, "Task Failed", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

}