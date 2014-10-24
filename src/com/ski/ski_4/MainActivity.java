package com.ski.ski_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.perm.kate.api.Api;


public class MainActivity extends Activity {

    private final int REQUEST_LOGIN = 1;

    ImageButton authorizeButton;
    Button logoutButton;
    Button postButton;
    EditText messageEditText;

    DbHelper mDbHelper;

    VkAccount account = new VkAccount();
    Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        startService(new Intent(BackgroundService.class.getName()));
        Log.i("Ski_c", "Service from main activity try to create");
    }


        //creating the local database  if it doesn't exist
        /*DbHelper db = new DbHelper(this);
        Log.i("Ski_c MainActivity", "DbHelper db = new DbHelper(this)");

        Log.i("Ski_c MainActivity", "db.getWritableDatabase();");
        db.getWritableDatabase();

        Log.i("Ski_c MainActivity", "passed db.getWritableDatabase() / want to be returned from db by this time");

        Log.i("Ski_c MainActivity", "db.openDataBase();");
        db.openDataBase();

        Log.i("Ski_c MainActivity", " db.receiveEntity();");
        try {
            db.receiveEntity();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("Ski_c MainActivity", " db.displayFromResult();");*/




    public void GoToBuy(View view) {
        Intent intent = new Intent(this, Container_Buy.class);
        //intent.putExtra("runner", Constants.ACTIVITY_BUY);
        Constants.ACTIVITY = 0;
        startActivity(intent);
    }

    public void GoToSell(View view) {
        Intent intent = new Intent(this, Container_Sell.class);
        //intent.putExtra("runner", Constants.ACTIVITY_SELL);
        Constants.ACTIVITY = 1;
        startActivity(intent);
        //	ft.replace(R.id.your_placehodler, new YourFragment());
        //ft.commit();
    }

    public void GoToCheck(View view) {
        Intent intent = new Intent(this, Check.class);
        //intent.putExtra("runner", Constants.ACTIVITY_SELL);
        Constants.ACTIVITY = 2;

        startActivity(intent);
        //	ft.replace(R.id.your_placehodler, new YourFragment());
        //ft.commit();
    }

    public void GoToInfo(View view) {
        Intent intent = new Intent(this, Info.class);
//intent.putExtra("runner", Constants.ACTIVITY_SELL);

        startActivity(intent);
// ft.replace(R.id.your_placehodler, new YourFragment());
//ft.commit();
    }


    /* btnFbLogin = (ImageButton) findViewById(R.id.btn_fb);

        mAsyncRunner = new AsyncFacebookRunner(facebook);

        *//**
     * Login button Click event
     * *//*
        btnFbLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("Image Button", "button Clicked");
                loginToFacebook();

                setupUI();


                //Если сессия есть создаём API для обращения к серверу
                if (account.access_token != null)
                    api = new Api(account.access_token, VkLoginActivity.API_ID);
            }
        });


    */


    /*// Your Facebook APP ID
    private static String APP_ID = "308180782571605"; // Replace with your App ID

    // Instance of Facebook Class
    private Facebook facebook = new Facebook(APP_ID);
    private AsyncFacebookRunner mAsyncRunner;
    String FILENAME = "AndroidSSO_data";
    private SharedPreferences mPrefs;

    // Buttons
    ImageButton btnFbLogin;


   *//* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);






    }*//*

    *//**
     * Function to login into facebook
     *//*
    public void loginToFacebook() {

        mPrefs = getPreferences(MODE_PRIVATE);
        String access_token = mPrefs.getString("access_token", null);
        long expires = mPrefs.getLong("access_expires", 0);

        if (access_token != null) {
            facebook.setAccessToken(access_token);

            btnFbLogin.setVisibility(View.INVISIBLE);


            Log.d("FB Sessions", "" + facebook.isSessionValid());
        }

        if (expires != 0) {
            facebook.setAccessExpires(expires);
        }

        if (!facebook.isSessionValid()) {
            facebook.authorize(this,
                    new String[]{"email", "publish_stream"},
                    new Facebook.DialogListener() {

                        @Override
                        public void onCancel() {
                            // Function to handle cancel event
                        }

                        @Override
                        public void onComplete(Bundle values) {
                            // Function to handle complete event
                            // Edit Preferences and update facebook acess_token
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("access_token",
                                    facebook.getAccessToken());
                            editor.putLong("access_expires",
                                    facebook.getAccessExpires());
                            editor.commit();

                            // Making Login button invisible
                            btnFbLogin.setVisibility(View.INVISIBLE);


                        }

                        @Override
                        public void onError(DialogError error) {
                            // Function to handle error

                        }

                        @Override
                        public void onFacebookError(FacebookError fberror) {
                            // Function to handle Facebook errors

                        }

                    });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebook.authorizeCallback(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                //авторизовались успешно
                account.access_token = data.getStringExtra("token");
                account.user_id = data.getLongExtra("user_id", 0);
                account.save(MainActivity.this);
                api = new Api(account.access_token, VkLoginActivity.API_ID);

            }
        }
    }


    *//**
     * Get Profile information by making request to Facebook Graph API
     *//*
    public void getProfileInformation() {
        mAsyncRunner.request("me", new AsyncFacebookRunner.RequestListener() {
            @Override
            public void onComplete(String response, Object state) {
                Log.d("Profile", response);
                String json = response;
                try {
                    // Facebook Profile JSON data
                    JSONObject profile = new JSONObject(json);

                    // getting name of the user
                    final String name = profile.getString("name");

                    // getting email of the user
                    final String email = profile.getString("email");

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Name: " + name + "\nEmail: " + email, Toast.LENGTH_LONG).show();
                        }

                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onIOException(IOException e, Object state) {
            }

            @Override
            public void onFileNotFoundException(FileNotFoundException e,
                                                Object state) {
            }

            @Override
            public void onMalformedURLException(MalformedURLException e,
                                                Object state) {
            }

            @Override
            public void onFacebookError(FacebookError e, Object state) {
            }
        });
    }

    *//**
     * Function to post to facebook wall
     *//*
    public void postToWall() {
        // post on user's wall.
        facebook.dialog(this, "feed", new Facebook.DialogListener() {

            @Override
            public void onFacebookError(FacebookError e) {
            }

            @Override
            public void onError(DialogError e) {
            }

            @Override
            public void onComplete(Bundle values) {
            }

            @Override
            public void onCancel() {
            }
        });

    }

    *//**
     * Function to show Access Tokens
     *//*
    public void showAccessTokens() {
        String access_token = facebook.getAccessToken();

        Toast.makeText(getApplicationContext(),
                "Access Token: " + access_token, Toast.LENGTH_LONG).show();
    }

    *//**
     * Function to Logout user from Facebook
     *//*
    public void logoutFromFacebook() {
        mAsyncRunner.logout(this, new AsyncFacebookRunner.RequestListener() {
            @Override
            public void onComplete(String response, Object state) {
                Log.d("Logout from Facebook", response);
                if (Boolean.parseBoolean(response) == true) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // make Login button visible
                            btnFbLogin.setVisibility(View.VISIBLE);

                        }

                    });

                }
            }

            @Override
            public void onIOException(IOException e, Object state) {
            }

            @Override
            public void onFileNotFoundException(FileNotFoundException e,
                                                Object state) {
            }

            @Override
            public void onMalformedURLException(MalformedURLException e,
                                                Object state) {
            }

            @Override
            public void onFacebookError(FacebookError e, Object state) {
            }
        });
    }

    private void setupUI() {
     //   authorizeButton = (ImageButton) findViewById(R.id.btn_vk);
        *//*logoutButton=(Button)findViewById(R.id.logout);
        postButton=(Button)findViewById(R.id.post);
        messageEditText=(EditText)findViewById(R.id.message);*//*
        authorizeButton.setOnClickListener(authorizeClick);
        *//*logoutButton.setOnClickListener(logoutClick);
        postButton.setOnClickListener(postClick);*//*
    }

    private View.OnClickListener authorizeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startLoginActivity();
        }
    };

    private void startLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, VkLoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);
    }*/


}