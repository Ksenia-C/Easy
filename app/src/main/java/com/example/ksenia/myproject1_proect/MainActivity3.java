package com.example.ksenia.myproject1_proect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
//если программа не знает команду, пользователь увидит на какой строке в его коде она находится
class Error_on_line{
    String name_of_er;
    Integer line;
    public Error_on_line(String name_of_er,Integer line){
        this.line=line;
        this.name_of_er=name_of_er;
    }

}
//для работы с ошибками
class Errors{
    private ArrayList<Error_on_line> erroysList=new ArrayList<>();
    void set_er(String name,int number){
        erroysList.add(new Error_on_line(name,number));
    }
    Error_on_line get_er(int number){
        return erroysList.get(number);
    }
    int get_len(){return erroysList.size();}
}

class OpenFile{
    //fun-функция, flag-имя переменной, ассоциированной с файлом, name - имя настоящего файла
    String flag,name,fun;
    public OpenFile(String flag, String name, String fun){
        this.flag=flag;
        this.name=name;
        this.fun=fun;
    }

}
//класс, куда записывается переведенная программа, и работающиая с файлами
class Program_to_out{
    String open_file_bodu;
    Integer line_number;
    public ArrayList<String> start_of_program, body;
    public ArrayList<OpenFile> openings;
    boolean securet=false,for_main=true;
    public Program_to_out(){
        this.line_number=1;
        start_of_program=new ArrayList<String>();
        body=new ArrayList<String>();
        openings=new ArrayList<OpenFile>();
    }
    public void write_start(){
        for(String i:start_of_program){
            System.out.println(i);
        }
    }
    public void write_body(){
        for(String i:body){
            System.out.println(i);
        }
    }
    public void write_to_open(String flag, String name){
        openings.add(new OpenFile(flag,name,"0"));
    }
    public void write_to_open(String name,String what_do, int tnum){
        for(int i=0;i<openings.size();i++){
            if(openings.get(i).flag.equals(name)){
                openings.get(i).fun=what_do;
                open_file_bodu="freopen(\""+openings.get(i).name+"\"," + (what_do.equals("r")?" \"r\" , stdin);":" \"w\" , stdout);" );
                return;
            }
        }
    }
}
//класс для переменной
class ForVar{
    String name,type;
    public ForVar(String name, String type){
        this.name=name;
        this.type=type;
    }

}
//позволяет работать с многомерными массивами, хранит интервал значений для одного измерения
class Inteval{
    Integer begin_ind,end_index;
    public Inteval(Integer b, Integer e){
        this.begin_ind=b;
        this.end_index=e;
    }

}
// класс для многомерных массивов
class ForMas{
    String name,type;
    ArrayList<Inteval> dimen=new ArrayList<Inteval>();
    public ForMas(String name, String type,ArrayList<Inteval> a){
        this.name=name;
        this.type=type;
        this.dimen=a;
    }


}
//класс для работы с переменными
class Various{
    public ArrayList<ForVar>vars;
    public ArrayList<ForMas>massives;
    String Value_in,After_in,Befor_in;
    public Various(){
        vars=new ArrayList<ForVar>();
        massives=new ArrayList<ForMas>();
    }
    public boolean check_in_vars(String name){//проверяет наличие переменной
        String find=name;
        for(ForVar i:vars){
            if(find.equals(i.name))return true;
        }
        return false;
    }
    public boolean check_in_mas(String name){//проверяет наличие массива
        String find="",fir="";
        int step=0;
        ArrayList<String>value_in_=new ArrayList<>();
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)=='['){
                if(name.charAt(i-1)!=']')find=fir;
                fir="";
            }
            if(name.charAt(i)==']'){
                value_in_.add(fir);
                step++;
                fir="";
                continue;
            }
            fir+=name.charAt(i);
        }
        if(find.equals("")){find=fir;fir="";}

        for(ForMas i:massives){
            if(find.equals(i.name)){
                this.After_in=fir;
                this.Value_in="";
                for (int j = 0; j < step; j++) {
                    //вычитает константу, так как в С++ индексация массива начинается с нуля
                    this.Value_in+=value_in_.get(j)+"- ("+i.dimen.get(j).begin_ind.toString()+")]";
                }
                this.Befor_in=find;
                return true;
            }
        }
        return false;
    }
    public void write_to_mas(String name, String type,ArrayList<Inteval>dimen){
        ForMas a=new ForMas(name,type,dimen);
        massives.add(a);
    }
    public void write_to_val(String name, String type){
        ForVar a=new ForVar(name,type);
        vars.add(a);
    }
}



