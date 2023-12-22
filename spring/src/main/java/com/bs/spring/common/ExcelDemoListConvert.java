package com.bs.spring.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.bs.spring.demo.model.dto.Demo;

public class ExcelDemoListConvert extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		List<Demo> demoList = (List<Demo>)model.get("demoList");
		
		//sheet 만들기
		final Sheet sheet1 = workbook.createSheet();
		
		addHeader(sheet1, List.of("번호","이름","나이","이메일","성별","사용언어"));
		demoList.forEach(e->{
			
			addContent(sheet1, e);
		});
	}

	private void addHeader(Sheet sheet, List<String> headerdata) {
		Row header = sheet.createRow(0);
		for(int i=0; i<headerdata.size(); i++) {
			header.createCell(i).setCellValue(headerdata.get(i));
		}
	}
		
	private void addContent(Sheet sheet, Demo demo) {
		Row row = sheet.createRow(sheet.getLastRowNum()+1);
		row.createCell(0).setCellValue(demo.getDevNo());
		row.createCell(1).setCellValue(demo.getDevName());
		row.createCell(2).setCellValue(demo.getDevAge());
		row.createCell(3).setCellValue(demo.getDevEmail());
		row.createCell(4).setCellValue(demo.getDevGender());
		row.createCell(5).setCellValue(String.join(",",demo.getDevLang()));
		
	}
}
