package com.example.hp.beyfikar.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.hp.beyfikar.R;
import com.example.hp.beyfikar.UserSharPrefer;

public class UserProfileUpdateActivity extends AppCompatActivity {

    private ImageView imgProfile;
    private EditText editName;
    private EditText editPhone;
    private EditText editAddress;
    private Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_update);

        imgProfile = findViewById(R.id.img_user_profile_update_detail);
        editName = findViewById(R.id.edit_name_user_profile_update_activity);
        editPhone = findViewById(R.id.edit_phone_user_profile_update_activity);
        editAddress = findViewById(R.id.edit_address_user_profile_update_activity);

        UserSharPrefer storeUser = new UserSharPrefer(this);
        editName.setText(storeUser.getName());
        editPhone.setText(storeUser.getUserPhone());
        editAddress.setText(storeUser.getUserAddress());
    }
}
