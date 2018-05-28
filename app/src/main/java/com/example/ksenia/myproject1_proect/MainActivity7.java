package com.example.ksenia.myproject1_proect;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
// активность с небольшими пояснениями про приложение
public class MainActivity7 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        setTitle(getString(R.string.title_of_pr));
        TextView text7 = findViewById(R.id.texthelp);
        text7.setMovementMethod(new ScrollingMovementMethod());
        text7.setText(getString(R.string.a11)+"\n"+getString(R.string.a12)+"\n"+getString(R.string.a13)+"\n"+getString(R.string.a14)+"\n"+getString(R.string.a15)+"\n"+getString(R.string.a16)+"\n"+getString(R.string.a17));

    }
}
