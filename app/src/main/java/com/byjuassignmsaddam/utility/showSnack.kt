package com.byjuassignmsaddam.utility

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView


import com.byjuassignmsaddam.R
import com.google.android.material.snackbar.Snackbar

class showSnack {
    fun isConnected(isConnected: Boolean, context: Activity) {
        val message: String
        val color: Int
        if (isConnected) {
            message = "Good! Connected to Internet"
            color = Color.WHITE
        } else {
            message = "Sorry! Not connected to internet"
            color = Color.RED
        }
        val snackbar =
            Snackbar.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        /*Snackbar snackbar = Snackbar.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });*/

        val sbView = snackbar.view
        val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
        textView.setTextColor(color)

        snackbar.show()
    }


    fun showSnackTop(
        isConnected: Boolean,
        rlIdAll: RelativeLayout,
        messages: String,
        context: Activity
    ) {
        val color: Int
        if (isConnected) {
            color = Color.WHITE
        } else {
            color = Color.RED
        }

        val snackbar = Snackbar.make(
            context.findViewById(android.R.id.content),
            messages,
            Snackbar.LENGTH_LONG
        )


        val sbView = snackbar.view
        val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
        textView.setTextColor(color)
        /*  FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) sbView.getLayoutParams();
        params.gravity = Gravity.TOP;
        sbView.setLayoutParams(params);*/
        snackbar.show()

    }

    fun isMoreLoad(isConnected: Boolean, messages: String, context: Activity) {
        val message: String
        val color: Int
        if (isConnected) {
            message = "Good! Connected to Internet"
            color = Color.WHITE
        } else {
            message = messages
            color = Color.RED
        }
        val snackbar =
            Snackbar.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
        textView.setTextColor(color)

        snackbar.show()
    }

}
