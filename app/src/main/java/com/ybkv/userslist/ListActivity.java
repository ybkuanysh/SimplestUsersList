package com.ybkv.userslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class ListActivity extends AppCompatActivity {

    public static final String USER_ADAPTER_KEY = "user_adapter_key";
    public static final String USER_ID_KEY = "user_id_key";
    public static final String USER_EDIT_CONTEXT_KEY = "user_edit_context_key";

    ListView listView;
    FloatingActionButton buttonAdd;

    static ArrayList<String> users = new ArrayList<String>();
    static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Collections.addAll(users, "Tom", "Bob", "Sam",
                "Alice", "Dmitry", "Alexey", "Oleg", "Alina", "Daniel", "Marina");

        Intent intentEdit = new Intent(ListActivity.this, EditActivity.class);

        listView = findViewById(R.id.list_view);
        buttonAdd = findViewById(R.id.floating_button_add);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, users);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intentEdit.putExtra(USER_ADAPTER_KEY, users);
                intentEdit.putExtra(USER_EDIT_CONTEXT_KEY, 1);
                intentEdit.putExtra(USER_ID_KEY, i);
                startActivity(intentEdit);

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentEdit.putExtra(USER_ADAPTER_KEY, users);
                intentEdit.putExtra(USER_EDIT_CONTEXT_KEY, 0);
                intentEdit.putExtra(USER_ID_KEY, users.size() + 1);
                startActivity(intentEdit);

            }
        });
    }


    public static void addUser(String username) {
        users.add(username);
        adapter.notifyDataSetChanged();
    }

    public static void editUser(int pos, String username) {
        users.remove(pos);
        users.add(pos, username);
        adapter.notifyDataSetChanged();
    }
}