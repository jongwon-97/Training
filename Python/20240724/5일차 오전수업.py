#입력함수
#두수를 입력(변수 2개)
#type(a)
#출력
'''string_a= input("입력A:")
print(type(string_a))
#자료형 int 로 변환 => 연산
int_a = int(string_a)
string_b=input("입력B: ")
int_b = int(string_b)

print("문자열자료:", string_a+ string_b)
print("숫자 자료:",int_a+int_b)'''

'''a,b= map(int,input("숫자를 띄어쓰기 구분해서 입력하세요.").split())

print("문자열 자료:",str(a)+str(b))
print("정수형 자료:",a+b)'''


'''
#인치를 입력받아서 cm 로 변환하기 -> 출력
#입력함수
raw_input=input("inch 단위의 숫자를 입력해주세요.")
#숫자 변환
inch = int(raw_input) # 연산이 가능해짐

cm = inch * 2.54
#출력하기 
print(inch,"inch는 cm 단위로",cm,"cm입니다.")
'''
'''
# 입력값에다가 1200을 곱한 결과 출력
num = input("원을 입력하세요")

try:
    
    won = int(num)
    result = won *1200
    print(won,"에 1200을 곱한 결과는",result,"입니다.",sep="") #sep은 띄어쓰기 공백을 없애주는 뜻 ex)1 원 > 1원
    
except ValueError :    #만약 1200원 이라 하면 오류가 발생하게됨 문자와 정수가 섞였기 때문
    print("오류가 발생했습니다.") 

#사용자로부터 원화 금액을 입력받고 1200을 곱한 결과 값
'''

#실수형 자료 입력 받아서 더하기, 뺄셈, 곱셈, 나눗셈 출력
'''input_a=float(input("첫 번째 숫자: "))
input_b=float(input("두 번째 숫자: "))

print("덧셈 결과: ", int(input_a+input_b))
print("뺄셈 결과: ", int(input_a-input_b))
print("곱셈 결과: ", int(input_a*input_b))
print("나눗셈 결과: ", input_a/input_b)'''

#원의 둘레와 원주율 계산하기
#변수 선언과 할당 
'''pi=3.14159265
r= 10
#변수 참조 
print("원주율=", pi)
print("반지름=", r)
print("원의 둘레=", 2* pi*r)
print("원의 넓이=", pi*r *r)'''
#변수 : 왼쪽의 값이 오른쪽 변수에 할당
#상수 : 파이, 이미 불변의 수를 상수라고함.ex)파이 값 , math()의 수학 함수

'''import math  #math 모듈 추가
r= 10'''
#변수 참조 
'''print("원주율=", math.pi)
print("반지름=", r)
print("원의 둘레=", math.pi*2 *r)
print("원의 넓이=", math.pi*r *r) 
'''
#날짜 시간 관련 함수 사용하여 출력하기
# 날짜 / 시간 과 관련된 기능을 가져옵니다
'''c'''
#현재 날짜/시간 을 구합니다
'''now = datetime.datetime.now()'''
#출력합니다
'''print(now.year,"년",sep="")
print(now.month,"월",sep="")
print(now.day,"일",sep="")
print(now.hour,"시",sep="")
print(now.minute,"분",sep="")
print(now.second,"초",sep="")
days = ['월','화','수','목','금','토','일']
print(days[now.weekday()],"요일",sep="")'''

'''import datetime

day1=datetime.date (2019,5,15)
day2=datetime.date.today()

diff=day2-day1
print(diff.days)
'''
#4로 나누어떨어지는 해는 윤년으로 한다. (2016년, 2020년, 2024년 등등)
#4와 100으로 나누어떨어지는 해는 평년으로 한다. (1900년, 2100년, 2200년)
#4, 100, 400으로 나누어떨어지는 해는 윤년으로 한다. (2000년, 2400년)

#윤년 구하기 1
year = int(input("연도를 입력하세요."))

if year % 400  == 0:
    result="윤년"
elif year % 100  == 0:
    result="평년"
elif year % 4  == 0:
    result="윤년"
else:
    result="평년"
    
print(year,"은",result,"입니다.",sep="")

#윤년 구하기2
year = int(input("윤년을 확인할 연도를 입력하세요"))# 확인할 연도를 입력 받습니다.

leap_year = False   # 윤년인지 아닌지 체크할 변수로 기본 평년으로 체크
if year % 4 == 0:               # 4로 나누어떨어지는지 확인
    if year % 100 == 0:         # 100으로 나누어떨어지는지 확인
        if year % 400 == 0:     # 400으로 나누어떨어지는지 확인
            leap_year = True    # 4, 100, 400으로 나누어떨어지면 윤년
    else:
        leap_year = True # 4로 나누어떨어지고, 100으로 나누어떨어지지 않으면 윤년

if leap_year:
    print(year, "년은 윤년입니다.")
else:
    print(year, "년은 평년입니다.")
