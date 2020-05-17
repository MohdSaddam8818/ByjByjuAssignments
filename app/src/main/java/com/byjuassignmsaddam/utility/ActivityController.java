package com.byjuassignmsaddam.utility;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;




public class ActivityController {

    public static void startActivity(Activity activity, Class clazz, Intent bundle, boolean isClearPrevious) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtras(bundle);
        if (isClearPrevious) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        }
        activity.startActivity(intent);
        activity.finishAffinity();
    }
    public static void startActivity(Activity activity, Class clazz, boolean isClearPrevious) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent);
        if (isClearPrevious)
            activity.finish();
    }

}
