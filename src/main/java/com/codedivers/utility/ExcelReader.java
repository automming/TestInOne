package com.codedivers.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private Workbook workbook;

	public ExcelReader(String filepath) {
		File file;
		FileInputStream fis;
		try {
			file = new File(filepath);
			fis = new FileInputStream(file);
			workbook = setWorkBook(fis, filepath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Workbook setWorkBook(FileInputStream fis, String filepath) throws IOException {
		if (filepath.endsWith(".xls")) {
			return new HSSFWorkbook(fis);
		} else if (filepath.endsWith(".xlsx")) {
			return new XSSFWorkbook(fis);
		} else {
			throw new IllegalArgumentException("File format is not an excel.");
		}
	}

	public Workbook getWorkbook() {
		return workbook;
	}

}
