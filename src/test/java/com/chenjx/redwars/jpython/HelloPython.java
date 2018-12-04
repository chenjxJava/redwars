package com.chenjx.redwars.jpython;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.util.ArrayList;

/**
 * create by chenjx 2018/9/6
 */

public class HelloPython {

    @Test
    public void python() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("print('hello')");
        interpreter.exec("a=[5,2,3,9,4,0]; ");
        interpreter.exec("print(sorted(a));");  //此处python语句是3.x版本的语法
        interpreter.exec("print sorted(a);");   //此处是python语句是2.x版本的语法
    }

    @Test
    public void pythonFunc() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("F:/location/ideaProject/redwars/py/hello.py");

        PyFunction pyFunction = interpreter.get("hello", PyFunction.class); // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyObject pyObject = pyFunction.__call__(); // 调用函数

        System.out.println(pyObject);
    }

    @Test
    public void demo() {
        ArrayList<Object> objects = Lists.newArrayList();
        System.out.println(objects);
    }
}
