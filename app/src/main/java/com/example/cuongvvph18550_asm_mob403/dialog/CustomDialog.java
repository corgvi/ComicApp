package com.example.cuongvvph18550_asm_mob403.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.cuongvvph18550_asm_mob403.R;

public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public String textDialog;
    public Dialog d;
    public Button yes, no;
    private TextView txtDialog;

    public CustomDialog(Activity a, String s) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.textDialog = s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        yes.setOnClickListener(this);
        txtDialog = findViewById(R.id.txt_dia);
        txtDialog.setText(textDialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                c.finish();
                break;
            default:
                break;
        }
        dismiss();
    }
}
