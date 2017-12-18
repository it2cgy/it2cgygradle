package com.yunengzhe.PVMTS.hbase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable; 
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;

import com.yunengzhe.commons.util.hbase.HbaseCellData;
import com.yunengzhe.commons.util.hbase.HbasePutData;
import com.yunengzhe.commons.util.hbase.HbaseResultData; 

public class HbaseClient {
	private static final Logger logger = LoggerFactory.getLogger(HbaseClient.class);

	// 声明静态配置
	private Configuration conf = null;
	public HbaseClient(String serverHost) {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", serverHost);
	}

	/*
	 * 创建表
	 * 
	 * @tableName 表名
	 * 
	 * @family 列族列表
	 */
	public  void creatTable(String tableName, String[] family)
			throws Exception {
		HBaseAdmin admin = new HBaseAdmin(conf);
		HTableDescriptor desc = new HTableDescriptor(tableName);
		for (int i = 0; i < family.length; i++) {
			desc.addFamily(new HColumnDescriptor(family[i]));
		}
		if (admin.tableExists(tableName)) {
			System.out.println("table Exists!"); 
		} else {
			admin.createTable(desc);
			System.out.println("create table Success!");
		}
	}

	/*
	 * 为表添加数据（适合知道有多少列族的固定表）
	 * 
	 * @rowKey rowKey
	 * 
	 * @tableName 表名
	 * 
	 * @column1 第一个列族列表
	 * 
	 * @value1 第一个列的值的列表
	 * 
	 * @column2 第二个列族列表
	 * 
	 * @value2 第二个列的值的列表
	 */
	public  void addData(String rowKey, String tableName,
			String[] column1, String[] value1, String[] column2, String[] value2)
					throws IOException {
		Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
		HTable table = new HTable(conf, Bytes.toBytes(tableName));// HTabel负责跟记录相关的操作如增删改查等//
		// 获取表
		HColumnDescriptor[] columnFamilies = table.getTableDescriptor() // 获取所有的列族
				.getColumnFamilies();

		for (int i = 0; i < columnFamilies.length; i++) {
			String familyName = columnFamilies[i].getNameAsString(); // 获取列族名
			if (familyName.equals("article")) { // article列族put数据
				for (int j = 0; j < column1.length; j++) {
					put.add(Bytes.toBytes(familyName),
							Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));
				}
			}
			if (familyName.equals("author")) { // author列族put数据
				for (int j = 0; j < column2.length; j++) {
					put.add(Bytes.toBytes(familyName),
							Bytes.toBytes(column2[j]), Bytes.toBytes(value2[j]));
				}
			}
		}
		table.put(put);
		System.out.println("add data Success!");
	}

	/*
	 * 根据rwokey查询
	 * 
	 * @rowKey rowKey
	 * 
	 * @tableName 表名
	 */
//	public  Result getResult(String tableName, String rowKey)
//			throws IOException {
//		Get get = new Get(Bytes.toBytes(rowKey));
//		HTable table = new HTable(conf, Bytes.toBytes(tableName));// 获取表
//		Result result = table.get(get);
//		for (KeyValue kv : result.list()) {
//			System.out.println("family:" + Bytes.toString(kv.getFamily()));
//			System.out
//			.println("qualifier:" + Bytes.toString(kv.getQualifier()));
//			System.out.println("value:" + Bytes.toString(kv.getValue()));
//			System.out.println("Timestamp:" + kv.getTimestamp());
//			System.out.println("-------------------------------------------");
//		}
//		return result;
//	}

