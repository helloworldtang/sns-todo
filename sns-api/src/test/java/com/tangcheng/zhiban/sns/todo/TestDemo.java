package com.tangcheng.zhiban.sns.todo;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemo {

    @Test
    public void TestDemo1() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date publishDateTime = dateFormat.parse("15:01");

        String[] split = "ddd&nbspdd".split("&nbsp");
        for (String s : split) {
            System.out.println(s);
        }

    }

}
