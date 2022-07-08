package com.ybkv.userslist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    TextView textView;
    TextInputEditText editText;
    Button buttonCancel, buttonSave;

    Bundle extra;
    ArrayList<String> users;
    int position, editMode;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Инициализируем элементы интерфейса
        editText = findViewById(R.id.edit_text_field);
        textView = findViewById(R.id.text_view_edit_layout);
        buttonCancel = findViewById(R.id.edit_user_cancel_button);
        buttonSave = findViewById(R.id.edit_user_save_button);

        // Получаем переданные данные из ListActivity
        extra = getIntent().getExtras();

        position = extra.getInt(ListActivity.USER_ID_KEY);
        users = extra.getStringArrayList(ListActivity.USER_ADAPTER_KEY);
        editMode = extra.getInt(ListActivity.USER_EDIT_CONTEXT_KEY);


        // Проверяем режим изменения данных и обновляем TextView, EditText
        if (editMode == 1) {
            textView.setText("Edit username");
            editText.setText(users.get(position));
        } else {
            textView.setText("Add new user");
        }

        // Подключаем лисенер на изменение текста и сохраняем изменения в переменную
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                username = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Обрабатываем нажатия кнопок сохрананения и отмены

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editMode == 1) {
                    ListActivity.editUser(position, username);
                } else {
                    ListActivity.addUser(username);
                }
                finish();
            }
        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}