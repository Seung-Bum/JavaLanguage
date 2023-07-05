package com.items.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@RequestMapping("/ecxel")
public class ExcelUtil {
	
	@SuppressWarnings("resource") // �ݱ� ���� ������ �ڿ� ��뿡 ���õ� ��� ����
	public static List<List<String>> readToList(String path){
		List<List<String>> list = new ArrayList<List<String>>();
		
		try {
			FileInputStream fi = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fi);
			XSSFSheet sheet = workbook.getSheetAt(0); // ù��° ��Ʈ
			
			for(int i=0; i<sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				if(row != null) {
					List<String> cellList = new ArrayList<String>();
					for(int j=0; j<row.getLastCellNum(); j++) {
						XSSFCell cell = row.getCell(j); // row�� ������
						if(cell != null) {
							cellList.add( cellReader(cell) ); //���� �о�ͼ� List�� �߰�
						}
					}
					list.add(cellList); // �߰��� �ο�List�� List�� �߰�
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	@SuppressWarnings("incomplete-switch") // switch ������ ������ �׸�� ���õ� ��� �����մϴ�(enum case).
	private static String cellReader(XSSFCell cell) { // cell �ϳ��ϳ� Ÿ�԰��� �Ŀ� value�� ��� return
		String value = "";
		CellType ct = cell.getCellTypeEnum();
		if(ct != null) {
			switch(cell.getCellTypeEnum()) {
			case FORMULA:
				value = cell.getCellFormula();
				break;
			case NUMERIC:
			    value=cell.getNumericCellValue()+"";
			    break;
			case STRING:
			    value=cell.getStringCellValue()+"";
			    break;
			case BOOLEAN:
			    value=cell.getBooleanCellValue()+"";
			    break;
			case ERROR:
			    value=cell.getErrorCellValue()+"";
			    break;
			}
		}
		return value; 
	}
	
	// ���� ��ƿ�� �������� ����غ���
	@GetMapping("/get/excel")
	public void createExcelDownload(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// ȣ�� �޴°� �ϰ� ���� ����°� �ϰ� �и��ؾ���, ���Ŀ� ������� �����Ͷ� ��ġ��
		
		final String fileName = "userList.xlsx";
	    
		/* ���� �׸��� */
		final String[] colNames = {
			"No", "����", "����", "������"
		};
		
		// ��� ������
		final int[] colWidths = {
			3000, 5000, 5000, 3000
		};
		
		final String[] rowData = {
			"ȣȣȣ", "������", "ŰŰŰ", "������"
		};
		
		//rows
		int rowCnt = 0;
		int cellCnt = 0;
		int listCount = 10; //listData.size();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("�������Ȳ");
		XSSFRow row = sheet.createRow(rowCnt++);
		
		try {
			
			//��� ���� ����
			for (int i = 0; i < colNames.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellValue(colNames[i]);
				sheet.setColumnWidth(i, colWidths[i]);	//column width ����
			}
			
			// row ���� ����
			for(String vo : rowData) {
				cellCnt = 0;
				row = sheet.createRow(rowCnt++);
				
				// �ѹ���
				XSSFCell cell = row.createCell(cellCnt++);
				cell.setCellValue(listCount--);
				
				// ����
				cell = row.createCell(cellCnt++);
				cell.setCellValue(vo);
				
				// ����
				cell = row.createCell(cellCnt++);
				cell.setCellValue(vo);
				
				// �ּ�
				cell = row.createCell(cellCnt++);
				cell.setCellValue(vo);
			}
		
	    // excel download
		res.setContentType("application/vnd.ms-excel");
		workbook.write(res.getOutputStream());
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
	}  

}
