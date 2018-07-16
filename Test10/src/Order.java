import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
class xuancai{
	int tableNumber;
	String orderedmenu;
	int price;
	int orderedNum;
	void load() throws IOException {
		FileReader fReader = new FileReader("c:/test/order.xlsx");
		BufferedReader bReader = new BufferedReader(fReader);
		String str = null;
		while((str = bReader.readLine())!=null) {
			System.out.println(str);
		}
		bReader.close();
		fReader.close();	
	}
}
public class Order {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
//			FileReader fReader = new FileReader("c:/test/order.txt");
//			BufferedReader bReader = new BufferedReader(fReader);
//			String str = null;
//			while((str = bReader.readLine())!=null) {
//				System.out.println(str);
//			}
//			bReader.close();
//			fReader.close();	
//		}
		xuancai a=new xuancai();
		a.load();
	}
}


