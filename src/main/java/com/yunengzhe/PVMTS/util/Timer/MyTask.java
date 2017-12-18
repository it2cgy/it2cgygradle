package com.yunengzhe.PVMTS.util.Timer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ������ʱ���������
 *
 */
public class MyTask extends java.util.TimerTask{      
    private Date time;
    private MyTask mytask;
    private int InterVal;

	public int getInterVal() {
		return InterVal;
	}

	public void setInterVal(int interVal) {
		InterVal = interVal;
	}

	public MyTask getMytask() {
		return mytask;
	}

	public void setMytask(MyTask mytask) {
		this.mytask = mytask;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void run(){   
//		System.out.println(time);
		long datetime = time.getTime();
		datetime = datetime - 180000;
		Date testTime = new Date(datetime);
//		System.out.println(testTime+"-----------------  ");
		getTime(testTime);
        long tm = time.getTime()+InterVal;
        mytask.setTime(new Date(tm));
    } 
	/**
	 * ���ü�ʱ��
	 * @param time
	 */
	@RequestMapping(params="Method=updatestatus")
	public static void getTime(Date time){
		System.out.println("开始啦");
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = df.format(now);
//		companyServiceImpl.updatestatus("2017-04-01");
	}
}
