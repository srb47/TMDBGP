package com.telenorgp.tmdbgp.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.telenorgp.tmdbgp.Activities.MainActivity.dialog;


public class NetworkChangeReceiver extends BroadcastReceiver {
    private NetworkChangeReceiver mInternetConnectionBroadcastReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (isOnline(context)) {
                dialog(true);
//                Log.e("Internet Connection:", "Online Connect Intenet ");
            } else {
                dialog(false);
//                Log.e("Internet Connection", "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
