
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class excel0719 {
		public static void main(String[] args) throws IOException {

			// File location
			String path = new File("src").getAbsolutePath();
		    
			String  filename = path + "/excel0719";
			// Order class lists 
			List<asdxzc> orderList = new ArrayList<>();

			// Initial needed values
			XSSFWorkbook inputWorkbook = null;
			XSSFRow incurRow;
			XSSFCell incurCell;
			XSSFSheet incurSheet;

			int rowCount = 0;

			// Open file & get file data
			FileInputStream fis = new FileInputStream("D:/test/Book1.xlsx");
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

				asdxzc order = new asdxzc();
				order.setA((int) Float.parseFloat(invalue[0]));
				
				
				
				orderList.add(order);
			}

			inputWorkbook.close();
			
			for (asdxzc order : orderList) {
				System.out.println(order.toString());
			}
			
			
		}
	}

