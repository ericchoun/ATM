
package ATM;

import java.util.*;

class Select {
	
	void menu() {				// 메뉴 출력
		System.out.println("1. 등록\n2. 입금\n3. 출금\n4. 조회\n5. 나가기");
	}
}

class Login {
	
	void login() {				// 등록
		
	}
}

class Deposit {
	
	void moneyIn() {			// 입금
		
	}	
}

class Withdrawal {

	void moneyOut() {			// 출금
		
	}
}

class Balance {
	
	void balance() {			// 조회
		
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		boolean a = true;
		
		Select s = new Select();
		s.menu();
		
		Login login = new Login();
		Deposit dep = new Deposit();
		Withdrawal wd = new Withdrawal();
		Balance bal = new Balance();
		
		Scanner sc = new Scanner(System.in);
		
		while (a) {
			i = sc.nextInt();
			switch(i) {
			case 1: 
				login.login();
				break;
			case 2:
				dep.moneyIn();
				break;
			case 3:
				wd.moneyOut();
				break;
			case 4:
				bal.balance();
				break;
			case 5:
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
