package com.codedivers.utility;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.codedivers.selenium.pom.Element;

public class ReadObjectRepository {

	private ExcelReader excel;

	public ReadObjectRepository(String filepath) {
		this.excel = new ExcelReader(filepath);
	}

	public Map<String, Element> getObjectRepository() {
		Map<String, Element> objectRepo = new HashMap<String, Element>();
		Sheet repoSheet = excel.getWorkbook().getSheet("Repository");
		int rows = repoSheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rows; i++) {
			Row row = repoSheet.getRow(i);
			Element element = new Element();
			element.setElementName((row.getCell(0) == null) ? "" : row.getCell(0).getStringCellValue());
			element.setId((row.getCell(1) == null) ? "" : row.getCell(1).getStringCellValue());
			element.setXpath((row.getCell(2) == null) ? "" : row.getCell(2).getStringCellValue());
			element.setName((row.getCell(3) == null) ? "" : row.getCell(3).getStringCellValue());
			element.setClassName((row.getCell(4) == null) ? "" : row.getCell(4).getStringCellValue());
			element.setTag((row.getCell(5) == null) ? "" : row.getCell(5).getStringCellValue());
			objectRepo.put(element.getElementName(), element);
		}
		return objectRepo;
	}

}
