package edu.yust.ceceom;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class javaclass0719 {
	private static Statement stmt;

	public static void main(String[] args) throws IOException {
	// File location
	String path = new File("src").getAbsolutePath();
    
	String fileName = "c:/test/01.xlsx";
	
	// Order class lists 
	List<Order> orderList = new ArrayList<>();

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

	for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
		// Get row
		incurRow = incurSheet.getRow(rowIndex);

		String invalue[] = new String[4];

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

		Order order = new Order();
		order.setTableNumber((int) Float.parseFloat(invalue[0]));
//		order.setOrderedMenu(invalue[1]);
//		order.setMenuPrice((int) Float.parseFloat(invalue[2].substring(0, invalue[2].length() - 1)));
//		order.setOrderedNumber((int) Float.parseFloat(invalue[3]));
		
		orderList.add(order);
	}

	inputWorkbook.close();
	
	
	// TODO Auto-generated method stub
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=
						DriverManager.getConnection("jdbc:mysql://localhost:3306/javaclass?useSSL=false&ServerTimezone=asia","root","root");
				stmt = conn.createStatement();
				for (Order order : orderList) {
					stmt.executeUpdate("insert into orderMenu(tableNumber) values (" + order.getTableNumber()+")");
					System.out.println(order.toString());
				}
	 			
	 			
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
	
}
}