//активность преобразовывает пользовательскую программу
public class MainActivity3 extends AppCompatActivity {
    TextView text_in_layout;
    String code,code_to_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle(getString(R.string.title_of_pr));
        code= getIntent().getStringExtra("code");
        code_to_back=code;
        text_in_layout= (TextView) findViewById(R.id.TextView2);
        text_in_layout.setMovementMethod(new ScrollingMovementMethod());
        Button bt=(Button)findViewById(R.id.Button2);
        Thread thread=new Thread(new AnotherRunnable());
        thread.start();
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent();
                i.putExtra("code",code_to_back);
                setResult(RESULT_OK, i);
                finish();

            }
        };
        bt.setOnClickListener(listener);
    }
    class AnotherRunnable implements Runnable {
        @Override
        public void run() {

            Integer number_of_string=1;
            //преобразование пользовательского кода в боллее удобный для работы вид
            {
                StringBuilder text_in = new StringBuilder(code);
                int i = 0;
                boolean i_dont_want = false, covopen = false;
                while (i < text_in.length()) {
                    char once = text_in.charAt(i);

                    try {

                        //данный символ встречается при создании массива и при работе с ним
                        //в первом случае надо ставить пробелы  между точками и скобками
                        // во втором - не разделять название массива со  скобкой
                        if (once == '[') {
                            int o = i - 1;
                            boolean key = false;
                            while (text_in.charAt(o) == ' ') {
                                if (o == -1) {
                                    key = true;
                                    break;
                                }
                                o--;
                            }
                            if (!key) {
                                if (o > 3 && text_in.charAt(o - 4) == 'a' && text_in.charAt(o - 3) == 'r' && text_in.charAt(o - 2) == 'r' && text_in.charAt(o - 1) == 'a') {
                                    i_dont_want = true;
                                    int o1 = 1;
                                    if (i != 0 && text_in.charAt(i - 1) != ' ') {
                                        text_in.insert(i, ' ');
                                        o1++;
                                    }
                                    if (i != text_in.length() - 1 && text_in.charAt(i + 1) != ' ') {
                                        text_in.insert(i + o1, ' ');
                                        i += o1;
                                    }
                                } else covopen = true;
                            }

                        }
                        // в случае создания массива между точками и скобками ставится пробел
                        if (i_dont_want && (once == ']' || once == '.')) {
                            int o1 = 1;
                            if (i != 0 && text_in.charAt(i - 1) != ' ') {
                                text_in.insert(i, ' ');
                                o1++;
                            }
                            if (i != text_in.length() - 1 && text_in.charAt(i + 1) != ' ') {
                                text_in.insert(i + o1, ' ');
                                i += o1;
                            }
                        }
                        //закрытие кавычки
                        if (once == ']' || ((once == '\'') && covopen)) {
                            if ((once == '\'') && i < text_in.length() - 3 && text_in.charAt(i - 2) != '\'') {
                                text_in.setCharAt(i, '\"');
                            }
                            covopen = false;
                            i_dont_want = false;

                        } else if (once == '\'') {//все, что стоит после этого символа и до закрывающей кавычки
                            //нужно оставить без изменений
                            if (i > 1 && text_in.charAt(i + 2) != '\'') {
                                text_in.setCharAt(i, '\"');
                            }
                            covopen = true;

                        }
                        //пробел заменяется на другой символ
                        if (covopen) {
                            if (once == ' ') {
                                text_in.setCharAt(i, (char) 147483440);
                            }
                            i++;
                            continue;
                        }
                        //все буквы должны быть прописными
                        if (once >= 65 && once <= 90) {
                            once = (char) ((char) once + 32);
                            text_in.setCharAt(i, once);
                        }
                        // перенос строки убирается
                        if (once == '\n') {
                            int yard = 0;
                            if (i != 0 && text_in.charAt(i - 1) != ' ') {
                                text_in.insert(i, ' ');
                                yard++;
                            }
                            text_in.deleteCharAt(i + yard);
                            text_in.insert(i + yard, "2<11(0@2$");
                            //пометка, что здесь был перенос строки, необходимо для сообщения, на какой строке произошла ошибка
                            i += 9 + yard;
                            number_of_string++;
                            yard = 0;
                            if (i != text_in.length() && text_in.charAt(i) != ' ') {
                                text_in.insert(i, ' ');
                            }
                            //i+=yard;


                        }
                        //перед и после специальными сочетаниями символов поставить пробел
                        if (i != text_in.length() - 1) {
                            if (((once == ':' || once == '+' || once == '/' || once == '-' || once == '*' || once == '>' || once == '<') && text_in.charAt(i + 1) == '=')
                                    || (once == '<' && text_in.charAt(i + 1) == '>')) {
                                int o = 2;
                                if (i != 0 && text_in.charAt(i - 1) != ' ') {
                                    text_in.insert(i, ' ');
                                    o++;
                                }
                                if (i != text_in.length() - 1 && text_in.charAt(i + 2) != ' ') {
                                    text_in.insert(i + o, ' ');
                                }
                                i += o;

                            }

                        }
                        //перед и после специальными символов поставить пробел
                        if (once == ';' || once == '(' || once == ')' || once == ',' || once == ':' || (once == '-' && !Character.isDigit(text_in.charAt(i + 1))) || once == '+' || once == '/' || once == '*'
                                || once == '=' || once == '>' || once == '<') {
                            int o = 1;
                            if (i != 0 && text_in.charAt(i - 1) != ' ') {
                                text_in.insert(i, ' ');
                                o++;
                            }
                            if (i != text_in.length() - 1 && text_in.charAt(i + 1) != ' ') {
                                text_in.insert(i + o, ' ');
                                i += o;
                            }
                        }
                        //лишние пробелы удаляются
                        if (once == ' ') {
                            if (text_in.charAt(i - 1) == ' ') {
                                text_in.deleteCharAt(i);
                                i--;
                            }
                        }
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Во время форматирования вашей программы возникла ошибка. Проверьте строку " + number_of_string, Toast.LENGTH_LONG);
                        toast.show();
                    }
                    i++;
                }


                code = text_in.toString();
            }



            final ArrayList<String> text=new ArrayList<>();
            for(String i:code.split(" ")){
                text.add(i);
            }
            final Program_to_out main_part=new Program_to_out();
            Various vari=new Various();
            Errors er=new Errors();
            main_part.start_of_program.add("#include<iostream>");
            main_part.start_of_program.add("using namespace std;");
            Iterator<String> pole=new Iterator<String>() {// строка, полученная из предыдущей части, разделяется по пробелам
                int cxtn=0;
                @Override
                public boolean hasNext() {
                    return cxtn < text.size();
                }

                @Override
                public String next() {
                    String a= text.get(cxtn);
                    cxtn++;
                    while(a.equals("2<11(0@2$")){
                        main_part.line_number++;//поддерживаем номер строки в изначальном коде
                        a= text.get(cxtn);
                        cxtn++;
                    }

                    for(int k =0;k<a.length();k++){
                        if(a.charAt(k)==(char)147483440) {
                            StringBuilder a1 = new StringBuilder(a);
                            a1.setCharAt(k,' ');//восстанавливаем пробел в пользовательских строках
                            a=a1.toString();
                        }

                    }
                    String a1=a;
                    a="";
                    for(String  k: a1.split(" ")) {
                        //изменяем следующие операции
                        if (k.equals("mod")) k= "%";
                        if (k.equals("div")) k= "/";
                        if (k.equals("/")) k= "/ (float)";
                        a+=k+" ";
                    }
                    return a.substring(0,a.length()-1);

                }
            };
            String i=pole.next();
            while(true){
                try {
                    if (i.equals("program")) {
                        i = pole.next();
                        i = pole.next();

                    } else if (i.equals("var")) {
                        //переменные и массивы
                        i = pole.next();
                        while (!i.equals("begin")) {
                            ArrayList<String> for_time = new ArrayList<>();
                            while (!i.equals(":")) {
                                if (!i.equals(",")) for_time.add(i);
                                i = pole.next();
                            }
                            i = pole.next();
                            if (i.equals("array")) {
                                ArrayList<Inteval> dimen = new ArrayList<>();
                                multidimensional_arrays:
                                while (true) {
                                    i = pole.next();
                                    i = pole.next();
                                    int begg = Integer.parseInt(i);
                                    i = pole.next();
                                    i = pole.next();
                                    i = pole.next();
                                    int endg = Integer.parseInt(i);
                                    i = pole.next();
                                    i = pole.next();
                                    i = pole.next();
                                    dimen.add(new Inteval(begg, endg));
                                    //изменение типа данных
                                    switch (i) {
                                        case "integer": {
                                            i = "int";
                                            break multidimensional_arrays;
                                        }
                                        case "string": {
                                            main_part.start_of_program.add("#include<string>");
                                            break multidimensional_arrays;
                                        }
                                        case "boolean": {
                                            i = "bool";
                                            break multidimensional_arrays;
                                        }
                                        case "shortint": {
                                            i = "char";
                                            break multidimensional_arrays;
                                        }
                                        case "byte": {
                                            i = "unsigned char";
                                            break multidimensional_arrays;
                                        }
                                        case "smallint": {
                                            i = "short int";
                                            break multidimensional_arrays;
                                        }
                                        case "word": {
                                            i = "unsigned int";
                                            break multidimensional_arrays;
                                        }
                                        case "cardinal": {
                                        }
                                        case "longword": {
                                            i = "unsigned long int";
                                            break multidimensional_arrays;
                                        }
                                        case "comp":
                                        case "double": {
                                            i = "double";
                                            break multidimensional_arrays;
                                        }
                                        case "extended": {
                                            i = "long double";
                                            break multidimensional_arrays;
                                        }
                                        case "single":
                                        case "real": {
                                            i = "float";
                                            break multidimensional_arrays;
                                        }

                                        case "longint": {
                                            i = "long int";
                                            break multidimensional_arrays;
                                        }
                                        case "char": {
                                            break multidimensional_arrays;
                                        }
                                        case "int64": {
                                            i = "long long int";
                                            break multidimensional_arrays;
                                        }
                                        case "qword": {
                                            i = "unsigned long long";
                                            break multidimensional_arrays;
                                        }
                                        case "array": {

                                            break;
                                        }
                                        default: {
                                            er.set_er(i, main_part.line_number);
                                            break multidimensional_arrays;
                                        }
                                    }

                                }
                                for (String j : for_time) {
                                    vari.write_to_mas(j, i, dimen);

                                }
                                i = pole.next();
                                i = pole.next();

                            } else {
                                //изменение типа данных
                                switch (i) {
                                    case "integer": {
                                        i = "int";
                                        break;
                                    }
                                    case "string": {
                                        main_part.start_of_program.add("#include<string>");
                                        break;
                                    }
                                    case "boolean": {
                                        i = "bool";
                                        break;
                                    }
                                    case "shortint": {
                                        i = "char";
                                        break;
                                    }
                                    case "byte": {
                                        i = "unsigned char";
                                        break;
                                    }
                                    case "smallint": {
                                        i = "short int";
                                        break;
                                    }
                                    case "word": {
                                        i = "unsigned int";
                                        break;
                                    }
                                    case "cardinal": {
                                    }
                                    case "longword": {
                                        i = "unsigned long int";
                                        break;
                                    }
                                    case "comp":
                                    case "double": {
                                        i = "double";
                                        break;
                                    }
                                    case "extended": {
                                        i = "long double";
                                        break;
                                    }
                                    case "single":
                                    case "real": {
                                        i = "float";
                                        break;
                                    }

                                    case "longint": {
                                        i = "long int";
                                        break;
                                    }
                                    case "char": {
                                        break;
                                    }
                                    case "int64": {
                                        i = "long long int";
                                        break;
                                    }
                                    case "qword": {
                                        i = "unsigned long long";
                                        break;
                                    }
                                    default: {
                                        er.set_er(i, main_part.line_number);
                                        break;
                                    }
                                }
                                for (String j : for_time) {
                                    vari.write_to_val(j, i);
                                    if(i.equals("string")){
                                        ArrayList<Inteval>dimen=new ArrayList<Inteval>();
                                        dimen.add(new Inteval(1,-1));
                                        vari.write_to_mas(j, i, dimen);
                                    }
                                }
                                i = pole.next();
                                i = pole.next();
                            }
                        }
                        continue;
                    } else if (i.equals("begin") && main_part.for_main) {
                        main_part.for_main = false;
                        main_part.body.add("int main() {");
                    } else if (i.equals("assign")) {
                        //открытие файла
                        i = pole.next();
                        i = pole.next();
                        String glag1 = i;
                        i = pole.next();
                        i = pole.next();
                        String name1 = i;
                        i = pole.next();
                        i = pole.next();
                        name1 = name1.substring(1, name1.length() - 1);
                        main_part.write_to_open(glag1, name1);
                    } else if (i.equals("reset") || i.equals("rewrite")) {
                        //определение действий, связанных с файлом
                        main_part.securet = true;
                        String j = i.equals("rewrite") ? "w" : "r";
                        i = pole.next();
                        i = pole.next();
                        main_part.write_to_open(i, j, 0);
                        main_part.body.add(main_part.open_file_bodu);
                        i = pole.next();
                        i = pole.next();
                    } else if (i.equals("read") || i.equals("write") || i.equals("writeln") || i.equals("readln")) {
                        //чтение из консоли и запись в консоль
                        String for_time = "";
                        boolean bo = false, ent = false;
                        if (i.equals("readln") || i.equals("writeln")) ent = true;
                        if (i.equals("read") || i.equals("readln")) {
                            for_time += "cin ";
                        } else {
                            for_time += "cout ";
                            bo = true;
                        }
                        i = pole.next();
                        i = pole.next();
                        String text_to_out="";
                        while (!i.equals(";") && !i.equals("else")) {
                            if (i.equals(")")) {
                                i = pole.next();
                                break;
                            }
                            if (vari.check_in_mas(i)) {
                                //здесь в везде далее
                                //смещение индексации
                                i = vari.Befor_in + vari.Value_in + vari.After_in;
                            }
                            do{
                                text_to_out+=i+" ";
                                i=pole.next();
                            }while(!i.equals(",")&& !i.equals(")"));
                            for_time += (bo ? "<< " : ">> ") + text_to_out;
                            text_to_out="";
                            //i = pole.next();
                            i = pole.next();
                        }
                        for_time += (ent ? (bo ? "<< " : ">> ")+"\"\\n\"" : "") + " ;";
                        main_part.body.add(for_time);
                        if (i.equals("else")) {
                            continue;
                        }
                    } else if (i.equals("if") || i.equals("while")) {
                        //цикл while и уловный оператор
                        boolean if_if = false;
                        if (i.equals("if")) if_if = true;
                        String part = "";
                        i = pole.next();
                        boolean if_cs_is = true, sc_need = false;
                        int count = 0, cur_count = 0;
                        while (if_if ? !i.equals("then") : !i.equals("do")) {
                            if (i.equals('(') && count == 0) {
                                if_cs_is = false;
                            }
                            if (i.equals(")")) {
                                cur_count = count;
                            }
                            if (vari.check_in_mas(i)) {
                                i = vari.Befor_in + vari.Value_in + vari.After_in;
                            } else if (i.equals("=")) i = "==";
                            else if (i.equals("and")) i = "&&";
                            else if (i.equals("or")) i = "||";
                            else if (i.equals("not")) i = "!";
                            else if (i.equals("<>")) i = "!=";
                            part += i + " ";
                            i = pole.next();
                            count++;
                        }
                        if (!if_cs_is && cur_count == count - 1) {
                            sc_need = false;
                        } else sc_need = true;
                        main_part.body.add((if_if ? "if " : "while ") + (sc_need ? "( " : "") + part + (sc_need ? ") " : ""));
                    } else if (i.equals("for")) {
                        //цикл for
                        String iter, meanings_to_, end_comp, direct = "";
                        iter = pole.next();
                        i = pole.next();
                        meanings_to_="";
                        i = pole.next();
                        while(!i.equals("to") && !i.equals("downto")){
                            meanings_to_+=i+" ";
                            i = pole.next();
                        }
                        if (i.equals("to")) {
                            direct = "<=";
                        } else if (i.equals("downto")) {
                            direct = ">=";
                        } else er.set_er(i, main_part.line_number);
                        end_comp = "";
                        i = pole.next();
                        while(!i.equals("do")){
                            if (vari.check_in_mas(i)) {
                                i = vari.Befor_in + vari.Value_in + vari.After_in;
                            }
                            end_comp+=i+" ";
                            i = pole.next();
                        }
                        main_part.body.add("for( " + iter + " = " + meanings_to_ + "; " + iter +" "+ direct+" " + end_comp + "; " + iter + (direct == ">=" ? "-- " : "++ ") + ')');
                    } else if (i.equals("close")) {
                        //закрытие файла
                        i = pole.next();
                        i = pole.next();
                        i = pole.next();
                        main_part.body.add("fclose ("+i+");");
                        i = pole.next();
                    } else if (i.equals("begin") && !main_part.for_main) {
                        main_part.body.add("{");
                    } else if (i.equals("end")) {
                        main_part.body.add("}");
                        i = pole.next();
                    } else if (i.equals("else")) {
                        main_part.body.add("else");
                    } else if (i.equals("exit")) {
                        main_part.body.add("return 0;");
                        i = pole.next();
                    } else if (i.equals("break")) {
                        main_part.body.add("break;");
                        i = pole.next();
                    } else if (i.equals("continue")) {
                        main_part.body.add("continue;");
                        i = pole.next();
                    } else if (i.equals("end.")) {
                        main_part.body.add("}");
                        break;
                    } else if (vari.check_in_vars(i) || vari.check_in_mas(i)) {
                        //действия с переменными и массивами

                        if (vari.check_in_mas(i)) {
                            i = vari.Befor_in + vari.Value_in + vari.After_in;
                        }
                        String znak = pole.next();
                        switch (znak) {
                            case "-=":
                            case "+=":
                            case "/=":
                            case "*=":
                                break;
                            case ":=":
                                znak = "=";
                                break;
                            default:
                                er.set_er(znak, main_part.line_number);
                                break;
                        }
                        String after = pole.next();
                        String essence = "";
                        while (!after.equals(";") && !after.equals("else") && !after.equals("end")) {
                            if (vari.check_in_mas(after)) {
                                after = vari.Befor_in + vari.Value_in + vari.After_in;
                            }
                            essence += " " + after;
                            after = pole.next();
                        }
                        main_part.body.add(i + " " + znak + essence + ';');
                        if (after.equals("else") || after.equals("end")) {
                            i = after;
                            continue;
                        }
                    } else {
                        er.set_er(i, main_part.line_number);
                    }
                }catch (Exception e){
                    er.set_er(i, main_part.line_number);
                }
                if(pole.hasNext())i=pole.next();
                else break;

            }
            code="";
            code+="//Errors:\n";
            //выведение ошибок
            if(er.get_len()==0){
                code+="//no errors\n";
            }
            for(int j=0;j<er.get_len();j++){
                code+="//"+er.get_er(j).name_of_er+" on line "+er.get_er(j).line+'\n';
            }
            /*if(main_part.securet){
                code+="#define _CRT_SECURE_NO_WARNINGS\n";
            }*/
            for(int j=0;j<main_part.start_of_program.size();j++){
                code+=main_part.start_of_program.get(j)+'\n';
            }
            //объявление переменных
            if(vari.vars.size()!=0){
                String type="developrnbuisjhflkgjlkjlkj";
                for(int j=0;j<vari.vars.size();j++){
                    if(vari.vars.get(j).type!=type){
                        if(!type.equals("developrnbuisjhflkgjlkjlkj")){
                            code+=";\n";
                        }
                        code+=vari.vars.get(j).type+ ' '+vari.vars.get(j).name;
                        type=vari.vars.get(j).type;
                    }
                    else{
                        code+=", "+vari.vars.get(j).name;
                    }
                }
                code+=";\n";
            }
            //объявление массивов
            if(vari.massives.size()!=0){
                String type="developrnbuisjhflkgjlkjlkj";
                for(int j=0;j<vari.massives.size();j++){
                    if(vari.massives.get(j).type!=type){
                        if(type.equals("string"))continue;
                        if(!type.equals("developrnbuisjhflkgjlkjlkj")){
                            code+=";\n";
                        }
                        code+=vari.massives.get(j).type+ ' '+vari.massives.get(j).name;
                        for(Inteval k:vari.massives.get(j).dimen){
                            code+="["+(k.end_index-k.begin_ind)+"]";
                        }
                        type=vari.massives.get(j).type;
                    }
                    else{
                        code+=", "+vari.massives.get(j).name;
                        for(Inteval k:vari.massives.get(j).dimen){
                            code+="["+(k.end_index-k.begin_ind)+"]";
                        }
                    }
                }
                code+=";\n";
            }
            //вся остальная часть программы
            for(int j=0;j<main_part.body.size();j++){
                code+=main_part.body.get(j)+'\n';
            }
            //выведение результата на экран
            text_in_layout.setText(code);

        }
    }

}
