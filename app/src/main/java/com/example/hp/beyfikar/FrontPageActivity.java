package com.example.hp.beyfikar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.beyfikar.Retrofit.UserProfileActivity;
import com.example.hp.beyfikar.checkaddress.CheckAddressActivity;
import com.example.hp.beyfikar.orderfrontpageactivity.OrderPojo;
import com.example.hp.beyfikar.orderfrontpageactivity.OrderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FrontPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HTAG";
    private Button btnCallOrder;
    private TextView txtShowAuthNumber;
    private ImageButton imgBtnSendArrow;
    private View profileDetailView;
    private TextView txtProfileDetailName;
    private TextView txtProfileDetailPhone;
    private CircleImageView circleProfileDetailImage;
    private UserSharPrefer storeUser;
    private EditText editOderTxt;

    private Button btnSendOrderFrontPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        storeUser = new UserSharPrefer(FrontPageActivity.this); // initialize store variable of sp

        editOderTxt = (EditText) findViewById(R.id.edit_order_front_page);

        btnSendOrderFrontPage = findViewById(R.id.btn_send_order_front_page);

        btnSendOrderFrontPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FrontPageActivity.this , CheckAddressActivity.class));
            }
        });


        ///////////////////////////////////////////////////////////
  // arrow image button
       /* imgBtnSendArrow = (ImageButton) findViewById(R.id.btn_arrow_send_order_front_page);
        imgBtnSendArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(FrontPageActivity.this, "Arrow Button Selected", Toast.LENGTH_SHORT).show();
                String editMsg = editOderTxt.getText().toString();
                OrderPojo order = new OrderPojo(1, editMsg);
                sendOrder(order);
             //   showMessage("Simple OrderSend");
            }
        });*/

        //////////////////////////////////////////////////////

// comment of text
       /* txtShowAuthNumber = (TextView) findViewById(R.id.txt_show_auth_number);
        if(getIntent() !=null){
            txtShowAuthNumber.setText(getIntent().getStringExtra("phone"));
        }*/

      //  txtShowAuthNumber.setText(storeUser.getName()); // set Text of front page TextVieww
//       txtShowAuthNumber.setText(storeUser.getImgPathInFile());



// 100% CORRECT CODE JUST HIDE..... BUTTON FOR CALLL
       /* btnCallOrder = (Button) findViewById(R.id.btn_call_order);
        btnCallOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:03328469195"));

                if (ActivityCompat.checkSelfPermission(FrontPageActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });*/

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

// For drawerlayout Profile Details
        profileDetailView = navigationView.getHeaderView(0);
        circleProfileDetailImage = (CircleImageView) profileDetailView.findViewById(R.id.profile_detail_circle_iamge);
        txtProfileDetailName = (TextView) profileDetailView.findViewById(R.id.txt_profile_detail_name);
        txtProfileDetailPhone = (TextView) profileDetailView.findViewById(R.id.txt_profile_detail_phone);
        txtProfileDetailName.setText(storeUser.getName()); // get text from sp and sett in profiile
        txtProfileDetailPhone.setText(storeUser.getUserPhone());

       /* UserProfileActivity obj = new UserProfileActivity();
        String mstr = obj.passProfileImage();*/

// cz "myImgPath" is not store in UserProfileAcitivity
        SharedPreferences sp = getSharedPreferences("userInfo" , MODE_PRIVATE);
        String ss = sp.getString("myImgPath" , "mNull");

        loadImageFromStorage(ss);  // comment just for some testing ..
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.front_page, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      /*  if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /////////////////////////////////////////////////////////////////////////////////////

// method for show image from internal storage
    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            circleProfileDetailImage.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void sendOrder(OrderPojo order){
        Gson gson = new GsonBuilder().setLenient().create();  // if there is some syntext error in json array
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gminternational.com.pk/mlarafolder/laraserver/public/index.php/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        OrderService service = retrofit.create(OrderService.class);
        Call<OrderPojo> client = service.userOrder(order);
        client.enqueue(new Callback<OrderPojo>() {
            @Override
            public void onResponse(Call<OrderPojo> call, Response<OrderPojo> response) {
                Log.d(TAG, "onResponse: Order Send ");

                showMessage("Response : Order Send ");
            }

            @Override
            public void onFailure(Call<OrderPojo> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    public void showMessage(final String msg) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Alert Message")
                .setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //  Snackbar.make( constraintLayout, msg ,Snackbar.LENGTH_SHORT).show();
                        Log.d(TAG, "showMessageBox: " + msg);
                    }
                })
                .show();
    }

}
