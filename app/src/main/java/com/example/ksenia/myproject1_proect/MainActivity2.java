package com.example.ksenia.myproject1_proect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import com.example.ksenia.myproject1_proect.MyEditText;


//активность для ввода кода программы и передачи его в следующую активность
public class MainActivity2 extends AppCompatActivity {

    MyEditText edit;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Pascal -> C++");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        Button bt=(Button) findViewById(R.id.Button1);
        Button men= (Button)findViewById(R.id.Button0);
        edit=(MyEditText) findViewById(R.id.editText1);
        edit.setMovementMethod(new ScrollingMovementMethod());
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.Button1: {
                        text =edit.getText().toString();
                        Intent i=new Intent(MainActivity2.this, MainActivity3.class);
                        i.putExtra("code",text);
                        startActivityForResult(i,0);
                        break;
                    }
                    case R.id.Button0: {
                        startActivity(new Intent(MainActivity2.this, MainActivity4.class));
                        break;
                    }
                }
            }
        };
        bt.setOnClickListener(listener);
        men.setOnClickListener(listener);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                edit.setText(data.getStringExtra("code"));
                break;
        }
    }
}
