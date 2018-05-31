package com.example.ksenia.myproject1_proect;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//активность для отправки письма на почту
public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        setTitle("Отправить письмо");
        Button bl=findViewById(R.id.ButtonLetter);
        final EditText ep,ec,ek;
        ep=(EditText)findViewById(R.id.TextPAscal);
        ec=(EditText)findViewById(R.id.TextCpp);
        ek=(EditText)findViewById(R.id.TextComent);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(ep.getText().length()==0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Вы забыли заполнить первое поле", Toast.LENGTH_LONG);
                    toast.show();
                   return;
                }
                String pasc=ep.getText().toString();
                String cppc=ec.getText().toString();
                String comentc=ek.getText().toString();

                Intent letter=new Intent(Intent.ACTION_SEND);
                letter.setData(Uri.parse("mailto:"));
                String[] to={"kseniapetrenko6@gmail.com"};
                letter.putExtra(Intent.EXTRA_EMAIL,to);
                letter.putExtra(Intent.EXTRA_SUBJECT,"for Easy");
                letter.putExtra(Intent.EXTRA_TEXT,"In Pascal:\n"+pasc+"\nIn C++:\n"+cppc+"\n"+comentc);
                letter.setType("message/rfc822");
                Intent chooser=Intent.createChooser(letter,"Send Email");
                startActivity(chooser);

            }
        };
        bl.setOnClickListener(listener);
    }
}
