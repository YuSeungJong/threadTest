package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 	문제) 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 		- 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
 * 		- 사용자는 showInputDialog()메서드를 이용해서 입력 받는다.
 * 		- 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 		- 5초안에 입력이 없으면 게임에 진것으로 처리하고 끝낸다.
 * 	
 * 	5초안에 입력이 완료되었을 때 결과 예시)
 * 		-- 결과 --
 * 		컴퓨터 : 가위
 * 		사용자 : 바위
 * 		결   과 : 당신이 이겼습니다.
 * 	
 * 	5초안에 입력이 없었을때 결과 예시
 * 		시간이 초과되어 당신이 졌습니다.
 */
public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th1 = new Game();
		Thread th2 = new CountDown1();
		
		th1.start();
		th2.start();
	}

}

class Game extends Thread{
	// 입력 여부를 확인하기 위한 변수 선언 ==> 쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck;
	@Override
	public void run() {
		int ran = (int)(Math.random() * 3 + 1);
		String you = "";
		switch(ran) {
		case 1:
			you = "묵"; break;
		case 2:
			you = "찌"; break;
		case 3:
			you = "빠"; break;
			
		}
		
		String str = JOptionPane.showInputDialog("가위 바위 보 중에 입력하세요.");
		
		System.out.println("입력한 값 : " + str);
		System.out.println("상대방 값 : " + you);
		
		if(str == null) {
			System.out.println("당신은 패배하였습니다.");
			System.exit(0);
		}
		
		if(!str.equals("묵")&&!str.equals("찌")&&!str.equals("빠")) {
			System.out.println("잘못입력하여 당신은 패배하였습니다.");
		}
		
		if(str.equals("묵")&&you.equals("찌")) {
			System.out.println("당신이 이겼습니다.");
		}
		
		if(str.equals("묵")&&you.equals("빠")) {
			System.out.println("당신이 졌습니다.");
		}
		
		if(str.equals("묵")&&you.equals("묵")) {
			System.out.println("비겼습니다.");
		}
		
		if(str.equals("찌")&&you.equals("찌")) {
			System.out.println("비겼습니다");
		}
		
		if(str.equals("찌")&&you.equals("빠")) {
			System.out.println("당신이 이겼습니다");
		}
		
		if(str.equals("찌")&&you.equals("묵")) {
			System.out.println("당신이 졌습니다.");
		}
		
		if(str.equals("빠")&&you.equals("찌")) {
			System.out.println("당신이 졌습니다.");
		}
		
		if(str.equals("빠")&&you.equals("빠")) {
			System.out.println("당신이 비겼습니다.");
		}
		
		if(str.equals("빠")&&you.equals("묵")) {
			System.out.println("당신이 이겼습니다.");
		}
		
		inputCheck = true;
		
	}
}

class CountDown1 extends Thread{
	@Override
	public void run() {
		for(int i = 10; i >= 1; i--) {
			
			// 입력이 완료되었는지 여부를 검사해서 입력이 완료되면 쓰레드를 종료시킨다. 
			if(Game.inputCheck==true) {
				return;		// run()메서드가 종료되면 해당 쓰레드도 종료된다.
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("10초가 지났습니다. 당신은 패배하였습니다.");
		System.exit(0);

	}
}




















