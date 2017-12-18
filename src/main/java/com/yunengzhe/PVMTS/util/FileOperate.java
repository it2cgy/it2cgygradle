package com.yunengzhe.PVMTS.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;

public class FileOperate {
	//删除文件
	public static boolean removeFile(String url, String name) {
		File file = new File(url + "\\" + name);
		return file.delete();
	}
	//重新命名文件名
	public static boolean renameToFile(File old, File dest) {
		return old.renameTo(dest);
	}
	/*//移动文件
	public static boolean renameToFile(File old, File dest) {
		return old.renameTo(dest);
	}*/
	
	/**   
     *  新建目录   
     *  @param  folderPath  String  如  c:/fqf   
     *  @return  boolean   
     */    
	public static boolean newFolder(String folderPath)  {    
		try {    
			String filePath = folderPath;    
			filePath = filePath.toString();    
			File myFilePath = new File(filePath);    
			if(!myFilePath.exists())  {    
				myFilePath.mkdir();    
			}   
			return true;
		}    
		catch (Exception  e)  {    
           System.out.println("新建目录操作出错");    
           e.printStackTrace();  
           return false;
		}    
	}    
   
	/**   
     *  新建文件
     *  创建一个文件，把内容写到文件里面   
     *  @param filePathAndName String 文件路径及名称  如c:/fqf.txt   
     *  @param fileContent String  文件内容   
     *  @return boolean   
     */    
	public static boolean newFile(String filePathAndName, String fileContent) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString(); // 取的路径及文件名
			File file = new File(filePath);
			/** 如果文件不存在就建一个新文件 **/
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter resultFile = new FileWriter(file); 		// 用来写入字符文件的便捷类,
																// 在给出 File
																// 对象的情况下构造一个
																// FileWriter 对象
			PrintWriter myFile = new PrintWriter(resultFile);	// 向文本输出流，打印对象的格式化表示形式，使用指定文件创建不具有自动行刷新的新，PrintWriter。
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();
			myFile.close();
			return true;
		} catch (Exception e) {
			System.out.println("新建文件操作出错");
			e.printStackTrace();
			return false;
		}
	}
   
	/**
	 * 删除文件
	 * @param filePathAndName String 文件路径及名称 ，如c:/fqf.txt
	 * @return boolean
	 */
	public static boolean delFile(String filePathAndName) {
		try {
			//String filePath = filePathAndName;
			//filePath = filePath.toString();
			File delFile = new File(filePathAndName);
			delFile.delete();
			return true;
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除文件夹
	 * @param folderPath String 文件夹路径及名称，如c:/fqf
	 * @return boolean
	 */
	public static boolean delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			//String filePath = folderPath;
			//filePath = filePath.toString();
			File folder = new File(folderPath);
			folder.delete(); // 删除空文件夹
			return true;
		} catch (Exception e) {
			System.out.println("删除文件夹操作出错");
			e.printStackTrace();
			return false;
		}
	}
   
	/**
	 * 删除文件夹里面的所有文件
	 * @param path String 文件夹路径，如 c:/fqf
	 */
	public static boolean delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		//测试此抽象路径名表示的文件是否是一个目录
		if (!file.isDirectory()) {
			return false;
		}
		//返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				//System.out.println("path + tempList[i]>>> " + path + tempList[i]);
				temp = new File(path + tempList[i]);
			} else {
				//System.out.println("path + File.separator + tempList[i]>>> " + path + File.separator + tempList[i]);
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				boolean bool = temp.delete();
				if(!bool)
					return false;
			}
			if (temp.isDirectory()) {
				boolean bool1 = delAllFile(path + "/" + tempList[i]);	//先删除文件夹里面的文件
				if(!bool1)
					return false;
				boolean bool2 = delFolder(path + "/" + tempList[i]);	//再删除空文件夹
				if(!bool2)
					return false;
			}
		}
		return true;
	}

	/**
	 * 复制单个文件
	 * @param source String 原文件路径 如：c:/fqf.txt
	 * @param dest String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static boolean copyFile(String source, String dest) {
		try {
			// int bytesum = 0;
			int byteRead = 0;
			File file = new File(source);
			if (file.exists()) { // 文件存在时
				InputStream input = new FileInputStream(source); // 读入原文件
				File destFile = new File(dest);
				if(!destFile.exists())
					destFile.getParentFile().mkdirs();
				FileOutputStream out = new FileOutputStream(dest);
				byte[] buffer = new byte[1020];
				// int length;
				while ((byteRead = input.read(buffer)) != -1) {
					// bytesum += byteread; //字节数 文件大小
					// System.out.println(bytesum);
					out.write(buffer, 0, byteRead);
				}
				input.close();
				out.close();
				return true;
			}else{//文件不存在时
				return false;
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
			return false;
		}
	}

	/*// 拷贝文件
	private boolean copyFile2(String source, String dest) {
		try {
			File inputFile = new File(source);
			File outFile = new File(dest);
			FileInputStream input = new FileInputStream(inputFile);
			FileOutputStream out = new FileOutputStream(outFile);
			byte[] buffer = new byte[10240];
			int byteRead = 0;
			while ((byteRead = input.read(buffer)) != -1) {
				out.write(buffer, 0, byteRead);
			}// end while
			input.close();
			out.close();
		}// end try
		catch (Exception e) {

		}// end catch
	}// end copyFile
*/		
	/**
	 * 复制整个文件夹内容
	 * @param sourcePath String 原文件路径，如：c:/fqf
	 * @param destPath String 复制后路径，如：f:/fqf/ff
	 * @return boolean
	 */
	public static boolean copyFolder(String sourcePath, String destPath) {

		try {
			(new File(destPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(sourcePath);
			if(!a.exists()){
				return false;
			}
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (sourcePath.endsWith(File.separator)) {
					temp = new File(sourcePath + file[i]);
				} else {
					temp = new File(sourcePath + File.separator + file[i]);
				}

				if (temp.isFile()) {//判断是否是文件
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(destPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是否是文件夹
					boolean bool= copyFolder(sourcePath + "/" + file[i], destPath + "/" + file[i]);
					if(!bool)
						return false;
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 移动文件到指定目录
	 * @param sourcePath String 如：c:/fqf.txt
	 * @param destPath String 如：d:/fqf.txt
	 */
	public static boolean moveFile(String sourcePath, String destPath) {
		boolean bool1 = copyFile(sourcePath, destPath);
		boolean bool2 = delFile(sourcePath);
	    if(bool1 && bool2){
	    	return true;
	    }else{
	    	return false;
	    }
	}

	/**
	 * 移动文件夹到指定目录
	 * @param sourcePath String 如：c:/fqf.txt
	 * @param destPath String 如：d:/fqf.txt
	 */
	public static boolean moveFolder(String sourcePath, String destPath) {
		boolean bool1 = copyFolder(sourcePath, destPath);
		boolean bool2 = delFolder(sourcePath);
		if(bool1 && bool2){
	    	return true;
	    }else{
	    	return false;
	    }
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		FileOperate file = new FileOperate();
		// file.newFolder("newFolder22222");
		System.out.println(file.copyFile("D:\\2\\新建文件夹\\新建文件夹\\新建文本文档.txt", "D:/5/ddd.txt"));
	}
}
