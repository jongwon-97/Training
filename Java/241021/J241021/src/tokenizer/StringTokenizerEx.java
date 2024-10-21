package tokenizer;

import java.util.StringTokenizer;

public class StringTokenizerEx {
	public static void main(String[] args) {
		String query = "name=kitae&addr=seoul&age=21";
		
		StringTokenizer st = new StringTokenizer(query, "="); //query 문에서 &를 기준으로 쪼갬
		
		int n = st.countTokens(); // 분리된 토큰 개수 name=kitae,addr=seoul,age=21 로 3개이다
		
		System.out.println("토큰 개수 = " + n);
		
		while (st.hasMoreTokens()) {
			String token = st.nextToken(); // 토큰 얻기
			System.out.println(token); // 토큰 출력
		}
	}

}
