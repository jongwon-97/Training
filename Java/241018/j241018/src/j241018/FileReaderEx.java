package j241018;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderEx {

	public static void main(String[] args) {
		FileReader in = null;
//		try {
//			in = new FileReader("C:\\Users\\user\\Desktop\\log.txt");
//			int c ;
//			while ((c = in.read()) != -1) {
//				System.out.print
//				((char)c);
//			}
//			in.close();
//		}
//		catch (IOException e) {
//			System.out.println("파일 입출력 오류");
//		}
		//buffer에 (배열)에 파일 내용을 저장하기
		try {
			in = new FileReader("C:\\Users\\user\\Desktop\\log.txt");
			int c ;
			
			char [] cbuf = new char[1040];
			int i = 0 ;
			
			while ((c = in.read(cbuf)) != -1) {
				System.out.print((char) c);
				System.out.print((char) cbuf[i]);
				i ++;
			}
			in.close();
		}
		catch (IOException e) {
			System.out.println("파일 입출력 오류");
		}


	}

}
