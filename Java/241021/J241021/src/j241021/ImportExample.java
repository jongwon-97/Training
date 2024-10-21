package j241021;

import java.util.Scanner;

public class ImportExample {

	public static void main(String[] args) {
		System.out.println("당신의 이름을 입력해주세요.");
		// 스캐너는 java.util. 이폴더에 있는건데 이걸 매번 붙여서 쓰긴 비효율적이라서 임포트를해서 생략해줌
		Scanner sc = new Scanner(System.in);
		String name = sc.next();

	}

}
