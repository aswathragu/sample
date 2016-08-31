package ash.com.sample;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText username;
    private EditText password;
    private Button loginbtn;
    private Button registerbtn;
    private Button Map;
    public static final String USER = "usr";
    public static final String PASSWORD = "pwd";
    private ArrayList<String> userArray = new ArrayList<>();
    private ArrayList<String> passwordArray = new ArrayList<>();
    private String usr;
    private String pwd;
    private Boolean flag = false;
    private Boolean flags = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final DBHelper dbHelper = new DBHelper(MainActivity.this);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        loginbtn = (Button) findViewById(R.id.login_button);
        loginbtn.setEnabled(true);
        userArray = dbHelper.getUserName();
        passwordArray = dbHelper.getPassword();
        loginbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                usr = username.getText().toString();
                pwd = password.getText().toString();


                if (usr.equals("") || pwd.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Fill The Required Fields", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (userArray.size() > 0) {
                        Log.d("LOGIN", "User Array SIZE" + userArray.size());
                        int z = 1;
                        for (int k = 0; k < userArray.size(); k++) {
                            z = z + 1;
                            Log.d("LOGIN", "User Array" + userArray.get(k));
                            if (usr.equals(userArray.get(k)) && pwd.equals(passwordArray.get(k))) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                                startActivity(intent);
                                flags = true;
                            } else if (usr.equals(userArray.get(k)) && !pwd.equals(passwordArray.get(k))) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Password Incorrect", Toast.LENGTH_SHORT);
                                toast.show();
                                flags = true;
                            } else if (!usr.equals(userArray.get(k)) && pwd.equals(passwordArray.get(k))) {
                                Toast toast = Toast.makeText(getApplicationContext(), "UserName Incorrect", Toast.LENGTH_SHORT);
                                toast.show();
                                flags = true;
                            } else if (z > userArray.size() && flags == false) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Please Register", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }


                    } else if (userArray.size() == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please Register", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });


        registerbtn = (Button) findViewById(R.id.register_button);
        registerbtn.setEnabled(true);
        registerbtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                usr = username.getText().toString();
                pwd = password.getText().toString();

                if (userArray.size() == 0) {
                    if (!usr.equals("") && !pwd.equals("")) {
                        Log.e("error 2", "2");
                        userArray.add(usr);
                        passwordArray.add(pwd);
                        dbHelper.InsertLogin(usr, pwd);
                        Toast toast = Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT);
                        toast.show();

                        Log.e("error 3", "3");
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Fill the Required Fields", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    int j = 1;
                    for (int i = 0; i < userArray.size(); i++) {
                        if (usr.equals(userArray.get(i))) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Username is Taken Already..!", Toast.LENGTH_SHORT);
                            toast.show();
                            flag = true;
                        }
                        j = j + 1;
                    }

                    if (j > userArray.size() && !flag) {
                        dbHelper.InsertLogin(usr, pwd);
                        userArray.add(usr);
                        passwordArray.add(pwd);
                        Toast toast = Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }

            }
        });

    }

}


