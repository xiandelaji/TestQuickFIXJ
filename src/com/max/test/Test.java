package com.max.test;

import java.text.DecimalFormat;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random random1 = new Random();
        System.out.println(random1.nextInt());
        System.out.println(new Random().nextDouble());
        System.out.println((new Double(new DecimalFormat("##.0000").format(new Random().nextFloat()*100))));
        System.out.println(Math.abs(new Random().nextInt())%1000);
        System.out.println(Math.abs(new Random().nextInt())%100+new DecimalFormat("##.0000").format(Math.abs((new Random().nextFloat()))));

	}

}
