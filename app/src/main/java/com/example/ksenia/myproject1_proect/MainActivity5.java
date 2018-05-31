package com.example.ksenia.myproject1_proect;

import android.app.Activity;
import android.os.Bundle;

import android.util.DisplayMetrics;

import android.widget.ListView;
import android.widget.TextView;



// справочник
public class MainActivity5 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        setTitle(getString(R.string.title_of_pr));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        TextView bt1=(TextView) findViewById(R.id.titlep);
        bt1.setTextSize(height/40);
        bt1=(TextView) findViewById(R.id.titlec);
        bt1.setTextSize(height/40);
        MyMonthAdapter adapter = new MyMonthAdapter(this, makeMas(),height/46);
        ListView lv =  findViewById(R.id.List);

        lv.setAdapter(adapter);
    }
    String name_pas[]= new String[]{"begin end","var a:integer","var a:string","var a:boolean","var a:shortint(char)"
    ,"var a:byte","var a:smallint","var a:word","var a:cardinal(longword)","var a:comp(double)",
    "var a:extended","var a:single(real)","var a:longint","var a:int64","var a:qword",
    "var a: array [1..11] of integer","var a: array [1..101] of ... array [1..101] of integer","begin",
    "Assign(input, 'input.txt'); \nreset(input);","Assign(output, 'output.txt');\nrewrite(output);"
    ,"read(a)","readln(a)","write(a)","writeln(a)","if true then ... else...","while true do...",
    "a = b","a and b","a or b","not","a <> b","for i := 1 to n do","\"for i := n downto 1 do\"","exit","break",
    "continue","a := 1","a += 1","a -= 1","a *= 1","a /= 1","a mod b","a div b","a / b"};


    String name_cpp[]= new String[]{"{ }","int a","string a","bool a","char a","unsigned char a","short int a",
    "unsigned int a","unsigned long int a","double a","long double a","float a","long int a","long long int a",
            "unsigned long long a","int a[10]","int a  [100]...[100]","int main() { ","freopen( \"input.txt\", \"r\", stdin)"
    ,"freopen( \"output.txt\", \"w\", stdout)","cin >> a","cin >> a >> \"\\n\"","cout << a","cout << a << \"\\n\"",
    "if( true ){...} else {...}","while (true) {...}","a == b","a && b","a || b","!","a != b",
    "for(int i=1; i<=n; i++)","for(int i=n; i>=1; i--)","return 0","break","continue","a = 1","a += 1","a -= 1","a *= 1","a /= 1","a % b"
    ,"a / b","a / (float) b"};
    My[] makeMas(){
        My[]res=new My[name_pas.length];
        for(int i=0;i < name_cpp.length;i++){
            res[i]=new My(name_pas[i],name_cpp[i]);
        }
        return res;
    }

}

