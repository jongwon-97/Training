package j241016;

public class Point {
	private int x, y; // 한 점을 구성하는 x, y 좌표
	
	public Point() {
		x=y=0;
		System.out.println("초기 x,y 값 :" + this.x + "," + this.y);
	}
	public Point(int x , int y ) {
		this.x = x;
		this.y = y;
		System.out.println("초기 x,y 값 :" + this.x + "," + this.y);
	}
	

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void showPoint() { // 점의 좌표 출력
		System.out.println("(" + x + "," + y + ")");
	}

}
