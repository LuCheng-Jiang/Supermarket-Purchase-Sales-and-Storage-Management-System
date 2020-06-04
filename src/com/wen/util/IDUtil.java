package com.wen.util;

import java.util.Random;

/**
 * 
 * @author
 * 用于生成毕业设计序号 0000 ~ 9999
 */
public class IDUtil {
	
	/**
	 * 生成毕业设计选题序号：范围 （0000 ~ 9999）
	 * @return
	 */
	public static String getId() {
		StringBuffer sb = new StringBuffer();
		
		Random r = new Random();
		for(int i=0;i<=3;i++) {
			int x = r.nextInt(10);
			sb.append(x);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
       StringBuffer sb = new StringBuffer();
		
		Random r = new Random();
		for(int i=0;i<=3;i++) {
			int x = r.nextInt(10);
			sb.append(x);
		}
		System.out.println(sb.toString());
	}
}
