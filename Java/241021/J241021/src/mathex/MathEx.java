package mathex;

public class MathEx {

	public static void main(String[] args) {
		System.out.println(Math.abs(-3.14));    //절대값 구하기
		System.out.println(Math.sqrt(9.0));     //9의 제곱근 =3
  		System.out.println(Math.exp(2));       // e 의 2승
		System.out.println(Math.round(3.14));    //반올림
		
		System.out.println("이번주 행운의 번호");
		for(int i=0;i<5;i++) {
			System.out.println((int)(Math.random()*45+1)+"");
		}

	}

}
