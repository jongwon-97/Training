package test2;

public class Parent {
	int func(int n) {
		if (n <= 1)
			return n;
		return func(n - 1) + func(n - 2);
	}

}
