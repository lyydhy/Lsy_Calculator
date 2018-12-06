package calculator.cqeec.com.lsy_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Util;

public class MainActivity extends AppCompatActivity {
    private String strOpera = ""; //记录输入的运算式
    private String strResult = ""; //记录运算结果
    private EditText result; //声明输出文本框对象
    private String lstrOpera = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.result = (EditText) this.findViewById(R.id.display); //实例化文本框对象
        //创建删除按钮并添加单击事件
        Button backBtn = (Button) this.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strOpera.equals("") == false) {
                    strOpera = strOpera.substring(0, strOpera.length() - 1);
                    result.setText(strOpera);
                } else {
                    Toast.makeText(MainActivity.this, "老弟，有问题哦！", Toast.LENGTH_LONG).show();
                }

            }
        });
        final Button clearBtn = (Button) this.findViewById(R.id.clearBtn);  //创建清楚按钮并添加单击事件
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strOpera = "";

                strResult = "";
                lstrOpera = "";

                result.setText(strOpera);
            }
        });
        Button equalBtn = (Button) this.findViewById(R.id.equalBtn);// //创建等于按钮并添加单击事件
        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strOpera.equals("") == true) {
                    Toast.makeText(MainActivity.this, "老弟，有问题哦！", Toast.LENGTH_LONG).show();
                } else {


                    Util util = new Util();
                    try {
                        strResult = util.evaluateExpression(strOpera);
                    } catch (Exception e3) {
                        Toast.makeText(MainActivity.this, "老弟，有问题哦！", Toast.LENGTH_LONG).show();
                        strOpera = "";
                    }
                    lstrOpera = strResult;
                    result.setText(strOpera + "\n" + lstrOpera);
                    strOpera = "";
                    strResult = "";


                }

            }
        });

    }

    //用于数字键，以及运算符共用的单击事件
    public void onClick(View v) {


        this.strOpera += ((Button) v).getText().toString();
        String n = this.strOpera.substring(0);


        if (!this.lstrOpera.equals("")) {
            if (n.equals("+") || n.equals("-") || n.equals("*") || n.equals("/")||n.equals("(")) {
                this.strOpera = this.lstrOpera.concat(strOpera);
                if(n.equals("("))
                {
                    StringBuffer s=new StringBuffer(this.strOpera);
                    int g=this.strOpera.indexOf("(");
                    s.insert(g,"*");
                    this.strOpera=s.toString();
                }
            }

        }
        this.result.setText(this.strOpera);


    }


}


