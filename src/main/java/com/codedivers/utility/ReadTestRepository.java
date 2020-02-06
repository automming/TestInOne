package com.codedivers.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.codedivers.selenium.pom.Element;
import com.codedivers.selenium.pom.TestCase;
import com.codedivers.selenium.pom.TestStep;

public class ReadTestRepository {

	private ExcelReader excel;

	public ReadTestRepository(String filepath) {
		this.excel = new ExcelReader(filepath);
	}

	public List<TestCase> getTestCasesRepository(Map<String, Element> objectRepo) {
		Map<String, TestCase> testcases = getAllTestCases(objectRepo);
		List<TestCase> testcasesRepo = new ArrayList<TestCase>();
		Sheet sheet = excel.getWorkbook().getSheet("TestSuite");
		int rows = sheet.getPhysicalNumberOfRows();

		for (int i = 1; i < rows; i++) {
			Row row = sheet.getRow(i);
			TestCase testcase = testcases.get(getStringCellValue(row.getCell(1)));
			testcase.setBrowser(getStringCellValue(row.getCell(3)));
			if (getStringCellValue(row.getCell(4)).equals("Yes")) {
				testcase.setExecute(1);
			} else {
				testcase.setExecute(0);
			}
			testcasesRepo.add(testcase);
		}

		return testcasesRepo;
	}

	private Map<String, TestCase> getAllTestCases(Map<String, Element> objectRepo) {
		Map<String, TestCase> testcasesRepo = new HashMap<String, TestCase>();
		Sheet sheet = excel.getWorkbook().getSheet("TestCases");
		int rows = sheet.getPhysicalNumberOfRows();

		for (int i = 1; i < rows; i++) {
			Row row = sheet.getRow(i);
			if (row.getCell(1) != null) {
				List<TestStep> steps = new ArrayList<TestStep>();
				TestCase testcase = new TestCase();
				testcase.setId(getStringCellValue(row.getCell(1)));
				testcase.setName(getStringCellValue(row.getCell(2)));
				for (int j = i; j < rows; j++) {
					Row stepRow = sheet.getRow(j);
					Row cRow = sheet.getRow(j + 1);
					if (cRow != null && cRow.getCell(1) == null) {
						String description = ((stepRow.getCell(3) == null) ? ""
								: stepRow.getCell(3).getStringCellValue());
						String action = ((stepRow.getCell(4) == null) ? "" : stepRow.getCell(4).getStringCellValue());
						String element = ((stepRow.getCell(5) == null) ? "" : stepRow.getCell(5).getStringCellValue());
						String data = ((stepRow.getCell(6) == null) ? "" : getStringCellValue(stepRow.getCell(6)));
						if (!element.equals(""))
							steps.add(new TestStep(description, action, objectRepo.get(element), data));
						else
							steps.add(new TestStep(description, action, null, data));
					} else {
						String description = ((stepRow.getCell(3) == null) ? ""
								: stepRow.getCell(3).getStringCellValue());
						String action = ((stepRow.getCell(4) == null) ? "" : stepRow.getCell(4).getStringCellValue());
						String element = ((stepRow.getCell(5) == null) ? "" : stepRow.getCell(5).getStringCellValue());
						String data = ((stepRow.getCell(6) == null) ? "" : getStringCellValue(stepRow.getCell(6)));
						if (!element.equals(""))
							steps.add(new TestStep(description, action, objectRepo.get(element), data));
						else
							steps.add(new TestStep(description, action, null, data));
						i = j;
						break;
					}
				}
				testcase.setSteps(steps);
				testcasesRepo.put(testcase.getId(), testcase);
			}
		}
		return testcasesRepo;

	}

	private String getStringCellValue(Cell cell) {
		DataFormatter formatter = new DataFormatter();
		String value = formatter.formatCellValue(cell);
		return value;
	}

}
