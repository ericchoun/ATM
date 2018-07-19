
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class readexcel {
	
	private static void insert(Statement stmt, int num, String str) {
		
		try {
			
			stmt.executeUpdate("insert into ex1 (num, str) values ("+num+", '"+str+"');");
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws IOException {
		Connection conn;
		Statement stmt = null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?useSSL=false&serverTimezone=Asia/Seoul", "root","ghdals0426");
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 오류" + e.getMessage());
		}
		
		
		
		// File location
		String path = new File("src").getAbsolutePath();
	    
		String fileName = path + "/asdf.xlsx";
		
		// Order class lists 
		List<Select> selectList = new ArrayList<>();

		// Initial needed values
		XSSFWorkbook inputWorkbook = null;
		XSSFRow incurRow;
		XSSFCell incurCell;
		XSSFSheet incurSheet;

		int rowCount = 0;

		// Open file & get file data
		FileInputStream fis = new FileInputStream(fileName);
		inputWorkbook = new XSSFWorkbook(fis);
		fis.close();

		// Process sheet data

		// Get 1ST sheet
		incurSheet = inputWorkbook.getSheetAt(0);

		// Get 1ST sheet number of rows
		rowCount = incurSheet.getPhysicalNumberOfRows();

		for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
			// Get row
			incurRow = incurSheet.getRow(rowIndex);

			String invalue[] = new String[2];

			int cellCount = incurRow.getPhysicalNumberOfCells();
			for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
				// Get every cell data
				incurCell = incurRow.getCell(cellIndex);

				switch (incurCell.getCellTypeEnum()) {

				case STRING:
					invalue[cellIndex] = incurCell.getStringCellValue() + "";
					break;

				case NUMERIC:
					invalue[cellIndex] = incurCell.getNumericCellValue() + "";
					break;

				default:
					System.out.println(incurCell.getCellTypeEnum());
				}
			}

			Select sel = new Select();
			sel.setNum((int) Float.parseFloat(invalue[0]));
			sel.setStr(invalue[1]);
			
			selectList.add(sel);
		} 

		inputWorkbook.close();
		
		for (Select sel : selectList) {
			int num = sel.num;
			String str = sel.str;
			insert(stmt, num, str);
			System.out.println(sel.toString());
		}
		
		
	}
}