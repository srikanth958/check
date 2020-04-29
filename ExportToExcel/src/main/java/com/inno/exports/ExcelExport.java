package com.inno.exports;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Collection;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport<T> implements SuperExport<T>{

	@Override
	public File Export(ResultSet rs,String Filename) throws Exception {
		ResultSetMetaData rsmd=rs.getMetaData();
		int colcount=rsmd.getColumnCount();
		File file=new File("C:\\Users\\Lenova\\Desktop\\"+Filename);
		FileOutputStream fout=new FileOutputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Sheet1");
		Row row=sheet.createRow(0);
		int clmn=1;
		for(int i=0;i<colcount;i++) {
			
			row.createCell(i).setCellValue(rsmd.getColumnName(clmn++));
		}
		
		insertRsData(sheet, rs, rsmd,colcount);
		workbook.write(fout);
		workbook.close();
		return file;
	}
	public static void insertRsData(XSSFSheet sheet ,ResultSet rs,ResultSetMetaData rsmd,int colcount) throws Exception {
		
		int rows=1;
		while(rs.next()) {
			
			Row rowdata=sheet.createRow(rows++);
			int clmns=1;
			for(int j=0;j<colcount;j++) {
				
				String type=rsmd.getColumnTypeName(clmns);
				
				if(type== "VARCHAR") {
					rowdata.createCell(j).setCellValue(rs.getString(clmns++));
				}
				else if(type=="INT") {
					rowdata.createCell(j).setCellValue(rs.getInt(clmns++));
				}
				else if(type== "DATE") {
					rowdata.createCell(j).setCellValue(rs.getDate(clmns++));
				}
				else if(type== "TIME") {
					rowdata.createCell(j).setCellValue(rs.getTime(clmns++));
				}
				else if(type== "FLOAT") {
					rowdata.createCell(j).setCellValue(rs.getFloat(clmns++));
				}
				else if(type== "LONG") {
					rowdata.createCell(j).setCellValue(rs.getLong(clmns++));
				}
				else if(type== "DOUBLE") {
					rowdata.createCell(j).setCellValue(rs.getDouble(clmns++));
				}
			
				}
				
			}
	}
	@Override
	public File Export(T object, String Filename) throws Exception {
		Object obj=object;
		Field fields[]=obj.getClass().getDeclaredFields();

		File file=new File("C:\\Users\\Lenova\\Desktop\\"+Filename);
		FileOutputStream fout=new FileOutputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Sheet1");
		Row row=sheet.createRow(0);
		for(int i=0;i<fields.length;i++) {
			Field name=fields[i];
			row.createCell(i).setCellValue(name.getName());
		}
		insertBeanData(obj, fields, sheet);
		workbook.write(fout);
		workbook.close();
		return file;
	}
	public void insertBeanData(Object obj,Field fields[],XSSFSheet sheet) throws Exception, IllegalAccessException {
		int  cell=0;
		Row datarow=sheet.createRow(1);
		
		for (Field field:fields) {
			
			field.setAccessible(true);
			Object value=field.get(obj);
			
			datarow.createCell(cell++).setCellValue(""+value+"");
			
			}
	}
	@Override
	public File Export(Collection<T> objects, String Filename) throws Exception {
		File file=new File("C:\\Users\\Lenova\\Desktop\\"+Filename);
		FileOutputStream fout=new FileOutputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Sheet1");
		Object obj=null;
		for(Object objs:objects) {
			obj=objs;
			break;
		}
				
		Field fields[]=obj.getClass().getDeclaredFields();
		Row row=sheet.createRow(0);
		for(int i=0;i<fields.length;i++) {
			Field name=fields[i];
			row.createCell(i).setCellValue(name.getName());
		}
		insertBeansData(objects, fields, sheet);
		workbook.write(fout);
		workbook.close();
		return file;
	}
	public void insertBeansData(Collection<T> objects,Field fields[],XSSFSheet sheet) throws Exception{
		int row=1;
		for(Object obj:objects) {
		
		int  cell=0;
		Row datarow=sheet.createRow(row++);
		
		for (Field field:fields) {
			
			field.setAccessible(true);
			Object value=field.get(obj);
			
			datarow.createCell(cell++).setCellValue(""+value+"");
			
			}
	}
	}
}
