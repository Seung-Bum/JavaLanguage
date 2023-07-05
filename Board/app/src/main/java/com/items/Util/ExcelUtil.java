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
	
	@SuppressWarnings("resource") // 닫기 가능 유형의 자원 사용에 관련된 경고 억제
	public static List<List<String>> readToList(String path){
		List<List<String>> list = new ArrayList<List<String>>();
		
		try {
			FileInputStream fi = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fi);
			XSSFSheet sheet = workbook.getSheetAt(0); // 첫번째 시트
			
			for(int i=0; i<sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				if(row != null) {
					List<String> cellList = new ArrayList<String>();
					for(int j=0; j<row.getLastCellNum(); j++) {
						XSSFCell cell = row.getCell(j); // row를 가져옴
						if(cell != null) {
							cellList.add( cellReader(cell) ); //셀을 읽어와서 List에 추가
						}
					}
					list.add(cellList); // 추가된 로우List를 List에 추가
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	@SuppressWarnings("incomplete-switch") // switch 문에서 누락된 항목과 관련된 경고를 억제합니다(enum case).
	private static String cellReader(XSSFCell cell) { // cell 하나하나 타입검증 후에 value에 담고 return
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
	
	// 엑셀 유틸로 가져가서 사용해보기
	@GetMapping("/get/excel")
	public void createExcelDownload(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 호출 받는것 하고 엑셀 만드는것 하고 분리해야함, 그후에 응답받은 데이터랑 뭉치기
		
		final String fileName = "userList.xlsx";
	    
		/* 엑셀 그리기 */
		final String[] colNames = {
			"No", "성명", "나이", "거주지"
		};
		
		// 헤더 사이즈
		final int[] colWidths = {
			3000, 5000, 5000, 3000
		};
		
		final String[] rowData = {
			"호호호", "하하하", "키키키", "허허허"
		};
		
		//rows
		int rowCnt = 0;
		int cellCnt = 0;
		int listCount = 10; //listData.size();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("사용자현황");
		XSSFRow row = sheet.createRow(rowCnt++);
		
		try {
			
			//헤더 정보 구성
			for (int i = 0; i < colNames.length; i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellValue(colNames[i]);
				sheet.setColumnWidth(i, colWidths[i]);	//column width 지정
			}
			
			// row 정보 구성
			for(String vo : rowData) {
				cellCnt = 0;
				row = sheet.createRow(rowCnt++);
				
				// 넘버링
				XSSFCell cell = row.createCell(cellCnt++);
				cell.setCellValue(listCount--);
				
				// 성명
				cell = row.createCell(cellCnt++);
				cell.setCellValue(vo);
				
				// 나이
				cell = row.createCell(cellCnt++);
				cell.setCellValue(vo);
				
				// 주소
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