//	/*
//	 * 遍历查询hbase表
//	 * 
//	 * @tableName 表名
//	 */
//	public  void getResultScann(String tableName) throws IOException {
//		Scan scan = new Scan();
//		ResultScanner rs = null;
//		HTable table = new HTable(conf, Bytes.toBytes(tableName));
//		try {
//			rs = table.getScanner(scan);
//			for (Result r : rs) {
//				for (KeyValue kv : r.list()) {
//					System.out.println("row:" + Bytes.toString(kv.getRow()));
//					System.out.println("family:"
//							+ Bytes.toString(kv.getFamily()));
//					System.out.println("qualifier:"
//							+ Bytes.toString(kv.getQualifier()));
//					System.out
//					.println("value:" + Bytes.toString(kv.getValue()));
//					System.out.println("timestamp:" + kv.getTimestamp());
//					System.out
//					.println("-------------------------------------------");
//				}
//			}
//		}catch(Exception e){
//			logger.error(e.getMessage(),e);
//		} 		
//		finally {
//			rs.close();
//		}
//	}

	/**
	 * 存多条数据
	 * @param tableName
	 * @param datas
	 */
	public  void putBatch(String tableName, List<HbasePutData> datas) {
		try {
			long start = System.currentTimeMillis();
			final List<Put> puts = new ArrayList<Put>();
			for(HbasePutData data:datas){  
				byte[] rowkey = data.getKey().getBytes();  
				Put put = new Put(rowkey);  
				put.addColumn(Bytes.toBytes(data.getFamilyName()),Bytes.toBytes(data.getQualifier()), Bytes.toBytes(data.getValue()));  
				puts.add(put);
			}  
			HTable table;

			table = new HTable(conf, Bytes.toBytes(tableName));
			this.echoTableInfo(table);
			// HTabel负责跟记录相关的操作如增删改查等//
			table.put(puts); 
			long end = System.currentTimeMillis();
			logger.debug(String.format("插入 %d 条,执行 %d ms",puts.size(),end-start));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}    

	private void echoTableInfo(HTable table){
		try{
			String zookeeper = table.getConfiguration().get("hbase.zookeeper.quorum");
			List<HRegionLocation> HRegionLocations = table.getRegionLocator().getAllRegionLocations();
			StringBuilder builder = new StringBuilder();
			builder.append("zookeeper:");
			if(zookeeper!=null){ 
				
				builder.append(zookeeper);
			}
			builder.append(" regions:");
			for(HRegionLocation region:HRegionLocations){
				builder.append(region.getHostnamePort());
				builder.append(",");			
			}
			logger.info("database info-----"+builder.toString());
		} catch (Exception e){
			logger.error(e.getMessage(),e);
		}

	}

	/*
	 * 遍历查询hbase表
	 * 
	 * @tableName 表名
	 */
	public   List<HbaseResultData> getResultScann(String tableName, String start_rowkey,
			String stop_rowkey) throws IOException {

		List<HbaseResultData> results = new ArrayList<HbaseResultData>();

		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(start_rowkey));
		scan.setStopRow(Bytes.toBytes(stop_rowkey));
		ResultScanner rs = null;
		HTable table = new HTable(conf, Bytes.toBytes(tableName));
		
		this.echoTableInfo(table);
		
		long startTime = System.currentTimeMillis();
		try {
			rs = table.getScanner(scan);
			for (Result r : rs) {
				HbaseResultData resultData = null;
				String  row = "";
				List<Cell> ceList =   r.listCells();    
				if(ceList!=null&&ceList.size()>0){  
					for(Cell cell:ceList){  
						row =Bytes.toString( cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());   
						if(resultData==null){
							List<HbaseCellData> dataCells = new ArrayList<HbaseCellData>(ceList.size());
							resultData = new HbaseResultData();
							resultData.setKey(row); 
							resultData.setCells(dataCells);  
						} 
						String value =Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());  
						String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());  
						String quali = Bytes.toString( cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());  

						HbaseCellData data = new HbaseCellData(family,quali,value);

						resultData.getCells().add(data);
					}  

				}  
				if(resultData!=null){
					results.add(resultData);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}finally {
			rs.close();
		}
		long endTime = System.currentTimeMillis();
		logger.debug("table:"+tableName+" start:"+ start_rowkey +" end:"+ stop_rowkey +" count:"+ results.size() +" time(ms):"+(endTime-startTime));
		return results;
	}

	/*
	 * 查询表中的某一列
	 * 
	 * @tableName 表名
	 * 
	 * @rowKey rowKey
	 */
	public  void getResultByColumn(String tableName, String rowKey,
			String familyName, String columnName) throws IOException {
		HTable table = new HTable(conf, Bytes.toBytes(tableName));
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName)); // 获取指定列族和列修饰符对应的列
		Result result = table.get(get);
		for (KeyValue kv : result.list()) {
			System.out.println("family:" + Bytes.toString(kv.getFamily()));
			System.out
			.println("qualifier:" + Bytes.toString(kv.getQualifier()));
			System.out.println("value:" + Bytes.toString(kv.getValue()));
			System.out.println("Timestamp:" + kv.getTimestamp());
			System.out.println("-------------------------------------------");
		}
	}

	/*
	 * 更新表中的某一列
	 * 
	 * @tableName 表名
	 * 
	 * @rowKey rowKey
	 * 
	 * @familyName 列族名
	 * 
	 * @columnName 列名
	 * 
	 * @value 更新后的值
	 */
	public  void updateTable(String tableName, String rowKey,
			String familyName, String columnName, String value)
					throws IOException {
		HTable table = new HTable(conf, Bytes.toBytes(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(familyName), Bytes.toBytes(columnName),
				Bytes.toBytes(value));
		table.put(put);
		System.out.println("update table Success!");
	}

	/*
	 * 查询某列数据的多个版本
	 * 
	 * @tableName 表名
	 * 
	 * @rowKey rowKey
	 * 
	 * @familyName 列族名
	 * 
	 * @columnName 列名
	 */
	public  void getResultByVersion(String tableName, String rowKey,
			String familyName, String columnName) throws IOException {
		HTable table = new HTable(conf, Bytes.toBytes(tableName));
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
		get.setMaxVersions(5);
		Result result = table.get(get);
		for (KeyValue kv : result.list()) {
			System.out.println("family:" + Bytes.toString(kv.getFamily()));
			System.out
			.println("qualifier:" + Bytes.toString(kv.getQualifier()));
			System.out.println("value:" + Bytes.toString(kv.getValue()));
			System.out.println("Timestamp:" + kv.getTimestamp());
			System.out.println("-------------------------------------------");
		}
		/*
		 * List<?> results = table.get(get).list(); Iterator<?> it =
		 * results.iterator(); while (it.hasNext()) {
		 * System.out.println(it.next().toString()); }
		 */
	}

	/*
	 * 删除指定的列
	 * 
	 * @tableName 表名
	 * 
	 * @rowKey rowKey
	 * 
	 * @familyName 列族名
	 * 
	 * @columnName 列名
	 */
	public  void deleteColumn(String tableName, String rowKey,
			String falilyName, String columnName) throws IOException {
		HTable table = new HTable(conf, Bytes.toBytes(tableName));
		Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
		deleteColumn.deleteColumns(Bytes.toBytes(falilyName),
				Bytes.toBytes(columnName));
		table.delete(deleteColumn);
		System.out.println(falilyName + ":" + columnName + "is deleted!");
	}

	/*
	 * 删除指定的列
	 * 
	 * @tableName 表名
	 * 
	 * @rowKey rowKey
	 */
	public  void deleteAllColumn(String tableName, String rowKey)
			throws IOException {
		HTable table = new HTable(conf, Bytes.toBytes(tableName));
		Delete deleteAll = new Delete(Bytes.toBytes(rowKey));
		table.delete(deleteAll);
		System.out.println("all columns are deleted!");
	}

	/*
	 * 删除表
	 * 
	 * @tableName 表名
	 */
//	public  void deleteTable(String tableName) throws IOException {
//		HBaseAdmin admin = new HBaseAdmin(conf);
//		admin.disableTable(tableName);
//		admin.deleteTable(tableName);
//		System.out.println(tableName + "is deleted!");
//	}

	public static void main(String[] args) throws Exception {
		
//		String[] family = { "colfam01"};
//		HbaseClient hbase1 = new HbaseClient("hbaseserver1");
//		//hbase1.creatTable("scadadb:analoginputhistory2", family);
//		List<HbaseResultData> results = hbase1.getResultScann("scadadb:analoginputhistory2", "a1000000", "a1000010");
//
//		 List<HbasePutData> datas = new ArrayList<HbasePutData>();
//		 for(HbaseResultData result:results){
//			 for(HbaseCellData cell:result.getCells()){
//				 HbasePutData data = new HbasePutData();
//				 data.setKey(result.getKey());
//				 data.setFamilyName(cell.getFamilyName());
//				 data.setQualifier(cell.getQualifier());
//				 data.setValue(cell.getValue());
//				 datas.add(data);
//			 }
//		 }
//		 
//		HbaseClient hbase2 = new HbaseClient("hbaseserver2");
//		hbase2.putBatch("scadadb:analoginputhistory2", datas);
//		
//		HbaseClient hbase = new HbaseClient("hbaseserver1");
		// 创建表
		//        String tableName = "blog2";
		//        String[] family = { "article", "author" };
		//        creatTable(tableName, family);
		//
		//        // 为表添加数据
		//
		//        String[] column1 = { "title", "content", "tag" };
		//        String[] value1 = {
		//                "Head First HBase",
		//                "HBase is the Hadoop database. Use it when you need random, realtime read/write access to your Big Data.",
		//                "Hadoop,HBase,NoSQL" };
		//        String[] column2 = { "name", "nickname" };
		//        String[] value2 = { "nicholas", "lee" };
		//        addData("rowkey1", "blog2", column1, value1, column2, value2);
		//        addData("rowkey2", "blog2", column1, value1, column2, value2);
		//        addData("rowkey3", "blog2", column1, value1, column2, value2);

//		// 遍历查询
//		hbase.getResultScann("scadadb:analoginputhistory", "a1000000", "a1030000");
//		// 根据row key范围遍历查询
//		hbase.getResultScann("scadadb:analoginputhistory", "a1000000", "a1200000");
		//
		//        // 查询
		//        getResult("blog2", "rowkey1");
		//
		//        // 查询某一列的值
		//        getResultByColumn("blog2", "rowkey1", "author", "name");
		//
		//        // 更新列
		//        updateTable("blog2", "rowkey1", "author", "name", "bin");
		//
		//        // 查询某一列的值
		//        getResultByColumn("blog2", "rowkey1", "author", "name");
		//
		//        // 查询某列的多版本
		//        getResultByVersion("blog2", "rowkey1", "author", "name");
		//
		//        // 删除一列
		//        deleteColumn("blog2", "rowkey1", "author", "nickname");
		//
		//        // 删除所有列
		//        deleteAllColumn("blog2", "rowkey1");
		//
		//        // 删除表
		//        deleteTable("blog2");

	}
}