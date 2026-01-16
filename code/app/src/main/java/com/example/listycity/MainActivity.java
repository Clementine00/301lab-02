package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button buttonAdd;
    Button buttonDelete;
    Button buttonConfirm;

    EditText editCity;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        editCity = findViewById(R.id.editCity);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        cityList = findViewById(R.id.city_list);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        String []cities = {"Edmonton", "Vancouver", "Moscow","Sydney","Berlin","Vienna","Tokyo","Osaka","Beijing","New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, dataList);
        cityList.setAdapter(cityAdapter);
        editCity.setVisibility(View.VISIBLE);
        editCity.requestFocus();

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            if (selectedPosition == position) {
                selectedPosition = -1;
                cityList.setItemChecked(position, false);
            } else {
                selectedPosition = position;
                cityList.setItemChecked(position, true);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCity.setVisibility(View.VISIBLE);
                buttonConfirm.setVisibility(View.VISIBLE);
            }
        });

        buttonDelete.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
                cityList.clearChoices();
            } else {
                Toast.makeText(MainActivity.this, "No City has been Selected", Toast.LENGTH_SHORT).show();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = editCity.getText().toString().trim();
                if (!newCity.isEmpty()){
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    editCity.setText("");
                    editCity.setVisibility(View.GONE);
                    buttonConfirm.setVisibility(View.GONE);
                }
            }
        });




    }
}