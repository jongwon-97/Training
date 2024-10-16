package test;

public class Mypro2 extends Mypro1 {
	int compute(int x , int y) {
		return x - y + super.compute(x,y);
	}

}
