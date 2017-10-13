package com.mdvns.mdvn.staff.sapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MdvnStaffSapiApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static class Fibonacci1 {
        //定义三个变量方法
        public static void main(String[] args) {
            int a = 1, b = 1, c = 0;
            System.out.println("斐波那契数列前20项为：");
            System.out.print(a + "\t" + b + "\t");
            for (int i = 1; i <= 18; i++) {
                c = a + b;
                a = b;
                b = c;
                System.out.print(c + "\t");
//                if ((i + 2) % 5 == 0)
//                    System.out.println();
            }
        }
    }

}
