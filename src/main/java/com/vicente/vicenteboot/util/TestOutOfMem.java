package com.vicente.vicenteboot.util;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestOutOfMem {

    public static void main(String[] args) {
        List list = new ArrayList<Map>();
        int i = 0;
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random random = new Random();
            Map map= new HashMap();
            map.put(i, random.nextInt(100000));
            list.add(map);
            System.out.println(i++);
        }

    }

}
