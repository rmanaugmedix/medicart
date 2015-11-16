package com.appslasherstudio.medicart;

import com.firebase.client.Firebase;

/**
 * Created by rmaninfinity on 11/16/15.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
