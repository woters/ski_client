package com.ski.ski_4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.facebook.*;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Alyosha on 07.11.2014.
 */
public class FbShareFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private Button shareButton;
    private Button backButton;

    private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");

    private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";

    private boolean pendingPublishReauthorization = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fb_share, container, false);

        /*LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
        authButton.setFragment(this);*/

        shareButton = (Button) view.findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*publishStory();*/
                /*publishStoryD();*/

                if(MainActivity.isNetworkStatusAvialable (getActivity())) {
                    // Toast.makeText(getApplicationContext(), "internet avialable", Toast.LENGTH_SHORT).show();

                    if (FacebookDialog.canPresentShareDialog(getActivity()
                                    .getApplicationContext(),
                            FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
                        // Publish the post using the Share Dialog


                        FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(getActivity())
                                .setLink("http://skipass.net.ua/")
                                .setPicture("http://media-cache-ak0.pinimg.com/originals/44/c7/6f/44c76f7d1852404fb2132c6f32fa390f.jpg")
                                .setCaption("available on Android")
                                .setName("Bukovel ski-pass")
                                .setDescription("The service assists you to sell or buy an unfinished Bukovel ski-pass")

                                .build();
                        Log.i("Ski_c FSF", "FacebookDialog shareDialog built");
                        uiHelper.trackPendingDialogCall(shareDialog.present());

                        Log.i("Ski_c FSF 79", " getActivity().finish()");

                } else {

                    Log.i("Ski_c MA ", "Internet is not avialable");
                    Toast.makeText(getActivity(), "Internet is not avialable", Toast.LENGTH_LONG).show();

                }



                    getActivity().finish();



                } else {
                    // Fallback. For example, publish the post using the Feed Dialog
                    publishStoryDD();
                    Log.i("Ski_c FSF", "Fallback!");
                }


            }
        });

        backButton = (Button) view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        AdView adView = (AdView)view.findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        if (savedInstanceState != null) {
            pendingPublishReauthorization =
                    savedInstanceState.getBoolean(PENDING_PUBLISH_KEY, false);
        }

        return view;
    }

    /*private void goToMain() {
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        // For scenarios where the main activity is launched and user
        // session is not null, the session state change notification
        // may not be triggered. Trigger it if it's open/closed.
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*uiHelper.onActivityResult(requestCode, resultCode, data);*/
        Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);
        Log.i("Ski_c FSF ", "data coming to onactivityresult: "+data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.i("Activity", "Success!");
            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PENDING_PUBLISH_KEY, pendingPublishReauthorization);
        uiHelper.onSaveInstanceState(outState);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            shareButton.setVisibility(View.VISIBLE);
            if (pendingPublishReauthorization &&
                    state.equals(SessionState.OPENED_TOKEN_UPDATED)) {
                pendingPublishReauthorization = false;
                /*publishStory();*/
                publishStoryD();
            }
        } else if (state.isClosed()) {
            shareButton.setVisibility(View.INVISIBLE);
        }
    }

    private boolean hasPublishPermission() {
        Session session = Session.getActiveSession();
        return session != null && session.getPermissions().contains("publish_actions");
    }


    private void publishStoryDD() {
        Bundle params = new Bundle();
        params.putString("name", "I've just used Bukovel ski-pass resale service");
        params.putString("caption", "available on Android");
        params.putString("description", "The service assists you to sell or buy an unfinished Bukovel ski-pass");
        params.putString("link", "http://skipass.net.ua/");
        params.putString("picture", "http://media-cache-ak0.pinimg.com/originals/44/c7/6f/44c76f7d1852404fb2132c6f32fa390f.jpg");

        WebDialog feedDialog = (
                new WebDialog.FeedDialogBuilder(getActivity(),
                        Session.getActiveSession(),
                        params)).build();
        feedDialog.show();

        Log.i("Ski_c FSF 216", " getActivity().finish()");

        getActivity().finish();
    }


    private void publishStoryD(){

        FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(getActivity())
                /*.setLink("https://developers.facebook.com/android")*/
                .build();
        uiHelper.trackPendingDialogCall(shareDialog.present());

        Bundle params = new Bundle();
        params.putString("name", "I've just used Bukovel ski-pass resale service");
        params.putString("caption", "available on Android");
        params.putString("description", "The service assists you to sell or buy an unfinished Bukovel ski-pass");
        params.putString("link", "http://skipass.net.ua/");
        params.putString("picture", "http://media-cache-ak0.pinimg.com/originals/44/c7/6f/44c76f7d1852404fb2132c6f32fa390f.jpg");

        WebDialog feedDialog = (
                new WebDialog.FeedDialogBuilder(getActivity(),
                        Session.getActiveSession(),
                        params))
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error == null) {
                            // When the story is posted, echo the success
                            // and the post Id.
                            final String postId = values.getString("post_id");
                            if (postId != null) {
                                Toast.makeText(getActivity(),
                                        "Posted story, id: "+postId,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // User clicked the Cancel button
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Publish cancelled",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else if (error instanceof FacebookOperationCanceledException) {
                            // User clicked the "x" button
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Publish cancelled",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Generic, ex: network error
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Error posting story",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                })
                .build();
        feedDialog.show();


    }

    private void publishStory() {

        Log.i("Ski_c FSF", " publishStory()");
        Session session = Session.getActiveSession();
        if (session != null) {

            List<String> permissions = session.getPermissions();
            Log.i("Ski_c FSF permissions before set are ", String.valueOf(permissions));

            /*// create a new permission
            Session.NewPermissionsRequest newPermissionsRequest = new Session
                    .NewPermissionsRequest(this, Arrays.asList("publish_actions"));
            // set a new publish permission request
            session.requestNewPublishPermissions(newPermissionsRequest);*/



            // Check for publish permissions

            if (!isSubsetOf(PERMISSIONS, permissions)) {
                pendingPublishReauthorization = true;
                Session.NewPermissionsRequest newPermissionsRequest = new Session
                        .NewPermissionsRequest(this, PERMISSIONS);
                session.requestNewPublishPermissions(newPermissionsRequest);
                return;
            }

            Bundle postParams = new Bundle();
            /*postParams.putString("name", "Facebook SDK for Android");
            postParams.putString("caption", "Build great social apps and get more installs.");
            postParams.putString("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
            postParams.putString("link", "https://developers.facebook.com/android");
            postParams.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");*/

            /*postParams.putString("name", "Bukovel ski-pass");*/
            postParams.putString("name", "I've just used Bukovel ski-pass resale service");
            postParams.putString("caption", "available on Android");
            postParams.putString("description", "The service assists you to sell or buy an unfinished Bukovel ski-pass");
            postParams.putString("link", "http://skipass.net.ua/");
            postParams.putString("picture", "http://media-cache-ak0.pinimg.com/originals/44/c7/6f/44c76f7d1852404fb2132c6f32fa390f.jpg");
            /*postParams.putString("message", "I've just used #Bukovel #Буковель ski-pass resale service");*/

            //@169716793065535

            /*postParams.putString("message", "this is a test tag of @[278905338821044] for a test");*/

            Request request = new Request(Session.getActiveSession(), "me/feed", postParams, HttpMethod.POST);
            request.setCallback(new Request.Callback() {
                @Override
                public void onCompleted(Response response) {

                    Log.i("Ski_c FSF", " onCompleted()");
                    if (response.getError() == null) {
                        // Tell the user success!

                        Log.i("Ski_c FSF", " success!");

                        /*Toast.makeText(getActivity()
                                        .getApplicationContext(),
                                "The post has appeared on Your timeline",
                                Toast.LENGTH_LONG).show();*/
                    }
                    else Log.i("Ski_c FSF", " error is "+response.getError());
                }
            });

            /*RequestAsyncTask task = new RequestAsyncTask(request);
            Log.i("Ski_c FSF", " task.execute()");
            task.execute();*/

            Log.i("Ski_c FSF", " request.executeAsync()");
            request.executeAsync();

            Toast.makeText(getActivity()
                            .getApplicationContext(),
                    "Shared on Facebook, the post will appear soon",
                    Toast.LENGTH_LONG).show();


            Log.i("Ski_c FSF 358", " getActivity().finish()");

            getActivity().finish();




        }
    }

    private boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
        for (String string : subset) {
            if (!superset.contains(string)) {
                return false;
            }
        }
        return true;
    }

}