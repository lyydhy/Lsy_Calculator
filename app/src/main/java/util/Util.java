package util;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Util {
    //声明三个类的属性,第一个是LinkedList类型，将用于操作数栈，起到装载操作数的作用。
    //第二个用于装载运行符。
    //第三个用于记录用户在窗口中输入的运算式
    private LinkedList<BigDecimal> operandList;
    private LinkedList<String> operatorList;
    private StringBuffer operandBuffer;

    //创建构造函数
    public Util(){
        /**
         * 在构造函数中，去实例化上面的这三个属性对象
         */
        operandList = new LinkedList<BigDecimal>();
        operatorList = new LinkedList<String>();
        operandBuffer = new StringBuffer();
    }


    /** 比较前后运算符的优先级 */
    private char compareOperator(String operator1, String operator2) {
        char result = '\u0000'; // 局部变量初始化
        //通过cahrAt方法获取字符串变量operator1、operator2
        //中的第一个字符分别给o1、o2两个变量
        char o1 = operator1.charAt(0);
        char o2 = operator2.charAt(0);

        //然后使用switch分支选择结构来判断o1和o2变量的
        //值，并执行相对应的操作
        switch (o1) {
            case '+':
                switch (o2) {
                    case '+':
                        result = '>';
                        break;
                    case '-':
                        result = '>';
                        break;
                    case '*':
                        result = '<';
                        break;
                    case '/':
                        result = '<';
                        break;
                    case '(':
                        result = '<';
                        break;
                    case ')':
                        result = '>';
                        break;
                    case '#':
                        result = '>';
                        break;
                }
                break; // 跳出case '+';
            case '-':
                switch (o2) {
                    case '+':
                        result = '>';
                        break;
                    case '-':
                        result = '>';
                        break;
                    case '*':
                        result = '<';
                        break;
                    case '/':
                        result = '<';
                        break;
                    case '(':
                        result = '<';
                        break;
                    case ')':
                        result = '>';
                        break;
                    case '#':
                        result = '>';
                        break;
                }
                break; // 跳出case '-';
            case '*':
                switch (o2) {
                    case '+':
                        result = '>';
                        break;
                    case '-':
                        result = '>';
                        break;
                    case '*':
                        result = '>';
                        break;
                    case '/':
                        result = '>';
                        break;
                    case '(':
                        result = '<';
                        break;
                    case ')':
                        result = '>';
                        break;
                    case '#':
                        result = '>';
                        break;
                }
                break; // 跳出case '*';
            case '/':
                switch (o2) {
                    case '+':
                        result = '>';
                        break;
                    case '-':
                        result = '>';
                        break;
                    case '*':
                        result = '>';
                        break;
                    case '/':
                        result = '>';
                        break;
                    case '(':
                        result = '<';
                        break;
                    case ')':
                        result = '>';
                        break;
                    case '#':
                        result = '>';
                        break;
                }
                break; // 跳出case '/';
            case '(':
                switch (o2) {
                    case '+':
                        result = '<';
                        break;
                    case '-':
                        result = '<';
                        break;
                    case '*':
                        result = '<';
                        break;
                    case '/':
                        result = '<';
                        break;
                    case '(':
                        result = '<';
                        break;
                    case ')':
                        result = '=';
                        break;
                    case '#':
                        result = '?'; // （后不能是#，如果是则是错误输入；
                        break;
                }
                break; // 跳出case '(';
            case ')':
                switch (o2) {
                    case '+':
                        result = '>';
                        break;
                    case '-':
                        result = '>';
                        break;
                    case '*':
                        result = '>';
                        break;
                    case '/':
                        result = '>';
                        break;
                    case '(':
                        result = '?'; // )后不能接（；
                        break;
                    case ')':
                        result = '>';
                        break;
                    case '#':
                        result = '>';
                        break;
                }
                break; // 跳出case ')';
            case '#':
                switch (o2) {
                    case '+':
                        result = '<';
                        break;
                    case '-':
                        result = '<';
                        break;
                    case '*':
                        result = '<';
                        break;
                    case '/':
                        result = '<';
                        break;
                    case '(':
                        result = '<';
                        break;
                    case ')':
                        result = '?'; // #后不能接）；
                        break;
                    case '#':
                        result = '=';
                        break;
                }
                break; // 跳出case '#';
        }// End Of switch

        return result;
    }

    /**
     * 采用算符优先算法计算表达式
     *
     * //@param String
     *            ex : 表达式的字符串；
     * @return String 类型的计算结果；
     */
    public String evaluateExpression(String ex) {
        // 在表达式首尾加上字符'#'以方便比较运算符
        ex = "#" + ex + "#";

        int count = 1; // 从ex的序号为1开始，即‘#’后
        operatorList.addLast("#");
        while (count < ex.length()) {
            String ch = String.valueOf(ex.charAt(count));

            if (Pattern.matches("[0-9\\.]", ch) // 当前字符如果是数字或.就把它放到运算数缓冲区
                    || (ch.equals("-") // "-"看成是负号的条件：在表达式的首位或在”（“之后；
                    && (count == 1 || ex.charAt(count - 1) == '('))) {
                operandBuffer.append(ch);
                ++count;
            }

            else {
                // 把运算数放入栈
                if (Pattern.matches("[\\+\\-\\*\\/\\)\\#]", ch)
                        && operandBuffer.length() != 0) {
                    operandList.addLast(new BigDecimal(Double.valueOf(
                            operandBuffer.toString()).toString()));

                    if (operandBuffer.length() > 0) {
                        operandBuffer.delete(0, operandBuffer.length());
                    }
                }

                // 比较运算符，并根据它进行计算
                switch (compareOperator(operatorList.getLast(), ch)) {
                    // ch优先级高，将ch压入运算符栈
                    case '<':
                        operatorList.addLast(ch);
                        ++count;
                        break;
                    // 优先级相等时，去掉（）或前后的#；
                    case '=':
                        operatorList.removeLast();
                        ++count;
                        break;
                    // ch优先级低，从运算数栈取出两个数，从运算符栈取出运算符，进行计算其结果放入运算数栈；
                    case '>':
                        BigDecimal b = operandList.removeLast();
                        BigDecimal a = operandList.removeLast();
                        String curOperator = operatorList.removeLast();
                        try {
                            operandList.addLast(operate(a, curOperator, b));
                        } catch (ArithmeticException e) {
                            return "除数不能为0！";
                        }

                        break;
                    // 运算符输入错误的处理：终止计算，在屏幕显示input error!
                    default:
                        return "输入有误！";
                }
            }
        }// End 0f while

        String result = operandList.getLast().toString();
        if (operandList.isEmpty() == false)
            operandList.clear();
        if (operatorList.isEmpty() == false)
            operatorList.clear();

        return result;
    }

    /**
     * 根据运算符进行二元计算，
     *
     * //@param BigDecimal类型
     *            a ? b
     * @return： BigDecimal类型的结果
     */
    private BigDecimal operate(BigDecimal a, String operator, BigDecimal b) {
        final int DEF_DIV_SCALE = 5;
        BigDecimal result = null;

        switch (operator.charAt(0)) {
            case '+':
                result = a.add(b);
                break;
            case '-':
                result = a.subtract(b);
                break;
            case '*':
                result = a.multiply(b);
                break;
            case '/':
                result = a.divide(b, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
                break;
        }
        return result;
    }
}
