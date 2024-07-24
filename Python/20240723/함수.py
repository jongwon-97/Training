def print_n_times(n,value):
    # print_n_times _는 띄어쓰기를 구분해준다 여기서 times는 몇번 돌리겠다라는
    # 시각적 효과를 주는것이므로 지워도 괜찮다 지울떄는 아래쪽에 times를 같이 지워야 한다
    for i in range(n):
        #n 을 i 에 넣는다
        print(value)

print_n_times(value="안녕하세요",n=5) 

def p_n_t(*values,n=2):
#가변 매개변수는 한번 사용 가능하며 맨 뒤에 사용 해주어야 한다
#만약 앞에 사용하게 되면 뒤쪽에 함수를 못받는다
#키워드 매개변수를 사용하면 뒤에 오는게 가능하다 
    for i in range(n):
        for value in values:
            print(value)
        print()
        
p_n_t("즐거운","화요일","똑똑한","화요일\n스터디",n=3) #n=3이 키워드 매개변수

def mul(*values):
    result=1
    for value in values:
        result *= value
    return result     
print(mul(5,7,9,10))          