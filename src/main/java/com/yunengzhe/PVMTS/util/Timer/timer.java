package com.yunengzhe.PVMTS.util.Timer;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class timer {
	/**
	 * ������ʱ��
	 */
//	private static int hour =1;
//	private static int min =00;
//	private static int interval =86400000;
	private static int hour =9;
	private static int min =1;
	private static int interval =86400000;
	public static void timer(){
		//���ÿ�ʼʱ��
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,hour);
		calendar.set(Calendar.MINUTE,min);
		calendar.set(Calendar.SECOND,0);
		Date time = calendar.getTime();
		//������ʱ��
		Timer timer = new Timer();
		MyTask mt = new MyTask();
		mt.setTime(time);
		mt.setMytask(mt);
		mt.setInterVal(interval);
        timer.schedule(mt, time, interval);//mt.runΪ��ʱִ���¼���TIMEִ�п�ʼʱ�䣬ִ�м��ʱ��300000ms(5min) 
        while(true){//���������ֹͣ�������,�����һֱѭ��ִ�д�����     
            try{     
                int in = System.in.read();    
                if(in == 's'){     
                    timer.cancel();//����̨����S�س��˳� 
                    break;  
                }     
            } catch (IOException e){     
                e.printStackTrace();     
            }     
        }  
	}
}
