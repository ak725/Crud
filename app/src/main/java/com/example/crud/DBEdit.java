package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DBEdit extends AppCompatActivity {

    private final int UPDATE_MODE = 6162;
    int MODE = 0;
    EditText Name;
    EditText PhoneNumber;
    Button Submit;
    UserData currentUserData;
    int current_uid;

    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        Intent i = getIntent();
        Name = findViewById(R.id.edittext_name);
        PhoneNumber = findViewById(R.id.editext_phonenumber);
        Submit = findViewById(R.id.button_create_contact);
        database = Database.getDatabase(this);

        if(i.getIntExtra("MODE",-1) == UPDATE_MODE){
            MODE = UPDATE_MODE;
            current_uid = i.getIntExtra("CURRENT_USER",-1);
            currentUserData = getCurrentUser(current_uid);
            Name.setText(currentUserData.firstName);
            PhoneNumber.setText(currentUserData.phone_number);
            Submit.setText(R.string.Button_Update_Text);
        }

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFieldsEmpty()){
                    if(MODE == UPDATE_MODE){
                        // UPDATE the UserData.
                        UserData userData = getCurrentUser(current_uid);
                        UserData updatedUserData = getEnteredUser();
                        userData.firstName = updatedUserData.firstName;
                        userData.phone_number = updatedUserData.phone_number;
                        database.userDao().update(userData);
                    }else{
                        //Insert the New USER
                        UserData userData = getEnteredUser();
                        database.userDao().insertAll(userData);
                    }
                    showToastMessage("Action Successful !!");
                    setResult(RESULT_OK);
                    finish();
                }else{
                    showToastMessage("The Fields Can't Be Empty");
                }
            }
        });


    }

    private UserData getCurrentUser(int uid){
        UserData userData;
        userData = database.userDao().getUserById(uid);
        return userData;
    }

    private UserData getEnteredUser(){
        UserData userData = new UserData();
        userData.firstName = Name.getText().toString();
        userData.phone_number = PhoneNumber.getText().toString();

        return userData;
    }

    private boolean isFieldsEmpty(){
        return Name.getText().toString().isEmpty() || PhoneNumber.getText().toString().isEmpty() ;
    }

    private void showToastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }
}