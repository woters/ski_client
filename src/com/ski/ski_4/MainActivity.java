package com.ski.ski_4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.AppEventsLogger;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*import com.perm.kate.api.Api;*/


public class MainActivity extends Activity {

    private final int REQUEST_LOGIN = 1;

    private LoginButton loginBtn;

    private UiLifecycleHelper uiHelper;

    /*private ImageButton loginBtn;*/
    Button logoutButton;
    Button postButton;
    EditText messageEditText;

    DbHelper mDbHelper;

    /*VkAccount account = new VkAccount();
    Api api;*/

    protected static int index;
    protected static boolean service;

    public static String userName = "";


    static EditText editText1;
    static EditText editText2;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        printKeyHash(this);

        /*Constants.ACTIVITY  = 5;
        Log.i("Ski_c MA  Constants.ACTIVITY on creating is ", String.valueOf(Constants.ACTIVITY));*/

        /*Log.i("Ski_c MA before if local index is ", String.valueOf(index));*/

        index = PreferenceManager.getDefaultSharedPreferences(this).getInt("index", 0);


        Log.i("Ski_c MA startup SharedPreferences index is ", String.valueOf(index));

        /*index++;
        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("index", index).commit();*/


        /*Log.i("Ski_c MA after if index is ", String.valueOf(index));*/

        setContentView(R.layout.activity_main);


        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        /*startService(new Intent(BackgroundService.class.getName()));
        Log.i("Ski_c MA ", "Service try to create");*/

        //service = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("service", false);

        //Log.i("Ski_c MA startup SharedPreferences service is ", String.valueOf(service));

        /*Log.i("Ski_c MA  Constants.SERVICE is ", String.valueOf(Constants.SERVICE));*/

        if (Constants.SERVICE ==0) {
        /*if (service==false) {*/

                    /*Constants.SERVICE=1;

            Log.i("Ski_c BcS  Constants.SERVICE is ", String.valueOf(Constants.SERVICE));*/

           startService(new Intent(BackgroundService.class.getName()));
           Log.i("Ski_c MA ", "Service try to create");
        }

        uiHelper = new UiLifecycleHelper(MainActivity.this, callback);
        uiHelper.onCreate(savedInstanceState);

        if(isNetworkStatusAvialable (getApplicationContext())) {
           // Toast.makeText(getApplicationContext(), "internet avialable", Toast.LENGTH_SHORT).show();
        } else {

            Log.i("Ski_c MA ", "Internet is not avialable");
            Toast.makeText(getApplicationContext(), "Internet is not avialable", Toast.LENGTH_LONG).show();

        }




        loginBtn = (LoginButton) findViewById(R.id.btn_fb);
        loginBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                if (user != null) {
                    /*userName.setText("Hello, " + user.getName());*/
                    Log.i("Ski_c MA fb userName is ", user.getName());
                    Toast.makeText(getApplicationContext(), "Welcome, "+user.getName(), Toast.LENGTH_LONG).show();
                    userName = user.getName();
                    Log.i("Ski_c MA fb isLoggedIn() is ", String.valueOf(isLoggedIn()));
                    Constants.FB=1;



                } else {
                    /*userName.setText("You are not logged");*/
                    Log.i("Ski_c MA fb", " You are not logged");
                    Log.i("Ski_c MA fb isLoggedIn() is ", String.valueOf(isLoggedIn()));
                }
            }
        });
    }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Log.i("Ski_c MA", " moveTaskToBack(true);");
                    moveTaskToBack(true);
                    return true;
                }
                return super.onKeyDown(keyCode, event);
        }

    public static boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return (session != null && session.isOpened());
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i("Ski_c MA", " Fb Logged in...");
        } else if (state.isClosed()) {
            Log.i("Ski_c MA", " Fb Logged out...");
        }
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };


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


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);

        uiHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);

        uiHelper.onPause();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {

            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Ski_c MA Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Ski_c MA Key Hash=", key);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Ski_c MA Name not found", e1.toString());
        }

        catch (NoSuchAlgorithmException e) {
            Log.e("Ski_c MA No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Ski_c MA Exception", e.toString());
        }

        return key;
    }

    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
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