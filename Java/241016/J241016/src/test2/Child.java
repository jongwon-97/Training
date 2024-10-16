package test2;

public class Child extends Parent {
	int func(int n) {
		if (n <= 1) {
			return n;
		}
		return func(n - 1) + func(n - 3);
	}
}
