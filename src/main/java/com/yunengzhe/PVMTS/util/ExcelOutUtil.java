package com.yunengzhe.PVMTS.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import com.yunengzhe.commons.util.PropertiesUtil;

import jxl.CellType;
import jxl.CellView;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;  

public class ExcelOutUtil extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelOutUtil.class);
	
	
     /**
      * 历史导出
      * @param response
      * @param type 曲线类型
      * @param list
      * @throws Exception
      */
	public void WriteExcel(HttpServletResponse response,String type,List<Map<String,Object>> list) throws Exception{
		//MCTIME=20160902 18:54, inp=0, dgencap=107, itp=0, DATATIME=1472813699, ittemp=33.3, pofactor=0
	    String fileName =  PropertiesUtil.getString("download_temp_path")+ "/"+"inverter.xls";
	    String fileNames="";
        try {
             File fileit=new File(fileName);
             fileit.setWritable(true, false);
             String fileNameit=fileit.getPath();
             System.out.println(fileNameit);
//			打开文件
            WritableWorkbook book = Workbook.createWorkbook( fileit);
//			生成名为“第一页”的工作表，参数0表示这是第一页
            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
            SheetSettings ss = sheet.getSettings();
            sheet.setColumnView(0, 40);
            sheet.setColumnView(1, 20);
            sheet.addCell( new Label( 0 , 0 , " 时间",CBwcfF));
            if(type.equals("inp")){
            	sheet.addCell( new Label( 1 , 0 , " 逆变器直流功率(kw)",CBwcfF));
            	fileNames="逆变器直流功率.xls";
            }
            if(type.equals("itp")){
            	sheet.addCell( new Label( 1 , 0 , " 逆变器交流功率(kw)",CBwcfF));
            	fileNames="逆变器交流功率.xls";
            }
            if(type.equals("dgencap")){
            	sheet.addCell( new Label( 1 , 0 , " 日发电量 (kWh)",CBwcfF));
            	fileNames="日发电量.xls";
            }
            if(type.equals("ittemp")){
            	sheet.addCell( new Label( 1 , 0 , " 逆变器温度(℃)",CBwcfF));
            	fileNames="逆变器温度.xls";
            }
            if(type.equals("pofactor")){
            	sheet.addCell( new Label( 1 , 0 , " 功率因数",CBwcfF));
            	fileNames="功率因数.xls";
            }
          
            DecimalFormat df = new DecimalFormat("#.00");
            int conut = 1;
            for (int i = 0; i < list.size(); i++) {   //循环一个list里面的数据到excel中
                
                if(list.get(i).get("hdtime")!=null){
                	sheet.addCell(new Label( 0 , conut ,list.get(i).get("hdtime").toString(),CBwcfF));//MCTIME
                }
                
      /*          if(type.equals("inp")){
                	if(list.get(i).get("inp")!=null){
   					 sheet.addCell(new Label( 1 , conut ,list.get(i).get("inp").toString(),CBwcfF));             	
   				                }
                }
                if(type.equals("dgencap")){
                	if(list.get(i).get("dgencap")!=null){
   					 sheet.addCell(new Label( 1 , conut ,list.get(i).get("dgencap").toString(),CBwcfF));             	
   				                }
                }
                if(type.equals("ittemp")){
                	if(list.get(i).get("ittemp")!=null){
   					 sheet.addCell(new Label( 1 , conut ,list.get(i).get("ittemp").toString(),CBwcfF));             	
   				                }
                }
                if(type.equals("pofactor")){
                	if(list.get(i).get("pofactor")!=null){
   					 sheet.addCell(new Label( 1 , conut ,list.get(i).get("pofactor").toString(),CBwcfF));             	
   				                }
                }*/
              
                sheet.addCell(new Label( 1 , conut ,list.get(i).get("value").toString(),CBwcfF));          
			
                conut++;
            }
         
            book.write();
            book.close();
            //-------------------------------------------------------------------------------------
            OutputStream os = null;
            InputStream in = null;
            String name=new String(fileNames.getBytes("UTF-8"),"ISO8859-1");
            response.reset();
           // response.setContentType("application/msexcel;charset=UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename="+name);
            try {
//                os.flush();
                os = response.getOutputStream();
                in = new FileInputStream(fileName);
                byte[] buffer = new byte[1024];
                int i = -1;
                while ((i = in.read(buffer)) != -1) {
                	os.write(buffer, 0, i);
                    // out.write(buffer,0,i);
         
                }
                os.flush();
                
                os.close();
                
            } catch (Exception e) {
                // TODO Auto-generated catch block 
                logger.error("发生异常"+e.getMessage(),e);
            } finally {
            	fileit.delete();
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (os != null) {
                    os.flush();
                    os.close();
                    os = null;
                }
         
            }
            //-------------------------------------------------------------------------------------
        } catch (Exception e) { 
            logger.error("发生异常"+e.getMessage(),e);
        }
       
	
	}
	
	/**
	 * 月报表导出
	 * @param response
	 * @param type 
	 * @param list  内容
	 * @param list2   表头
	 * @throws Exception
	 */
	
	
	public void bbglExcel(HttpServletResponse response,String type,List<Map<String,Object>> list,List list2) throws Exception{
		//MCTIME=20160902 18:54, inp=0, dgencap=107, itp=0, DATATIME=1472813699, ittemp=33.3, pofactor=0
	   
		String fileName = PropertiesUtil.getString("download_temp_path")+ "/monthtable.xls";
        try {
             File fileit=new File(fileName);
             fileit.setWritable(true, false);
             String fileNameit=fileit.getPath();
             System.out.println(fileNameit);
//			打开文件
            WritableWorkbook book = Workbook.createWorkbook( fileit);
//			生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
            SheetSettings ss = sheet.getSettings();
            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            //for(list.get(arg0))
            sheet.setColumnView(0, 40);
            sheet.setColumnView(1, 20);
            
            sheet.addCell( new Label( 0 , 0 , "日期",CBwcfF));
            
            for(int i=0;i<list2.size();i++){
            	sheet.addCell( new Label( i+1 , 0 , ((String)list2.get(i)).substring(0,10),CBwcfF));
            }
            DecimalFormat df = new DecimalFormat("#.00");
            int conut = 1;
            for (int i = 0; i < list.size(); i++) {   //循环一个list里面的数据到excel中
                
                if(list.get(i).get("tou")!=null){
                	sheet.addCell(new Label( 0 , conut ,list.get(i).get("tou").toString(),CBwcfF));
                }
                
                
                for(int j=0;j<list2.size();j++){
                	sheet.addCell(new Label( j+1 , conut ,list.get(i).get((String)list2.get(j)).toString(),CBwcfF)); 
                }
            
				
			
                conut++;
            }
         
            book.write();
            book.close();
            //-------------------------------------------------------------------------------------
            OutputStream os = null;
            InputStream in = null;
            String fileNames="月报表.xls";
            String name=new String(fileNames.getBytes("UTF-8"),"ISO8859-1");
            String tablename=(((String)list2.get(0)).toString()).substring(0,7);
            response.reset();
            String tablenamename=tablename+name;
           // response.setContentType("application/msexcel;charset=UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename="+tablenamename);
            try {
//                os.flush();
                os = response.getOutputStream();
                in = new FileInputStream(fileName);
                byte[] buffer = new byte[1024];
                int i = -1;
                while ((i = in.read(buffer)) != -1) {
                	os.write(buffer, 0, i);
                    // out.write(buffer,0,i);
         
                }
                os.flush();
                
                os.close();
                
            } catch (Exception e) {
                // TODO Auto-generated catch block

                logger.error("发生异常"+e.getMessage(),e);
            } finally {
            	fileit.delete();
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (os != null) {
                    os.flush();
                    os.close();
                    os = null;
                }
         
            }
            //-------------------------------------------------------------------------------------
        } catch (Exception e) {

            logger.error("发生异常"+e.getMessage(),e);
        }
       
	
	}
	
	
	/**
	 * 日报表下载
	 * @param response
	 * @param type
	 * @param list  内容
	 * @param list2   表头
	 * @throws Exception
	 */
	
	public void rbglExcel(HttpServletResponse response,String type,List<Map<String,Object>> list,List<Map<String,Object>> list2) throws Exception{
		//MCTIME=20160902 18:54, inp=0, dgencap=107, itp=0, DATATIME=1472813699, ittemp=33.3, pofactor=0
		
		//[{run_status_descrip=, irradiation=1, max_irr_time=, max_windspeed=, max_irr_intensity=0, name=test1, max_component_temp=, create_time=2016-10-19 13:12:25.0, max_temperature=45}]
	    String fileName = PropertiesUtil.getString("download_temp_path")+ "/daytable.xls";
	    logger.debug("export excel path:"+fileName);
        try {
             File fileit=new File(fileName);
             fileit.setWritable(true, false);
             String fileNameit=fileit.getPath();
             System.out.println(fileNameit);
//			打开文件
            WritableWorkbook book = Workbook.createWorkbook( fileit);
//			生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
            SheetSettings ss = sheet.getSettings();
            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
            jxl.write.WritableCellFormat CBwcfF2=new jxl.write.WritableCellFormat(Bwf);
            //设置表格格式
            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);    
            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            
          //  CBwcfF2.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF2.setAlignment(jxl.format.Alignment.LEFT);
            CBwcfF2.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);
            CBwcfF2.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            
            
            sheet.setColumnView(0, 15);
            sheet.setColumnView(1, 20);
            sheet.setColumnView(2, 32);
            sheet.setColumnView(3, 32);
            sheet.setColumnView(4, 28);
            sheet.setColumnView(5, 20);
            sheet.setColumnView(6, 20);
            sheet.setColumnView(7, 20);
            sheet.addCell( new Label( 0 , 0 , "一 、基本信息",CBwcfF));
            sheet.mergeCells(0, 0, 6,0);
            sheet.addCell( new Label( 0 , 1 , "电站名称",CBwcfF));
            sheet.mergeCells(0, 1, 1,1);
            sheet.addCell( new Label( 2 , 1 , (String)list2.get(0).get("name"),CBwcfF));
            sheet.mergeCells(2, 1, 3,1);
            sheet.addCell( new Label( 4 , 1 , "报表生成时间",CBwcfF));
            sheet.addCell( new Label( 5 , 1 , (String)list2.get(0).get("create_time"),CBwcfF));
            sheet.mergeCells(5, 1, 6,1);
            sheet.addCell( new Label( 0 , 2 , "日辐照量（kWh）",CBwcfF));
            sheet.mergeCells(0, 2, 1,2);
            sheet.addCell( new Label( 2 , 2 , (String)list2.get(0).get("irradiation"),CBwcfF));
            sheet.mergeCells(2, 2, 3,2);
            sheet.addCell( new Label( 4 , 2 , "最高辐照强度（kW/㎡）",CBwcfF));
            sheet.addCell( new Label( 5 , 2 , (String)list2.get(0).get("max_irr_intensity"),CBwcfF));
            sheet.mergeCells(5, 2, 6,2);
            sheet.addCell( new Label( 0 , 3 , "最大风速（m/s）",CBwcfF));
            sheet.mergeCells(0, 3, 1,3);
            sheet.addCell( new Label( 2 , 3 , (String)list2.get(0).get("max_windspeed"),CBwcfF));
            sheet.mergeCells(2, 3, 3,3);
            sheet.addCell( new Label( 4 , 3 , "最高辐射时间",CBwcfF));
            sheet.addCell( new Label( 5 , 3 , (String)list2.get(0).get("max_irr_time"),CBwcfF));
            sheet.mergeCells(5, 3, 6,3);
            sheet.addCell( new Label( 0 , 4 , "最高气温（℃）",CBwcfF));
            sheet.mergeCells(0, 4, 1,4);
            sheet.addCell( new Label( 2 , 4 , (String)list2.get(0).get("max_temperature"),CBwcfF));
            sheet.mergeCells(2, 4, 3,4);
            sheet.addCell( new Label( 4 , 4 , "组件最高温度（℃）",CBwcfF));
            sheet.addCell( new Label( 5 , 4 , (String)list2.get(0).get("max_component_temp"),CBwcfF));
            sheet.mergeCells(5, 4, 6,4);
            DecimalFormat df = new DecimalFormat("#.00");
            int conut = 4;
            int h =0;	  
            int j=0;
            int d=0;
            int y=0;
            int bwd=1;
            String idcopy="";
            	for (int i = 0; i < list.size(); i++) {
            		if(!list.get(i).get("id").toString().equals(idcopy)||idcopy.equals("")){
            		conut+=2;
            		idcopy=list.get(i).get("id").toString();
            		if(y==1){
            			sheet.addCell(new Label( 0 , conut-1 , "",CBwcfF));
            			sheet.mergeCells(0, conut-1, 6, conut-1);
            		}
            		if(y==0){
            			sheet.addCell(new Label( 0 , conut-1 , "二 、设备信息",CBwcfF));
            			sheet.mergeCells(0, conut-1, 6,conut-1);
            			y=1;
            		}
            		
            		sheet.addCell(new Label( 0 , conut , "并网点("+bwd+")",CBwcfF));
            		bwd++;
            		sheet.addCell(new Label( 1 , conut , list.get(i).get("name").toString(),CBwcfF));
            		sheet.addCell(new Label( 2 , conut , "总电量（kWh）",CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,"峰值电量（kWh）",CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,"平值电量（kWh）",CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,"谷值电量（kWh）",CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,"尖值电量（kWh）",CBwcfF));
            		h = conut;
                	conut++;	
        			sheet.addCell(new Label( 2 , conut ,list.get(i).get("total_eq").toString(),CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,list.get(i).get("peak_eq").toString(),CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,list.get(i).get("flat_eq").toString(),CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,list.get(i).get("valley_eq").toString(),CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,list.get(i).get("sharp_eq").toString(),CBwcfF));
            		conut++;	
            		sheet.addCell(new Label( 1 , conut ,"逆变器名称",CBwcfF));
            		sheet.addCell(new Label( 2 , conut ,"逆变器所接组件总功率（kW）",CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,"设备最高交流功率（kW）",CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,"设备最高温度（℃）",CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,"平均效率（%）",CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,"日电量（kWh）",CBwcfF));
            		conut++;	
            		sheet.addCell(new Label( 1 , conut ,list.get(i).get("itname").toString(),CBwcfF));
            		sheet.addCell(new Label( 2 , conut ,list.get(i).get("inp").toString(),CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,list.get(i).get("max_jlp").toString(),CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,list.get(i).get("max_temperature").toString(),CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,list.get(i).get("avg_itpower").toString(),CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,list.get(i).get("nbqtotal_eq").toString(),CBwcfF));
            		sheet.mergeCells(1, h, 1, h+1);
            		}else{
        			conut++;	
        			sheet.addCell(new Label( 1 , conut ,list.get(i).get("itname").toString(),CBwcfF));
            		sheet.addCell(new Label( 2 , conut ,list.get(i).get("inp").toString(),CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,list.get(i).get("max_jlp").toString(),CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,list.get(i).get("max_temperature").toString(),CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,list.get(i).get("avg_itpower").toString(),CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,list.get(i).get("nbqtotal_eq").toString(),CBwcfF));
            		}
            		if(j==0){
            			j=h;
            		}else{
            			if(j!=h){
            				System.out.println("j: "+j+" d:"+conut);
            				sheet.mergeCells(0, j, 0, d);
            				j=h;
            			}else{
            				d=conut;
            			}
            		}
         
                }
            	sheet.mergeCells(0, h, 0, conut);
            	System.out.println("h: "+h+" conut:"+conut);
            	sheet.addCell( new Label( 0 , conut+1 , "三、运行情况说明",CBwcfF));
            	sheet.addCell( new Label( 0 , conut+2 , (String)list2.get(0).get("run_status_descrip"),CBwcfF2));
            	sheet.mergeCells(0,conut+1, 6, conut+1);
            	sheet.mergeCells(0,conut+2, 6, conut+3);
            book.write();
            book.close();
            //-------------------------------------------------------------------------------------
            OutputStream os = null;
            InputStream in = null;
            String tablename=((String)list2.get(0).get("create_time")).substring(0,10);
            String fileNames="月报表.xls";
            String name=new String(fileNames.getBytes("UTF-8"),"ISO8859-1");
            String tablenamename=tablename+name;
            response.reset();
            response.setContentType("application/x-msdownload;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename="+tablenamename);
            try {
                os = response.getOutputStream();
                in = new FileInputStream(fileName);
                byte[] buffer = new byte[1024];
                int i = -1;
                while ((i = in.read(buffer)) != -1) {
                	os.write(buffer, 0, i);
                }
                os.flush();
                
                os.close();
                
            } catch (Exception e) {
                // TODO Auto-generated catch block

                logger.error("发生异常"+e.getMessage(),e);
            } finally {
            	fileit.delete();
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (os != null) {
                    os.flush();
                    os.close();
                    os = null;
                }
         
            }
            //-------------------------------------------------------------------------------------
        } catch (Exception e) {

            logger.error("发生异常"+e.getMessage(),e);
        }
       
	
	}
	
	public List<File> bbglallExcel(HttpServletResponse response,String type,List<Map<String,Object>> list,List list2,List<File> listfile) throws Exception{
		//MCTIME=20160902 18:54, inp=0, dgencap=107, itp=0, DATATIME=1472813699, ittemp=33.3, pofactor=0
		 String tablename=(((String)list2.get(0)).toString()).substring(0,7);
	    String fileName =PropertiesUtil.getString("download_temp_path")+ "/monthtable"+tablename+".xls";
	    logger.debug("export excel path:"+fileName);
        try {
             File fileit=new File(fileName);
             listfile.add(fileit);
             fileit.setWritable(true, false);
             String fileNameit=fileit.getPath();
             System.out.println(fileNameit);
//			打开文件
            WritableWorkbook book = Workbook.createWorkbook( fileit);
//			生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
            SheetSettings ss = sheet.getSettings();
            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            //for(list.get(arg0))
            sheet.setColumnView(0, 40);
            sheet.setColumnView(1, 20);
            
            sheet.addCell( new Label( 0 , 0 , "日期",CBwcfF));
            
            for(int i=0;i<list2.size();i++){
            	sheet.addCell( new Label( i+1 , 0 , ((String)list2.get(i)).substring(0,10),CBwcfF));
            }
            DecimalFormat df = new DecimalFormat("#.00");
            int conut = 1;
            for (int i = 0; i < list.size(); i++) {   //循环一个list里面的数据到excel中
                
                if(list.get(i).get("tou")!=null){
                	sheet.addCell(new Label( 0 , conut ,list.get(i).get("tou").toString(),CBwcfF));
                }
                
                
                for(int j=0;j<list2.size();j++){
                	sheet.addCell(new Label( j+1 , conut ,list.get(i).get((String)list2.get(j)).toString(),CBwcfF)); 
                }
            
				
			
                conut++;
            }
         
            book.write();
            book.close();
          
            //-------------------------------------------------------------------------------------
        } catch (Exception e) {

            logger.error("发生异常"+e.getMessage(),e);
        }
        return listfile;
       
	
	}
	
	
	public List<File> rbglallExcel(HttpServletResponse response,String type,List<Map<String,Object>> list,List<Map<String,Object>> list2,List<File> listfile) throws Exception{
		//MCTIME=20160902 18:54, inp=0, dgencap=107, itp=0, DATATIME=1472813699, ittemp=33.3, pofactor=0
		
		//[{run_status_descrip=, irradiation=1, max_irr_time=, max_windspeed=, max_irr_intensity=0, name=test1, max_component_temp=, create_time=2016-10-19 13:12:25.0, max_temperature=45}]
		 String tablename=((String)list2.get(0).get("create_time")).substring(0,10);
		String fileName =PropertiesUtil.getString("download_temp_path")+ "/daytable"+tablename+".xls";
		
		  logger.debug("export excel path:"+fileName);
		  
        try {
             File fileit=new File(fileName);
             listfile.add(fileit);
             fileit.setWritable(true, false);
             String fileNameit=fileit.getPath();
             System.out.println(fileNameit);
//			打开文件
            WritableWorkbook book = Workbook.createWorkbook( fileit);
//			生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
            SheetSettings ss = sheet.getSettings();
            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
            jxl.write.WritableCellFormat CBwcfF2=new jxl.write.WritableCellFormat(Bwf);
            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            
          //  CBwcfF2.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF2.setAlignment(jxl.format.Alignment.LEFT);
            CBwcfF2.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);
            CBwcfF2.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            
            
            sheet.setColumnView(0, 15);
            sheet.setColumnView(1, 20);
            sheet.setColumnView(2, 32);
            sheet.setColumnView(3, 32);
            sheet.setColumnView(4, 28);
            sheet.setColumnView(5, 20);
            sheet.setColumnView(6, 20);
            sheet.setColumnView(7, 20);
            sheet.addCell( new Label( 0 , 0 , "一 、基本信息",CBwcfF));
            sheet.mergeCells(0, 0, 6,0);
            sheet.addCell( new Label( 0 , 1 , "电站名称",CBwcfF));
            sheet.mergeCells(0, 1, 1,1);
            sheet.addCell( new Label( 2 , 1 , (String)list2.get(0).get("name"),CBwcfF));
            sheet.mergeCells(2, 1, 3,1);
            sheet.addCell( new Label( 4 , 1 , "报表生成时间",CBwcfF));
            sheet.addCell( new Label( 5 , 1 , (String)list2.get(0).get("create_time"),CBwcfF));
            sheet.mergeCells(5, 1, 6,1);
            sheet.addCell( new Label( 0 , 2 , "日辐照量（kWh）",CBwcfF));
            sheet.mergeCells(0, 2, 1,2);
            sheet.addCell( new Label( 2 , 2 , (String)list2.get(0).get("irradiation"),CBwcfF));
            sheet.mergeCells(2, 2, 3,2);
            sheet.addCell( new Label( 4 , 2 , "最高辐照强度（kW/㎡）",CBwcfF));
            sheet.addCell( new Label( 5 , 2 , (String)list2.get(0).get("max_irr_intensity"),CBwcfF));
            sheet.mergeCells(5, 2, 6,2);
            sheet.addCell( new Label( 0 , 3 , "最大风速（m/s）",CBwcfF));
            sheet.mergeCells(0, 3, 1,3);
            sheet.addCell( new Label( 2 , 3 , (String)list2.get(0).get("max_windspeed"),CBwcfF));
            sheet.mergeCells(2, 3, 3,3);
            sheet.addCell( new Label( 4 , 3 , "最高辐射时间",CBwcfF));
            sheet.addCell( new Label( 5 , 3 , (String)list2.get(0).get("max_irr_time"),CBwcfF));
            sheet.mergeCells(5, 3, 6,3);
            sheet.addCell( new Label( 0 , 4 , "最高气温（℃）",CBwcfF));
            sheet.mergeCells(0, 4, 1,4);
            sheet.addCell( new Label( 2 , 4 , (String)list2.get(0).get("max_temperature"),CBwcfF));
            sheet.mergeCells(2, 4, 3,4);
            sheet.addCell( new Label( 4 , 4 , "组件最高温度（℃）",CBwcfF));
            sheet.addCell( new Label( 5 , 4 , (String)list2.get(0).get("max_component_temp"),CBwcfF));
            sheet.mergeCells(5, 4, 6,4);
            DecimalFormat df = new DecimalFormat("#.00");
            int conut = 4;
            int h =0;	  
            int j=0;
            int d=0;
            int y=0;
            int bwd=1;
            String idcopy="";
            	for (int i = 0; i < list.size(); i++) {
            		if(!list.get(i).get("id").toString().equals(idcopy)||idcopy.equals("")){
            		conut+=2;
            		idcopy=list.get(i).get("id").toString();
            		if(y==1){
            			sheet.addCell(new Label( 0 , conut-1 , "",CBwcfF));
            			sheet.mergeCells(0, conut-1, 6, conut-1);
            		}
            		if(y==0){
            			sheet.addCell(new Label( 0 , conut-1 , "二 、设备信息",CBwcfF));
            			sheet.mergeCells(0, conut-1, 6,conut-1);
            			y=1;
            		}
            		
            		sheet.addCell(new Label( 0 , conut , "并网点("+bwd+")",CBwcfF));
            		bwd++;
            		sheet.addCell(new Label( 1 , conut , list.get(i).get("name").toString(),CBwcfF));
            		sheet.addCell(new Label( 2 , conut , "总电量（kWh）",CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,"峰值电量（kWh）",CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,"平值电量（kWh）",CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,"谷值电量（kWh）",CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,"尖值电量（kWh）",CBwcfF));
            		h = conut;
                	conut++;	
        			sheet.addCell(new Label( 2 , conut ,list.get(i).get("total_eq").toString(),CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,list.get(i).get("peak_eq").toString(),CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,list.get(i).get("flat_eq").toString(),CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,list.get(i).get("valley_eq").toString(),CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,list.get(i).get("sharp_eq").toString(),CBwcfF));
            		conut++;	
            		sheet.addCell(new Label( 1 , conut ,"逆变器名称",CBwcfF));
            		sheet.addCell(new Label( 2 , conut ,"逆变器所接组件总功率（kW）",CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,"设备最高交流功率（kW）",CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,"设备最高温度（℃）",CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,"平均效率（%）",CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,"日电量（kWh）",CBwcfF));
            		conut++;	
            		sheet.addCell(new Label( 1 , conut ,list.get(i).get("itname").toString(),CBwcfF));
            		sheet.addCell(new Label( 2 , conut ,list.get(i).get("inp").toString(),CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,list.get(i).get("max_jlp").toString(),CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,list.get(i).get("max_temperature").toString(),CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,list.get(i).get("avg_itpower").toString(),CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,list.get(i).get("nbqtotal_eq").toString(),CBwcfF));
            		sheet.mergeCells(1, h, 1, h+1);
            		}else{
        			conut++;	
        			sheet.addCell(new Label( 1 , conut ,list.get(i).get("itname").toString(),CBwcfF));
            		sheet.addCell(new Label( 2 , conut ,list.get(i).get("inp").toString(),CBwcfF));
            		sheet.addCell(new Label( 3 , conut ,list.get(i).get("max_jlp").toString(),CBwcfF));
            		sheet.addCell(new Label( 4 , conut ,list.get(i).get("max_temperature").toString(),CBwcfF));
            		sheet.addCell(new Label( 5 , conut ,list.get(i).get("avg_itpower").toString(),CBwcfF));
            		sheet.addCell(new Label( 6 , conut ,list.get(i).get("nbqtotal_eq").toString(),CBwcfF));
            		}
            		if(j==0){
            			j=h;
            		}else{
            			if(j!=h){
            				System.out.println("j: "+j+" d:"+conut);
            				sheet.mergeCells(0, j, 0, d);
            				j=h;
            			}else{
            				d=conut;
            			}
            		}
         
                }
            	sheet.mergeCells(0, h, 0, conut);
            	System.out.println("h: "+h+" conut:"+conut);
            	sheet.addCell( new Label( 0 , conut+1 , "三、运行情况说明",CBwcfF));
            	sheet.addCell( new Label( 0 , conut+2 , (String)list2.get(0).get("run_status_descrip"),CBwcfF2));
            	sheet.mergeCells(0,conut+1, 6, conut+1);
            	sheet.mergeCells(0,conut+2, 6, conut+3);
            book.write();
            book.close();
            //-------------------------------------------------------------------------------------
        }catch (Exception e) {

            logger.error("发生异常"+e.getMessage(),e);
        }
       
	   return listfile;
	}
	
	
	//文件打包下载
    public HttpServletResponse downLoadFiles(List<File> files, HttpServletResponse response)
            throws Exception {
        try {
            /**这个集合就是你想要打包的所有文件，
             * 这里假设已经准备好了所要打包的文件*/
            //List<File> files = new ArrayList<File>();
     
            /**创建一个临时压缩文件，
             * 我们会把文件流全部注入到这个文件中
             * 这里的文件你可以自定义是.rar还是.zip*/
        	//String fileNames="报表.xls";
           // String name=new String(fileNames.getBytes("UTF-8"),"ISO8859-1");
          String fileName =PropertiesUtil.getString("download_temp_path")+ "/报表.zip";    		
  		  logger.debug("export excel path:"+fileName);
            File file = new File(fileName);
            if (!file.exists()){   
                file.createNewFile();   
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);   
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
           ZipOutputStream zipOut 
            = new ZipOutputStream(fous);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream*/
           zipFile(files, zipOut);
            zipOut.close();
            fous.close();
           return downloadZip(file,response);
        }catch (Exception e) {
                e.printStackTrace();
                logger.error("发生异常"+e.getMessage(),e);
            }
            /**直到文件的打包已经成功了，
             * 文件的打包过程被我封装在FileUtil.zipFile这个静态方法中，
             * 稍后会呈现出来，接下来的就是往客户端写数据了*/
           
        for(int i=0;i<files.size();i++){
        	files.get(i).delete();
        }
        return response ;
    }

  /**
     * 把接受的全部文件打成压缩包 
     * @param List<File>;  
     * @param org.apache.tools.zip.ZipOutputStream  
     */
    public static void zipFile
            (List files,ZipOutputStream outputStream) {
        int size = files.size();
        for(int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
    }

    public static HttpServletResponse downloadZip(File file,HttpServletResponse response) {
        try {
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();

        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");

//如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(file.getName(), "UTF-8"));
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        } catch (IOException e) {
        e.printStackTrace();

        logger.error("发生异常"+e.getMessage(),e);
        }finally{
             try {
                    File f = new File(file.getPath());
                    f.delete();
                } catch (Exception e) {
                    e.printStackTrace();

                    logger.error("发生异常"+e.getMessage(),e);
                }
        }
        
        return response;
    }

/**  
     * 根据输入的文件与输出流对文件进行打包
     * @param File
     * @param org.apache.tools.zip.ZipOutputStream
     */
    public static void zipFile(File inputFile,
            ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中*/
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据   
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象   
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        logger.error("发生异常"+e.getMessage(),e);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            logger.error("发生异常"+e.getMessage(),e);
        }
    }

    
    
    /**
	 * 投资方报表下载
	 * @param response
	 * @param type
	 * @param list  内容
	 * @param list2   表头
	 * @throws Exception
	 */
	
	public void tzflExcel(HttpServletResponse response,String em,List<Map<String,Object>> list,String tableName,String type,String tableTime,String tableTitle) throws Exception{
		//MCTIME=20160902 18:54, inp=0, dgencap=107, itp=0, DATATIME=1472813699, ittemp=33.3, pofactor=0
		
		//[{run_status_descrip=, irradiation=1, max_irr_time=, max_windspeed=, max_irr_intensity=0, name=test1, max_component_temp=, create_time=2016-10-19 13:12:25.0, max_temperature=45}]
	    String fileName = PropertiesUtil.getString("download_temp_path")+"/daytable.xls";   		
		 logger.debug("export excel path:"+fileName);
	    try {
             File fileit=new File(fileName);
             fileit.setWritable(true, false);
             String fileNameit=fileit.getPath();
             System.out.println(fileNameit);
//			打开文件
            WritableWorkbook book = Workbook.createWorkbook( fileit);
//			生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
            SheetSettings ss = sheet.getSettings();
            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
            jxl.write.WritableCellFormat CBwcfF2=new jxl.write.WritableCellFormat(Bwf);
            //设置表格格式
            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);    
            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
          //  CBwcfF2.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF2.setAlignment(jxl.format.Alignment.LEFT);
            CBwcfF2.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);
            CBwcfF2.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            CellView cellView=new CellView();
            cellView.setAutosize(true);
            sheet.setColumnView(0, 30);
            sheet.setColumnView(1, 30);
            sheet.setColumnView(2, 30);
            sheet.setColumnView(3, 30);
            sheet.setColumnView(4, 30);
            sheet.setColumnView(5, 30);
            sheet.setColumnView(6, 30);
            sheet.setColumnView(7, 30);
            sheet.setColumnView(8, 30);
            sheet.setColumnView(9, 30);
            sheet.setColumnView(10, 30);
            sheet.setColumnView(11, 30);
            sheet.setColumnView(12, 30);
            sheet.setColumnView(13, 30);
            sheet.addCell( new Label( 0 , 0 ,tableTitle,CBwcfF));
            sheet.mergeCells(0, 0, 13,0);
            sheet.addCell( new Label( 0 , 1 , "报表名称",CBwcfF));
            sheet.mergeCells(0, 1, 1,1);
            sheet.addCell( new Label( 2 , 1 ,tableName,CBwcfF));
            sheet.mergeCells(2, 1, 4,1);
            sheet.addCell( new Label( 5 , 1 , "报表类型",CBwcfF));
            sheet.mergeCells(5, 1, 6,1);
            sheet.addCell( new Label( 7 , 1 , type,CBwcfF));
            sheet.mergeCells(7, 1, 8,1);
            sheet.addCell( new Label( 9 , 1 , "生成时间 ",CBwcfF));
            sheet.mergeCells(9, 1, 10,1);
            sheet.addCell( new Label( 11 , 1 , tableTime,CBwcfF));
            sheet.mergeCells(11, 1, 13,1);
            sheet.addCell( new Label( 0 , 2 , "电站名称 ",CBwcfF));
            sheet.mergeCells(0, 2, 1,2);
            sheet.addCell( new Label( 2 , 2 , "装机容量(kW)",CBwcfF));
            sheet.addCell( new Label( 3 , 2 , "辐照度(kWh)",CBwcfF));
            sheet.addCell( new Label( 4 , 2 , "发点电量(kWh)",CBwcfF));
            sheet.addCell( new Label( 5 , 2 , "上网电量(kWh)",CBwcfF));
            sheet.addCell( new Label( 6 , 2 , "自用电量(kWh)",CBwcfF));
            sheet.addCell( new Label( 7 , 2 , "自用电比率(%)",CBwcfF));
            sheet.addCell( new Label( 8 , 2 , "故障停机损失电量(kWh)",CBwcfF));
            sheet.addCell( new Label( 9 , 2 , "故障停机损失电量比率(%)",CBwcfF));
            sheet.addCell( new Label( 10 , 2 , "补贴收益(元)",CBwcfF));
            sheet.addCell( new Label( 11 , 2 , "上网收益(元)",CBwcfF));
            sheet.addCell( new Label( 12 , 2 , "自用收益(元)",CBwcfF));
            sheet.addCell( new Label( 13 , 2 , "总收益(元)",CBwcfF));
            for(int i=0;i<list.size();i++){
            	 sheet.addCell( new Label( 0 , i+3 , (String)(list.get(i).get("station_name")),CBwcfF));
                 sheet.mergeCells(0, i+3, 1,i+3);
                 sheet.addCell( new Label( 2 , i+3 , (String)(list.get(i).get("installed_capacity")),CBwcfF));
                 sheet.addCell( new Label( 3 , i+3 ,(String)(list.get(i).get("RadiantExposure")),CBwcfF));
                 sheet.addCell( new Label( 4 , i+3 , (String)(list.get(i).get("Generation")),CBwcfF));
                 sheet.addCell( new Label( 5 , i+3 , (String)(list.get(i).get("GenerationOnLine")),CBwcfF));
                 sheet.addCell( new Label( 6 , i+3 , (String)(list.get(i).get("GenerationSelfUs")),CBwcfF));
                 sheet.addCell( new Label( 7 , i+3 , (String)(list.get(i).get("GenerationSelfUsPersent")),CBwcfF));
                 sheet.addCell( new Label( 8 , i+3 , (String)(list.get(i).get("GenerationLosed")),CBwcfF));
                 sheet.addCell( new Label( 9 , i+3 , (String)(list.get(i).get("GenerationLosedPersent")),CBwcfF));
                 sheet.addCell( new Label( 10 , i+3 ,(String)(list.get(i).get("SubsidyIncome")),CBwcfF));
                 sheet.addCell( new Label( 11 , i+3 , (String)(list.get(i).get("OnLineIncome")),CBwcfF));
                 sheet.addCell( new Label( 12 , i+3 ,(String)(list.get(i).get("SelfUsIncome")),CBwcfF));
                 sheet.addCell( new Label( 13 , i+3 , (String)(list.get(i).get("TotalIncome")),CBwcfF));
            }
            DecimalFormat df = new DecimalFormat("#.00");
          
            book.write();
            book.close();
            //-------------------------------------------------------------------------------------
            OutputStream os = null;
            InputStream in = null;
         //   String tablename=((String)list2.get(0).get("create_time")).substring(0,10);
            String fileNames=tableName+".xls";
            String name=new String(fileNames.getBytes("UTF-8"),"ISO8859-1");
         //   String tablenamename=tablename+name;
            response.reset();
            response.setContentType("application/x-msdownload;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename="+name);
            try {
                os = response.getOutputStream();
                in = new FileInputStream(fileName);
                byte[] buffer = new byte[1024];
                int i = -1;
                while ((i = in.read(buffer)) != -1) {
                	os.write(buffer, 0, i);
                }
                os.flush();
                
                os.close();
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("os is null");

                logger.error("发生异常"+e.getMessage(),e);
            } finally {
            	fileit.delete();
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (os != null) {
                    os.flush();
                    os.close();
                    os = null;
                }
         
            }
            //-------------------------------------------------------------------------------------
        } catch (Exception e) {
        	System.out.println(e);

            logger.error("发生异常"+e.getMessage(),e);
        }
       
	
	}
	
	
	
	public List<File> tzflallExcel(HttpServletResponse response,String em,List<Map<String,Object>> list,String tableName,String type,String tableTime,String tableTitle,List<File> listfile) throws Exception{
		//MCTIME=20160902 18:54, inp=0, dgencap=107, itp=0, DATATIME=1472813699, ittemp=33.3, pofactor=0
		
		//[{run_status_descrip=, irradiation=1, max_irr_time=, max_windspeed=, max_irr_intensity=0, name=test1, max_component_temp=, create_time=2016-10-19 13:12:25.0, max_temperature=45}]
	    String fileName =  PropertiesUtil.getString("download_temp_path")+"/"+ tableName+".xls"; 		
		 logger.debug("export excel path:"+fileName);
        try {
             File fileit=new File(fileName);
             listfile.add(fileit);
             fileit.setWritable(true, false);
             String fileNameit=fileit.getPath();
             System.out.println(fileNameit);
//			打开文件
            WritableWorkbook book = Workbook.createWorkbook( fileit);
//			生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
            SheetSettings ss = sheet.getSettings();
            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
            jxl.write.WritableCellFormat CBwcfF2=new jxl.write.WritableCellFormat(Bwf);
            //设置表格格式
            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);    
            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
          //  CBwcfF2.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF2.setAlignment(jxl.format.Alignment.LEFT);
            CBwcfF2.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);
            CBwcfF2.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            CellView cellView=new CellView();
            cellView.setAutosize(true);
            sheet.setColumnView(0, 30);
            sheet.setColumnView(1, 30);
            sheet.setColumnView(2, 30);
            sheet.setColumnView(3, 30);
            sheet.setColumnView(4, 30);
            sheet.setColumnView(5, 30);
            sheet.setColumnView(6, 30);
            sheet.setColumnView(7, 30);
            sheet.setColumnView(8, 30);
            sheet.setColumnView(9, 30);
            sheet.setColumnView(10, 30);
            sheet.setColumnView(11, 30);
            sheet.setColumnView(12, 30);
            sheet.setColumnView(13, 30);
            sheet.addCell( new Label( 0 , 0 ,tableTitle,CBwcfF));
            sheet.mergeCells(0, 0, 13,0);
            sheet.addCell( new Label( 0 , 1 , "报表名称",CBwcfF));
            sheet.mergeCells(0, 1, 1,1);
            sheet.addCell( new Label( 2 , 1 ,tableName,CBwcfF));
            sheet.mergeCells(2, 1, 4,1);
            sheet.addCell( new Label( 5 , 1 , "报表类型",CBwcfF));
            sheet.mergeCells(5, 1, 6,1);
            sheet.addCell( new Label( 7 , 1 , type,CBwcfF));
            sheet.mergeCells(7, 1, 8,1);
            sheet.addCell( new Label( 9 , 1 , "生成时间 ",CBwcfF));
            sheet.mergeCells(9, 1, 10,1);
            sheet.addCell( new Label( 11 , 1 , tableTime,CBwcfF));
            sheet.mergeCells(11, 1, 13,1);
            sheet.addCell( new Label( 0 , 2 , "电站名称 ",CBwcfF));
            sheet.mergeCells(0, 2, 1,2);
            sheet.addCell( new Label( 2 , 2 , "装机容量(kW)",CBwcfF));
            sheet.addCell( new Label( 3 , 2 , "辐照度(kWh)",CBwcfF));
            sheet.addCell( new Label( 4 , 2 , "发点电量(kWh)",CBwcfF));
            sheet.addCell( new Label( 5 , 2 , "上网电量(kWh)",CBwcfF));
            sheet.addCell( new Label( 6 , 2 , "自用电量(kWh)",CBwcfF));
            sheet.addCell( new Label( 7 , 2 , "自用电比率(%)",CBwcfF));
            sheet.addCell( new Label( 8 , 2 , "故障停机损失电量(kWh)",CBwcfF));
            sheet.addCell( new Label( 9 , 2 , "故障停机损失电量比率(%)",CBwcfF));
            sheet.addCell( new Label( 10 , 2 , "补贴收益(元)",CBwcfF));
            sheet.addCell( new Label( 11 , 2 , "上网收益(元)",CBwcfF));
            sheet.addCell( new Label( 12 , 2 , "自用收益(元)",CBwcfF));
            sheet.addCell( new Label( 13 , 2 , "总收益(元)",CBwcfF));
            for(int i=0;i<list.size();i++){
            	 sheet.addCell( new Label( 0 , i+3 , (String)(list.get(i).get("station_name")),CBwcfF));
                 sheet.mergeCells(0, i+3, 1,i+3);
                 sheet.addCell( new Label( 2 , i+3 , (String)(list.get(i).get("installed_capacity")),CBwcfF));
                 sheet.addCell( new Label( 3 , i+3 ,(String)(list.get(i).get("RadiantExposure")),CBwcfF));
                 sheet.addCell( new Label( 4 , i+3 , (String)(list.get(i).get("Generation")),CBwcfF));
                 sheet.addCell( new Label( 5 , i+3 , (String)(list.get(i).get("GenerationOnLine")),CBwcfF));
                 sheet.addCell( new Label( 6 , i+3 , (String)(list.get(i).get("GenerationSelfUs")),CBwcfF));
                 sheet.addCell( new Label( 7 , i+3 , (String)(list.get(i).get("GenerationSelfUsPersent")),CBwcfF));
                 sheet.addCell( new Label( 8 , i+3 , (String)(list.get(i).get("GenerationLosed")),CBwcfF));
                 sheet.addCell( new Label( 9 , i+3 , (String)(list.get(i).get("GenerationLosedPersent")),CBwcfF));
                 sheet.addCell( new Label( 10 , i+3 ,(String)(list.get(i).get("SubsidyIncome")),CBwcfF));
                 sheet.addCell( new Label( 11 , i+3 , (String)(list.get(i).get("OnLineIncome")),CBwcfF));
                 sheet.addCell( new Label( 12 , i+3 ,(String)(list.get(i).get("SelfUsIncome")),CBwcfF));
                 sheet.addCell( new Label( 13 , i+3 , (String)(list.get(i).get("TotalIncome")),CBwcfF));
            }
            DecimalFormat df = new DecimalFormat("#.00");
          
            book.write();
            book.close();
   
        } catch (Exception e) {
        	System.out.println(e);

            logger.error("发生异常"+e.getMessage(),e);
        }
       
	     return listfile;
	}
	
	
	
	
	public void ownerExcel(HttpServletResponse response,String em,List<Map<String,Object>> list,String tableName,String type,String tableTime,String tableTitle) throws Exception{
		//MCTIME=20160902 18:54, inp=0, dgencap=107, itp=0, DATATIME=1472813699, ittemp=33.3, pofactor=0
		
		//[{run_status_descrip=, irradiation=1, max_irr_time=, max_windspeed=, max_irr_intensity=0, name=test1, max_component_temp=, create_time=2016-10-19 13:12:25.0, max_temperature=45}]
	    String fileName = PropertiesUtil.getString("download_temp_path")+"/"+"daytable.xls"; 	
	 	logger.debug("export excel path:"+fileName);
        try {
             File fileit=new File(fileName);
             fileit.setWritable(true, false);
             String fileNameit=fileit.getPath();
             System.out.println(fileNameit);
//			打开文件
            WritableWorkbook book = Workbook.createWorkbook( fileit);
//			生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
            SheetSettings ss = sheet.getSettings();
            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
            jxl.write.WritableCellFormat CBwcfF2=new jxl.write.WritableCellFormat(Bwf);
            //设置表格格式
            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);    
            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
          //  CBwcfF2.setAlignment(jxl.write.Alignment.CENTRE);
            CBwcfF2.setAlignment(jxl.format.Alignment.LEFT);
            CBwcfF2.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);
            CBwcfF2.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
            CBwcfF2.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
            CellView cellView=new CellView();
            cellView.setAutosize(true);
            sheet.setColumnView(0, 15);
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 15);
            sheet.setColumnView(4, 15);
            sheet.setColumnView(5, 15);
            sheet.setColumnView(6, 15);
            sheet.setColumnView(7, 15);
            sheet.setColumnView(8, 15);
            sheet.setColumnView(9, 15);
            sheet.setColumnView(10, 15);
            sheet.setColumnView(11, 15);
            sheet.setColumnView(12, 15);
            sheet.setColumnView(13, 15);
            sheet.setColumnView(14, 15);
            sheet.setColumnView(15, 15);
            sheet.setColumnView(16, 15);
            sheet.setColumnView(17, 15);
            sheet.setColumnView(18, 15);
            sheet.setColumnView(19, 15);
            sheet.setColumnView(20, 15);
            sheet.setColumnView(21, 15);
            sheet.setColumnView(22, 15);
            sheet.setColumnView(23, 15);
            sheet.setColumnView(24, 15);
            sheet.addCell( new Label( 0 , 0 ,tableTitle,CBwcfF));
            sheet.mergeCells(0, 0, 24,0);
            sheet.addCell( new Label( 0 , 1 , "报表名称",CBwcfF));
            sheet.mergeCells(0, 1, 4,1);
            sheet.addCell( new Label( 5 , 1 ,tableName,CBwcfF));
            sheet.mergeCells(5, 1, 8,1);
            sheet.addCell( new Label( 9 , 1 , "报表类型",CBwcfF));
            sheet.mergeCells(9, 1, 12,1);
            sheet.addCell( new Label( 13 , 1 , type,CBwcfF));
            sheet.mergeCells(13,1, 16,1);
            sheet.addCell( new Label( 17 , 1 , "生成时间 ",CBwcfF));
            sheet.mergeCells(17, 1, 20,1);
            sheet.addCell( new Label( 21 , 1 , tableTime,CBwcfF));
            sheet.mergeCells(21, 1, 24,1);
            sheet.addCell( new Label( 0 , 2 , "日期",CBwcfF));
            sheet.mergeCells(0, 2, 4,2);
            sheet.addCell( new Label( 5 , 2 , "总用电量（kWh）",CBwcfF));
            sheet.mergeCells(5, 2, 9,2);
            sheet.addCell( new Label( 10 , 2 , "使用光伏电站电量（kWh）",CBwcfF));
            sheet.mergeCells(10, 2, 14,2);
            sheet.addCell( new Label( 15 , 2 , "使用电网电量（kWh）",CBwcfF));
            sheet.mergeCells(15, 2, 19,2);
            sheet.addCell( new Label( 20 , 2 , "节省电量（kWh）",CBwcfF));
            sheet.mergeCells(20, 2, 24,2);
            Double GenerationAllUseall=0.0;
            Double GenerationSelfUseall=0.0;
            Double GenerationUseOnLineall=0.0;
            Double SaveCastall=0.0;
            DecimalFormat df = new DecimalFormat("#.00");
            for(int i=0;i<list.size();i++){
            	if(type.equals("月报表")){
            		sheet.addCell( new Label( 0 , i+3 , ((String)(list.get(i).get("createtime"))).substring(8, 10)+"日",CBwcfF));
            	}else{
            		sheet.addCell( new Label( 0 , i+3 ,((String)(list.get(i).get("createtime"))).substring(5, 7)+"月",CBwcfF));
            	}
           	    
                sheet.mergeCells(0, i+3, 4,i+3);
                sheet.addCell( new Label( 5 , i+3 , (String)(list.get(i).get("GenerationAllUse")),CBwcfF));
                sheet.mergeCells(5, i+3, 9,i+3);
                GenerationAllUseall+=Double.parseDouble((String)(list.get(i).get("GenerationAllUse")));
                sheet.addCell( new Label( 10 , i+3 ,(String)(list.get(i).get("GenerationSelfUse")),CBwcfF));
                sheet.mergeCells(10, i+3, 14,i+3);
                GenerationSelfUseall+=Double.parseDouble((String)(list.get(i).get("GenerationSelfUse")));
                sheet.addCell( new Label( 15 , i+3 , (String)(list.get(i).get("GenerationUseOnLine")),CBwcfF));
                sheet.mergeCells(15, i+3, 19,i+3);
                GenerationUseOnLineall+=Double.parseDouble((String)(list.get(i).get("GenerationUseOnLine")));
                sheet.addCell( new Label( 20 , i+3 , (String)(list.get(i).get("SaveCast")),CBwcfF));
                sheet.mergeCells(20, i+3, 24,i+3);
                SaveCastall+=Double.parseDouble((String)(list.get(i).get("SaveCast")));
           }
            
            sheet.addCell( new Label( 0 , list.size()+3 , "合计",CBwcfF));
            sheet.mergeCells(0, list.size()+3, 4,list.size()+3);
            sheet.addCell( new Label( 5 , list.size()+3 , df.format(GenerationAllUseall)+"",CBwcfF));
            sheet.mergeCells(5, list.size()+3, 9,list.size()+3);
            sheet.addCell( new Label( 10 , list.size()+3 , df.format(GenerationSelfUseall) +"",CBwcfF));
            sheet.mergeCells(10, list.size()+3, 14,list.size()+3);
            sheet.addCell( new Label( 15 , list.size()+3 ,  df.format(GenerationUseOnLineall)+"",CBwcfF));
            sheet.mergeCells(15, list.size()+3, 19,list.size()+3);
            sheet.addCell( new Label( 20 , list.size()+3 , df.format(SaveCastall) +"",CBwcfF));
            sheet.mergeCells(20, list.size()+3, 24,list.size()+3);
            
          
            book.write();
            book.close();
            //-------------------------------------------------------------------------------------
            OutputStream os = null;
            InputStream in = null;
         //   String tablename=((String)list2.get(0).get("create_time")).substring(0,10);
            String fileNames=tableName+".xls";
            String name=new String(fileNames.getBytes("UTF-8"),"ISO8859-1");
         //   String tablenamename=tablename+name;
            response.reset();
            response.setContentType("application/x-msdownload;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename="+name);
            try {
                os = response.getOutputStream();
                in = new FileInputStream(fileName);
                byte[] buffer = new byte[1024];
                int i = -1;
                while ((i = in.read(buffer)) != -1) {
                	os.write(buffer, 0, i);
                }
                os.flush();
                
                os.close();
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("os is null");

                logger.error("发生异常"+e.getMessage(),e);
            } finally {
            	fileit.delete();
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (os != null) {
                    os.flush();
                    os.close();
                    os = null;
                }
         
            }
            //-------------------------------------------------------------------------------------
        } catch (Exception e) {
        	System.out.println(e);

            logger.error("发生异常"+e.getMessage(),e);
        }
       
	
	}
	
	public List<File> ownerallExcel(HttpServletResponse response,String em,List<Map<String,Object>> list,String tableName,String type,String tableTime,String tableTitle,List<File> listfile) throws Exception{
		
		 String fileName =  PropertiesUtil.getString("download_temp_path")+"/"+ tableName+".xls"; 
		 logger.debug("export excel path:"+fileName);
		 	
	        try {
	             File fileit=new File(fileName);
	             listfile.add(fileit);
	             fileit.setWritable(true, false);
	             String fileNameit=fileit.getPath();
	             System.out.println(fileNameit);
//				打开文件
	            WritableWorkbook book = Workbook.createWorkbook( fileit);
//				生成名为“第一页”的工作表，参数0表示这是第一页
	            WritableSheet sheet = book.createSheet( " 第一页 " , 0 );
	            SheetSettings ss = sheet.getSettings();
	            WritableFont Bwf=new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false);
	            jxl.write.WritableCellFormat CBwcfF=new jxl.write.WritableCellFormat(Bwf);
	            jxl.write.WritableCellFormat CBwcfF2=new jxl.write.WritableCellFormat(Bwf);
	            //设置表格格式
	            CBwcfF.setAlignment(jxl.write.Alignment.CENTRE);
	            CBwcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);    
	            CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
	            CBwcfF.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
	            CBwcfF.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
	            CBwcfF.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
	          //  CBwcfF2.setAlignment(jxl.write.Alignment.CENTRE);
	            CBwcfF2.setAlignment(jxl.format.Alignment.LEFT);
	            CBwcfF2.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);
	            CBwcfF2.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
	            CBwcfF2.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.MEDIUM);
	            CBwcfF2.setBorder(jxl.format.Border.LEFT, BorderLineStyle.MEDIUM);
	            CBwcfF2.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);
	            CellView cellView=new CellView();
	            cellView.setAutosize(true);
	            sheet.setColumnView(0, 15);
	            sheet.setColumnView(1, 15);
	            sheet.setColumnView(2, 15);
	            sheet.setColumnView(3, 15);
	            sheet.setColumnView(4, 15);
	            sheet.setColumnView(5, 15);
	            sheet.setColumnView(6, 15);
	            sheet.setColumnView(7, 15);
	            sheet.setColumnView(8, 15);
	            sheet.setColumnView(9, 15);
	            sheet.setColumnView(10, 15);
	            sheet.setColumnView(11, 15);
	            sheet.setColumnView(12, 15);
	            sheet.setColumnView(13, 15);
	            sheet.setColumnView(14, 15);
	            sheet.setColumnView(15, 15);
	            sheet.setColumnView(16, 15);
	            sheet.setColumnView(17, 15);
	            sheet.setColumnView(18, 15);
	            sheet.setColumnView(19, 15);
	            sheet.setColumnView(20, 15);
	            sheet.setColumnView(21, 15);
	            sheet.setColumnView(22, 15);
	            sheet.setColumnView(23, 15);
	            sheet.setColumnView(24, 15);
	            sheet.addCell( new Label( 0 , 0 ,tableTitle,CBwcfF));
	            sheet.mergeCells(0, 0, 24,0);
	            sheet.addCell( new Label( 0 , 1 , "报表名称",CBwcfF));
	            sheet.mergeCells(0, 1, 4,1);
	            sheet.addCell( new Label( 5 , 1 ,tableName,CBwcfF));
	            sheet.mergeCells(5, 1, 8,1);
	            sheet.addCell( new Label( 9 , 1 , "报表类型",CBwcfF));
	            sheet.mergeCells(9, 1, 12,1);
	            sheet.addCell( new Label( 13 , 1 , type,CBwcfF));
	            sheet.mergeCells(13,1, 16,1);
	            sheet.addCell( new Label( 17 , 1 , "生成时间 ",CBwcfF));
	            sheet.mergeCells(17, 1, 20,1);
	            sheet.addCell( new Label( 21 , 1 , tableTime,CBwcfF));
	            sheet.mergeCells(21, 1, 24,1);
	            sheet.addCell( new Label( 0 , 2 , "日期",CBwcfF));
	            sheet.mergeCells(0, 2, 4,2);
	            sheet.addCell( new Label( 5 , 2 , "总用电量（kWh）",CBwcfF));
	            sheet.mergeCells(5, 2, 9,2);
	            sheet.addCell( new Label( 10 , 2 , "使用光伏电站电量（kWh）",CBwcfF));
	            sheet.mergeCells(10, 2, 14,2);
	            sheet.addCell( new Label( 15 , 2 , "使用电网电量（kWh）",CBwcfF));
	            sheet.mergeCells(15, 2, 19,2);
	            sheet.addCell( new Label( 20 , 2 , "节省电量（kWh）",CBwcfF));
	            sheet.mergeCells(20, 2, 24,2);
	            Double GenerationAllUseall=0.0;
	            Double GenerationSelfUseall=0.0;
	            Double GenerationUseOnLineall=0.0;
	            Double SaveCastall=0.0;
	            DecimalFormat df = new DecimalFormat("#.00");
	            for(int i=0;i<list.size();i++){
	            	if(type.equals("月报表")){
	            		sheet.addCell( new Label( 0 , i+3 , ((String)(list.get(i).get("createtime"))).substring(8, 10)+"日",CBwcfF));
	            	}else{
	            		sheet.addCell( new Label( 0 , i+3 ,((String)(list.get(i).get("createtime"))).substring(5, 7)+"月",CBwcfF));
	            	}
	           	    
	                sheet.mergeCells(0, i+3, 4,i+3);
	                sheet.addCell( new Label( 5 , i+3 , (String)(list.get(i).get("GenerationAllUse")),CBwcfF));
	                sheet.mergeCells(5, i+3, 9,i+3);
	                GenerationAllUseall+=Float.parseFloat((String)(list.get(i).get("GenerationAllUse")));
	                sheet.addCell( new Label( 10 , i+3 ,(String)(list.get(i).get("GenerationSelfUse")),CBwcfF));
	                sheet.mergeCells(10, i+3, 14,i+3);
	                GenerationSelfUseall+=Double.parseDouble((String)(list.get(i).get("GenerationSelfUse")));
	                sheet.addCell( new Label( 15 , i+3 , (String)(list.get(i).get("GenerationUseOnLine")),CBwcfF));
	                sheet.mergeCells(15, i+3, 19,i+3);
	                GenerationUseOnLineall+=Double.parseDouble((String)(list.get(i).get("GenerationUseOnLine")));
	                sheet.addCell( new Label( 20 , i+3 , (String)(list.get(i).get("SaveCast")),CBwcfF));
	                sheet.mergeCells(20, i+3, 24,i+3);
	                SaveCastall+=Double.parseDouble((String)(list.get(i).get("SaveCast")));
	           }
	            
	            sheet.addCell( new Label( 0 , list.size()+3 , "合计",CBwcfF));
	            sheet.mergeCells(0, list.size()+3, 4,list.size()+3);
	            sheet.addCell( new Label( 5 , list.size()+3 , df.format(GenerationAllUseall)+"",CBwcfF));
	            sheet.mergeCells(5, list.size()+3, 9,list.size()+3);
	            sheet.addCell( new Label( 10 , list.size()+3 , df.format(GenerationSelfUseall)+"",CBwcfF));
	            sheet.mergeCells(10, list.size()+3, 14,list.size()+3);
	            sheet.addCell( new Label( 15 , list.size()+3 , df.format(GenerationUseOnLineall)+"",CBwcfF));
	            sheet.mergeCells(15, list.size()+3, 19,list.size()+3);
	            sheet.addCell( new Label( 20 , list.size()+3 , df.format(SaveCastall)+"",CBwcfF));
	            sheet.mergeCells(20, list.size()+3, 24,list.size()+3);
	            
	          
	            book.write();
	            book.close();
	        
	            //-------------------------------------------------------------------------------------
	        } catch (Exception e) {
	        	System.out.println(e);

                logger.error("发生异常"+e.getMessage(),e);
	        }
		
		       return  listfile;
		
	}
	
	
	
}



/*  */



