package j241016.updowncasting;

class Person {
	String name;
	String id;

	public Person(String name) {
		this.name = name;
	}
}

class Student extends Person {
	String grade;
	String department;

	public Student(String name) {
		super(name);
	}
}

public class UpcastingEx {


	public static void main(String[] args) {
		Person p;
		Student s = new Student("이재문");
		p = s; // 업캐스팅 발생
		System.out.println(p.name); // 오류 없음
//		p.grade = "A"; // 컴파일 오류 p = person 의 영역만 가르키기 때문에 오류가난다 
//		p.department = "Com"; // 컴파일 오류
		
		
		//다운캐스팅시 Student t=p; 는 오류가 나므로 아래와 같이 강제로 형변환을 시켜줘야 한다 
		Student t =(Student)p;
		t.grade = "A"; // 컴파일 오류 p = person 의 영역만 가르키기 때문에 오류가난다 
		t.department = "Com"; // 컴파일 오류
		
		System.out.println(t.name);
		System.out.println(t.grade);
		System.out.println(t.department);
	}

}
