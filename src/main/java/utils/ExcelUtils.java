package utils;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static String FILE_PATH;

	private static Workbook workbook;
	private static Sheet sheet;

	// Initialize Excel file
	public static void initExcel() {

		try {

			// Create timestamp
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

			// Dynamic filename
			FILE_PATH = System.getProperty("user.dir") + "/ExcelReport/TestResults_" + timestamp + ".xlsx";

			// Create new workbook
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Results");

			// Header
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Test Name");
			header.createCell(1).setCellValue("Status");

			FileOutputStream fos = new FileOutputStream(FILE_PATH);
			workbook.write(fos);
			fos.close();

			System.out.println("Excel Report: " + FILE_PATH);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Write result
	public static void writeResult(String testName, String status) {

		try {

			int lastRow = sheet.getLastRowNum() + 1;
			Row row = sheet.createRow(lastRow);

			// Test Name
			row.createCell(0).setCellValue(testName);

			// Create styles
			CellStyle passStyle = workbook.createCellStyle();
			Font passFont = workbook.createFont();
			passFont.setColor(IndexedColors.GREEN.getIndex());
			passFont.setBold(true);
			passStyle.setFont(passFont);

			CellStyle failStyle = workbook.createCellStyle();
			Font failFont = workbook.createFont();
			failFont.setColor(IndexedColors.RED.getIndex());
			failFont.setBold(true);
			failStyle.setFont(failFont);

			CellStyle skipStyle = workbook.createCellStyle();
			Font skipFont = workbook.createFont();
			skipFont.setColor(IndexedColors.ORANGE.getIndex());
			skipFont.setBold(true);
			skipStyle.setFont(skipFont);

			// Status Cell
			Cell statusCell = row.createCell(1);
			statusCell.setCellValue(status);

			// Apply color
			if (status.equalsIgnoreCase("PASS")) {
				statusCell.setCellStyle(passStyle);
			} else if (status.equalsIgnoreCase("FAIL")) {
				statusCell.setCellStyle(failStyle);
			} else {
				statusCell.setCellStyle(skipStyle);
			}

			// Write file
			FileOutputStream fos = new FileOutputStream(FILE_PATH);
			workbook.write(fos);
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Close workbook
	public static void closeExcel() {
		try {
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
