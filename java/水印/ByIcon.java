package com.ai.cq12582.system;  
    
import java.awt.AlphaComposite;  
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.RenderingHints;  
import java.awt.Transparency;
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.OutputStream;  
  



import javax.imageio.ImageIO;  
import javax.swing.ImageIcon;  
     
public class ByIcon {     
    
    public static void main(String[] args) {     
        String targerPath = "new.png" ;   
        String content = "test1201";
    	int width = 400;
		int height = 300;
        ByIcon.markImageByIcon(content,targerPath , -30, width,height);    
    }     
    
    public static void markImage(String content, OutputStream os, Integer degree,int width,int height) {     
		
        try {     
        	
        	BufferedImage buffImg0 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		Graphics2D g0 = buffImg0.createGraphics();

    		buffImg0 = g0.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT); 
    		g0.dispose(); 
    		g0 = buffImg0.createGraphics(); 
    		
    		g0.drawString("", 2, 30);
    		
    		BufferedImage buffImg = new BufferedImage(width,  height, BufferedImage.TYPE_INT_RGB);   
            Graphics2D g = buffImg.createGraphics();     
    
            buffImg = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT); 
    		g.dispose(); 
    		g = buffImg.createGraphics(); 
    		
            // 设置对线段的锯齿状边缘处理     
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);     
    
            g.drawImage(buffImg0.getScaledInstance(buffImg0.getWidth(null), buffImg0.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);  
            		
            if (null != degree) {     
                g.rotate(Math.toRadians(degree),     
                        (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);     
            }     

            g.setColor(Color.GRAY); 
    		
    		Font font1=new Font("Times New Roman",Font.BOLD,30);
    		g.setFont(font1);
    		
            g.drawString(content,buffImg.getWidth() / 2, buffImg.getHeight() / 2);
            
            g.dispose();   
            g0.dispose();
            // 生成图片     
            ImageIO.write(buffImg, "PNG", os);     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if (null != os)     
                    os.close();     
            } catch (Exception e) {     
                e.printStackTrace();     
            }     
        }     
    }     
    public static void markImageByIcon(String content, String targerPath, Integer degree,int width,int height) {     
		
        OutputStream os = null;     
        try {     
        	
        	BufferedImage buffImg0 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		Graphics2D g0 = buffImg0.createGraphics();

    		buffImg0 = g0.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT); 
    		g0.dispose(); 
    		g0 = buffImg0.createGraphics(); 
    		
    		g0.drawString("", 2, 30);
    		
    		BufferedImage buffImg = new BufferedImage(width,  height, BufferedImage.TYPE_INT_RGB);   
            Graphics2D g = buffImg.createGraphics();     
    
            buffImg = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT); 
    		g.dispose(); 
    		g = buffImg.createGraphics(); 
    		
            // 设置对线段的锯齿状边缘处理     
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);     
    
            g.drawImage(buffImg0.getScaledInstance(buffImg0.getWidth(null), buffImg0.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);  
            		
            if (null != degree) {     
                g.rotate(Math.toRadians(degree),     
                        (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);     
            }     

            g.setColor(Color.GRAY); 
    		
    		Font font1=new Font("Times New Roman",Font.BOLD,30);
    		g.setFont(font1);
    		
            g.drawString(content,60,30);
            
            g.dispose();     
            os = new FileOutputStream(targerPath);     
            // 生成图片     
            ImageIO.write(buffImg, "PNG", os);     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if (null != os)     
                    os.close();     
            } catch (Exception e) {     
                e.printStackTrace();     
            }     
        }     
    }     
}   
