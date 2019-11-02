package com.rajkumarrajan.roomroomdb.Util;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Support {
    private Context context;

    public Support(Context context) {
        this.context = context;
    }

    public String EditTextToString(EditText editText) {
        return editText.getText().toString();
    }



    public void DisplayToast(String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
    }
}
