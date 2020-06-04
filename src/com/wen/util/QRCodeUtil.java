package com.wen.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.wen.dao.pojo.Product;
import com.swetake.util.Qrcode;

public class QRCodeUtil {
	
	public static void Qrcode(Product p,HttpServletRequest request,String path) throws IOException {
		 Qrcode qrcode = new Qrcode();  
	       qrcode.setQrcodeErrorCorrect('M');//纠错等级（分为L、M、H三个等级）  
	       qrcode.setQrcodeEncodeMode('B');//N代表数字，A代表a-Z，B代表其它字符  
	       qrcode.setQrcodeVersion(20);//版本  
	       //生成二维码中要存储的信息  
	       String qrData ="商品编号："+p.getPid()+ ",商品名称："+p.getPname()+",商品种类："+p.getCid()+",规格等级："+p.getPspec()+",单位："+p.getUid()+",报警数量："+p.getPminNumber();
	       //设置一下二维码的像素  
	       int width = 67 + 12*19;
	       int height = 67 + 12*19;
	       BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	       //绘图  
	       Graphics2D gs = bufferedImage.createGraphics();  
	       gs.setBackground(Color.WHITE);  
	       gs.setColor(Color.BLACK);  
	       gs.clearRect(0, 0, width, height);//清除下画板内容  
	         
	       //设置下偏移量,如果不加偏移量，有时会导致出错。  
	       int pixoff = 2;  
	         
	       byte[] d = qrData.getBytes("gb2312");  
	       if(d.length > 0 && d.length <120){  
	           boolean[][] s = qrcode.calQrcode(d);  
	           for(int i=0;i<s.length;i++){  
	               for(int j=0;j<s.length;j++){  
	                   if(s[j][i]){  
	                       gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);  
	                   }  
	               }  
	           }  
	       }  
	       gs.dispose();  
	       bufferedImage.flush();
	       String realPath = request.getSession().getServletContext().getRealPath("/")+path;// 项目在容器中实际发布运行的根路径
	       ImageIO.write(bufferedImage, "png", new File(realPath));  
	}
}
