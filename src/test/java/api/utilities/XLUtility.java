package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;

	public XLUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) {

		try {
			fi = new FileInputStream(path);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(fi);
		} catch (IOException e) {

			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		int lastRowNum = sheet.getLastRowNum();
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fi.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lastRowNum;
	}

	public int getCellCount(String sheetName, int rowNo) {

		try {
			fi = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook = new XSSFWorkbook(fi);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);

		int lastCellNum = row.getLastCellNum();

		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lastCellNum;
	}

	public String getCellData(String sheetName, int rowNo, int cellNo) {
		try {
			fi = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook = new XSSFWorkbook(fi);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.getCell(cellNo);

		DataFormatter formatter = new DataFormatter();
		String data;

		data = formatter.formatCellValue(cell);
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setCellData(String sheetName, int rowNo, int cellNo, String data) throws IOException {

		File xlFile = new File(path);
		if (!xlFile.exists()) {
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.close();
		}

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);

		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
		}

		sheet = workbook.getSheet(sheetName);

		if (sheet.getRow(rowNo) == null) {
			sheet.createRow(rowNo);
		}

		row = sheet.getRow(rowNo);
		cell = row.createCell(cellNo);
		cell.setCellValue(data);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();

	}

}
