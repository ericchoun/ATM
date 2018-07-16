import java.io.*;

public class orderFood {
	private int tableNum;
	private String orderedMenu;
	private int price;
	private int orderedNum;	
	
	void load() throws IOException {
		
		FileReader fReader = new FileReader("c:/test/order.txt");
		BufferedReader bReader = new BufferedReader(fReader);
		
		String [] lineContents;

		String line = null;
		while ((line = bReader.readLine()) != null) {
			lineContents = line.split(",");
			System.out.println(lineContents[1]);
			}
		bReader.close();
		fReader.close();
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public String getOrderedMenu() throws IOException {
		return orderedMenu;
	}

	public void setOrderedMenu(String orderedMenu) throws IOException {
		
		this.orderedMenu = orderedMenu;

	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOrderedNum() {
		return orderedNum;
	}

	public void setOrderedNum(int orderedNum) {
		this.orderedNum = orderedNum;
	}
	
	
}


