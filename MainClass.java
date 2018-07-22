package ATM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainClass {
	
	static void menu1() {				// 첫번째 메뉴 출력
		System.out.println("========================");
		System.out.println("1.등록\t2.로그인\t3.나가기");
	}
	
	static void menu2() {				// 다음 메뉴 출력
		System.out.println("========================");
		System.out.println("1.입금\t2.출금\t3.조회\t4.나가기");
	}
	
	private static void deposit(Statement stmt, String id, float moneyIn, float money) {
		
		try {
			
			System.out.println("입금하실 금액을 입력하세요");
			Scanner sc = new Scanner(System.in);
			moneyIn = sc.nextFloat();
			ResultSet rs = stmt.executeQuery("select userMoney from userinfo where userID = '"+id+"';");
			while (rs.next()) {
				money = rs.getFloat("userMoney") + moneyIn;
				if (money < 1000000) {
					System.out.println(moneyIn+"원이 입금되었습니다.");
				} else if (money >= 1000000){
					System.out.println("한도를 초과하셨습니다.");
				} 
			}
			stmt.executeUpdate("update userinfo set userMoney = '"+money+"' where userID = '"+id+"';");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void withdraw(Statement stmt, String id, float moneyOut, float money) {
		
		float a = 0;
		try {
			System.out.println("출금할 금액을 입력하세요");
			Scanner sc = new Scanner(System.in);		// 출금할 금액 입력
			moneyOut = sc.nextFloat();					// 입력한 금액을 moneyOut 변수에 저장
			ResultSet rs = stmt.executeQuery("select userMoney from userinfo where userID = '"+id+"';");	// id에 해당하는 보유 금액을 불러옴
			while (rs.next()) {
				money = rs.getFloat("userMoney") - moneyOut;
				a = money;
				if (rs.getFloat("userMoney") >= moneyOut) {						// 만약 잔액이 출금금액보다 많으면 정상적으로 출금
					System.out.println(moneyOut+"원을 출금하였습니다.");
				} else {
					System.out.println("잔액이 부족합니다.");	// 만약 잔액이 출금금액보다 적다면 잔액이 부족하다는 메세지
					a = money + moneyOut;
				}
			}
			stmt.executeUpdate("update userinfo set userMoney = '"+a+"' where userID = '"+id+"';");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void balance(Statement stmt, String id) {
		
		try {
			ResultSet rs = stmt.executeQuery("select userMoney from userinfo where userID = '"+id+"';");
			while (rs.next()) {
				System.out.println("잔액은 "+rs.getFloat("userMoney")+"원 입니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		int j;
		boolean a = true;
		Connection conn;
		Statement stmt = null;
		Statement stmt2 = null;
		String id = null;
		String pass = null;
		float moneyIn = (float) 0;
		float moneyOut = (float) 0;
		float money = (float) 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice?useSSL=false&serverTimezone=Asia/Seoul", "root","");
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 오류" + e.getMessage());
		}
		
		menu1();
			
		Scanner sc = new Scanner(System.in);
		
		while (a) {
			i = sc.nextInt();
			switch(i) {
			case 1: 
				try {
					
					Scanner sc1 = new Scanner(System.in);
					System.out.println("아이디를 입력하세요: ");
					id = sc1.nextLine();
					System.out.println("비밀번호를 입력하세요: ");
					pass = sc1.nextLine();
					stmt.executeUpdate("insert into userinfo (userID, userPass, userMoney) values ('"+id+"','"+pass+"',0);");
					System.out.println("회원가입 완료.");
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
					menu1();
				}
				break;
			case 2:
				try {
					Scanner sc2 = new Scanner(System.in);
					System.out.println("로그인하세요.");
					System.out.println("ID : ");
					id = sc2.nextLine();
					System.out.println("Password : ");
					pass = sc2.nextLine();

					menu2();

								
					while (a) {
						j = sc.nextInt();
						switch(j) {
						case 1: 
							deposit(stmt, id, moneyIn, money);
							menu2();
							break;
						case 2:
							withdraw(stmt, id, moneyOut, money);
							menu2();
							break;
						case 3:
							balance(stmt, id);
							menu2();
							break;
						case 4:
							System.out.println("End.");
							a = false;
							break;
						default:
							System.out.println("잘못 입력하셨습니다.");
							break;
						}
					}
					
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("End.");
				a = false;
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

}
