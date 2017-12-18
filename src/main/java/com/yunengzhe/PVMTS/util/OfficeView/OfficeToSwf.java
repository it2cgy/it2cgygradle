package com.yunengzhe.PVMTS.util.OfficeView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.yunengzhe.commons.util.PropertiesUtil;

public class OfficeToSwf {

	private static final int environment = PropertiesUtil.getInt("environment");	//环境 1：windows 2:linux
	private String sourceFile; 	//(只涉及pdf2swf路径问题)
	//private String destFilePath;//转换后的存储的路径
	//private String outputPath;	//输入路径 ，如果不设置就输出在默认的位置
	private String fileName;
	private File pdfFile;		//输入文件 ，如果不设置就输出在默认的位置
	private File swfFile;		//输入文件 ，如果不设置就输出在默认的位置
	//private File officeFile;
	
	/**
	 * 利用构造函数初始化
	 */
	public OfficeToSwf(String sourceFile, String destFilePath) {
		System.out.println("sourceFile  >>>>>  " + sourceFile + "   " + "destFile  >>>> " + destFilePath);
		//init(sourceFile, destFilePath);
		this.sourceFile = sourceFile;
		//this.destFilePath = destFilePath;
		this.fileName = sourceFile.substring(sourceFile.lastIndexOf("/") + 1, sourceFile.lastIndexOf("."));
		//this.officeFile = new File(sourceFile);
		this.pdfFile = new File(destFilePath + "/" + fileName + ".pdf");
		this.swfFile = new File(destFilePath + "/" + fileName + ".swf");
	}
	
	public OfficeToSwf(String sourceFile) {    
		System.out.println("sourceFile  >>>>>  " + sourceFile);
		//init(sourceFile, destFilePath);
		this.sourceFile = sourceFile;
		//this.destFilePath = destFilePath;
		this.fileName = sourceFile.substring(0, sourceFile.lastIndexOf("."));
		//this.officeFile = new File(sourceFile);
		this.pdfFile = new File(fileName + ".pdf");
		this.swfFile = new File(fileName + ".swf");
	}
	
	/*private void init(String sourceFile, String destFile) {
		this.fileString = fileString;
		fileName = fileString.substring(0, fileString.lastIndexOf("."));
		officeFile = new File(fileString);
		pdfFile = new File(fileName + ".pdf");
		swfFile = new File(fileName + ".swf");
	}*/
	
	
	/**
	 * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为
	 * http://www.openoffice.org/
	 * 
	 * <pre>
	 * 方法示例:
	 * String sourcePath = "F:\\office\\source.doc";
	 * String destFile = "F:\\pdf\\dest.pdf";
	 * Converter.office2PDF(sourcePath, destFile);
	 * </pre>
	 * 
	 * @param sourceFile
	 *            源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc,
	 *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc
	 * @param outputFile
	 *            目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf
	 * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0,
	 *         则表示操作成功; 返回1, 则表示转换失败
	 */
	public int office2PDF() {
		try {
			String fileExtName = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
			if(fileExtName.equals("pdf")){
				System.out.println("//////////////////////");
				if (!pdfFile.getParentFile().exists()) {
					//System.out.println("true");
					pdfFile.getParentFile().mkdirs();
					//System.out.println(pdfFile.exists());
				}
				
				FileInputStream input = new FileInputStream(sourceFile);
				FileOutputStream output = new FileOutputStream(pdfFile.getPath());
				
				byte buffer[] = new byte[1024];
				int len = 0;
				//循环将输入流中的内容读取到缓冲区当中
		        while((len = input.read(buffer)) > 0){
		            //输出缓冲区的内容到浏览器，实现文件下载
		        	output.write(buffer, 0, len);
		        }
		        //关闭文件输入流
		        input.close();
		        //关闭输出流
		        output.close();
		        System.out.println("//////////////////////");
				return 1;
			}
			
			File inputFile = new File(sourceFile);
			if (!inputFile.exists()) {
				System.out.println("false");
				return -1;// 找不到源文件, 则返回-1
			}

			// 如果目标路径不存在, 则新建该路径
			File outputFile = this.pdfFile;
			System.out.println("outputFile>>>>> " + outputFile.getPath());
			if (!outputFile.exists()) {
				outputFile.getParentFile().mkdirs();
			}

			//这个地方要写成一个单例
			/*String OpenOffice_HOME = Params.openOfficePath;//"C:\\Program Files (x86)\\OpenOffice 4";//这里是OpenOffice的安装目录, 在我的项目中,为了便于拓展接口,没有直接写成这个样子,但是这样是绝对没问题的
			// 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
			if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
				OpenOffice_HOME += "\\";
			}
			
			// 启动OpenOffice的服务
			String command = OpenOffice_HOME
					+ "program\\soffice.exe -headless -accept=\"socket,host=" + Params.host + ",port=" + Params.port + ";urp;\"";*/
			//System.out.println("连接路径：" + Params.command);
			//Process pro = Runtime.getRuntime().exec(Params.command);
			
			// connect to an OpenOffice.org instance running on port 8100
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(PropertiesUtil.getInt("openoffice_port"));
			connection.connect();

			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(
					connection);
			System.out.println("inputFile>>> " + inputFile.getPath());
			System.out.println("outputFile>>> " + outputFile.getPath());
			converter.convert(inputFile, outputFile);

			// close the connection
			//connection.disconnect();
			//System.out.println("isConnected》》 " + connection.isConnected());
			// 关闭OpenOffice服务的进程
			//pro.destroy();
			
			return 1;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	private File FileInputStream(String sourceFile2) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 转换成 swf
	 */
	private void pdf2Swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				if (environment == 1) {// windows环境处理
					System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
					try {
						Process p = r.exec("C:/Program Files (x86)/SWFTools/pdf2swf.exe " + pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9");
						
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.out.print(loadStream(p.getInputStream()));
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
						p.destroy();
					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				} else if (environment == 2) {// linux环境处理
					System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
					try {
						Process p = r.exec(PropertiesUtil.getString("lpdf2swf_path") + "pdf2swf " + pdfFile.getPath()
								+ " -o " + swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						System.err.println("****swf转换成功，文件输出："
								+ swfFile.getPath() + "****");
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
						p.destroy();
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else {
				System.out.println("****pdf不存在,无法转换****");
			}
		} else {
			System.out.println("****swf已经存在不需要转换****");
		}
	}

	static String loadStream(InputStream in) throws IOException {

		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();

		while ((ptr = in.read()) != -1) {
			buffer.append((char) ptr);
		}
		return buffer.toString();
	}
	
	/**
	 * 转换主方法
	 */
	public String conver() {
		
		if (swfFile.exists()) {
			System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
			String tempString = swfFile.getPath();
			return tempString;
		}
		if (environment == 1) {
			System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
		} else {
			System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
		}
		try {
			this.office2PDF();
			this.pdf2Swf();
			//doc2pdf();
			//pdf2swf();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			return tempString;
		} else {
			return null;
		}
	}

	/**
	 * 返回文件路径
	 */
	/*public String getSwfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}

	}*/
	/**
	 * 设置输出路径
	 */
	/*public void setOutputPath() {
		//this.outputPath = outputPath;
		if (!outputPath.equals("")) {
			String realName = fileName.substring(fileName.lastIndexOf("/"),
					fileName.lastIndexOf("."));
			if (outputPath.charAt(outputPath.length()) == '/') {
				swfFile = new File(outputPath + realName + ".swf");
			} else {
				swfFile = new File(outputPath + realName + ".swf");
			}
		}
	}*/
}
