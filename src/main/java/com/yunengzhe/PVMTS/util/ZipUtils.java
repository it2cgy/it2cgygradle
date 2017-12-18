package com.yunengzhe.PVMTS.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
 

public class ZipUtils {

	/**
	 * 
	 * 使用gzip进行压缩
	 */
	public static String gzip(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Base64.encodeBase64String(out.toByteArray());
	}

	/**
	 *
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 * 
	 * @param compressedStr
	 * @return
	 */
	public static String ungzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = Base64.decodeBase64(compressedStr);
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}

	/**
	 * 使用zip进行压缩
	 * 
	 * @param str
	 *            压缩前的文本
	 * @return 返回压缩后的文本
	 */
	public static final String zip(String str) {
		if (str == null)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr =  Base64.encodeBase64String(compressed);
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return compressedStr;
	}

	/**
	 * 使用zip进行解压缩
	 * 
	 * @param compressed
	 *            压缩后的文本
	 * @return 解压后的字符串
	 */
	public static final String unzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = Base64.decodeBase64(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}
	
	public static void main(String args[]){
		String test="{\"pointData\":[{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503452282,\"mtime\":809070,\"measuerName\":\"Voltage\",\"value\":624.0},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503452282,\"mtime\":809070,\"measuerName\":\"Current\",\"value\":7.12},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503452282,\"mtime\":809070,\"measuerName\":\"GeneratedActivePower\",\"value\":4.44},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503452282,\"mtime\":809070,\"measuerName\":\"PositiveActiveP\",\"value\":489.288},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503451840,\"mtime\":39606,\"measuerName\":\"PositiveActiveFlatP\",\"value\":0.0},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503451840,\"mtime\":39606,\"measuerName\":\"PositiveActivePeakP\",\"value\":0.0},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503452282,\"mtime\":809070,\"measuerName\":\"PositiveActiveValleyP\",\"value\":489.288},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503451840,\"mtime\":39606,\"measuerName\":\"PositiveActiveSharpP\",\"value\":0.0},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503451840,\"mtime\":39606,\"measuerName\":\"ReverseActiveP\",\"value\":0.015},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503451840,\"mtime\":39606,\"measuerName\":\"ReverseActiveFlatP\",\"value\":0.0},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503451840,\"mtime\":39606,\"measuerName\":\"ReverseActivePeakP\",\"value\":0.0},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503451840,\"mtime\":39606,\"measuerName\":\"ReverseActiveValleyP\",\"value\":0.015},{\"avaiabletag\":1,\"qualitycode\":0,\"time\":1503451840,\"mtime\":39606,\"measuerName\":\"ReverseActiveSharpP\",\"value\":0.0},{\"avaiabletag\":1,\"qualitycode\":2241,\"time\":1503452282,\"mtime\":0,\"measuerName\":\"PositiveActivePDaily\",\"value\":8.052002},{\"avaiabletag\":1,\"qualitycode\":2241,\"time\":1503451840,\"mtime\":0,\"measuerName\":\"ReverseActivePDaily\",\"value\":0.0},{\"avaiabletag\":1,\"qualitycode\":2241,\"time\":1503452282,\"mtime\":0,\"measuerName\":\"PositiveActivePMonth\",\"value\":104229.016},{\"avaiabletag\":1,\"qualitycode\":2241,\"time\":1503451840,\"mtime\":0,\"measuerName\":\"ReverseActivePMonth\",\"value\":103005.0},{\"avaiabletag\":1,\"qualitycode\":2241,\"time\":1503452282,\"mtime\":0,\"measuerName\":\"PositiveActivePYear\",\"value\":111025.016},{\"avaiabletag\":1,\"qualitycode\":2241,\"time\":1503451840,\"mtime\":0,\"measuerName\":\"ReverseActivePYear\",\"value\":109735.0},{\"avaiabletag\":1,\"qualitycode\":65555,\"time\":1503372381,\"mtime\":78608,\"measuerName\":\"UnifiedPower\",\"value\":5.475969E-7},{\"avaiabletag\":1,\"qualitycode\":65554,\"time\":1503368721,\"mtime\":277937,\"measuerName\":\"UnifiedPowerGeneration\",\"value\":0.0}],\"equipcontainerID\":67,\"equipcontainerTableID\":5}";
		String ziptest = ZipUtils.zip(test);
		String gziptest = ZipUtils.gzip(test);
		System.out.println(ziptest);
		System.out.println(ZipUtils.unzip(ziptest));

		System.out.println(gziptest);
		System.out.println(ZipUtils.ungzip(gziptest));
//		long time1 = System.currentTimeMillis();
//		for(int i=0;i<100000;i++){ 
//			ZipUtils.zip(test);
//		}
//		System.out.println(System.currentTimeMillis()-time1);
//		
//		long time2 = System.currentTimeMillis();
//		for(int i=0;i<100000;i++){
//			ZipUtils.unzip(ziptest);
//		}
//		System.out.println(System.currentTimeMillis()-time2);
//		
//		long time3 = System.currentTimeMillis();
//		for(int i=0;i<100000;i++){
//			ZipUtils.gzip(test);
//		}
//		System.out.println(System.currentTimeMillis()-time3);
//		
//		long time4 = System.currentTimeMillis();
//		for(int i=0;i<100000;i++){
//			ZipUtils.ungzip(gziptest);
//		}
//		System.out.println(System.currentTimeMillis()-time4);
//		
//		
	}
}
