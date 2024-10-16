package j241016;

public class ColorPoint extends Point {
	private String color; // 점의 색

	public ColorPoint() { // 이걸로 호출을 하면 기본생성자 point(){} 가 호출됨
	}

	public ColorPoint(int x, int y, String color) { 
		super(x, y);
		this.color = color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void showColorPoint() { // 컬러 점의 좌표 출력
		System.out.print(color);
		showPoint(); // Point의 showPoint() 호출
	}

}
