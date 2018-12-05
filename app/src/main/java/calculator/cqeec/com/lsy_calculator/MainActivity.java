package calculator.cqeec.com.lsy_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                        strOpera = util.evaluateExpression(strOpera);
                    } catch (AbstractMethodError e3) {
                        Toast.makeText(MainActivity.this, "老弟，有问题哦！", Toast.LENGTH_LONG).show();
                    }
                    strResult = util.evaluateExpression(strOpera);
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
        int b = 0;
        String g = "+-*/";
        String c = this.strOpera;
//                 try{
//                     for(int i=1;i<this.strOpera.length();i++)
//
//                     {/*
//                     if(c.charAt(i)==this.strOpera.charAt(++i)&&c.substring(i).equals("+")||
//                             c.substring(i).equals("-")||
//                             c.substring(i).equals("*")||
//                             c.substring(i).equals("/")||
//                             c.substring(i).equals("."))
//                     {
//                         Toast.makeText(MainActivity.this,"老弟有问题哦！",Toast.LENGTH_LONG).show();
//                        this.strOpera=this.strOpera.substring(0,this.strOpera.length()-1);
//                     }*/
//                      if(g.indexOf(this.strOpera.substring(i))!=-1)
//                      {
//                          b++;
//                          if(b>2)
//                          {
//                              Toast.makeText(MainActivity.this,"老弟有问题哦！",Toast.LENGTH_LONG).show();
//                          }
//                      }
//                     }
//                 }
//                 catch (Exception e2)
//                 {
//                     Toast.makeText(MainActivity.this,"老弟有11问题哦！",Toast.LENGTH_LONG).show();
//                 }
//

        if (!this.lstrOpera.equals("")) {
            if (n.equals("+") || n.equals("-") || n.equals("*") || n.equals("/")) {
                this.strOpera = this.lstrOpera.concat(strOpera);
            }
        } else {

            if (n.equals("+") || n.equals("-") || n.equals("*") || n.equals("/") || n.equals(")")) {
                Toast.makeText(MainActivity.this, "老弟，有问题哦！", Toast.LENGTH_LONG).show();
                this.strOpera = "";

            }


        }
       this.result.setText(strOpera);





    }


}


