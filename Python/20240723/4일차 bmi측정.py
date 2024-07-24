#기존의 info.txt를 열어서 for문을 실행한 후 다른이름으로 저장하기
#기능이 되는 구조임: 파일명 new_info_result.txt
#info.txt 파일의 내용을 읽어 BMI를 계산하고 결과를 저장합니다.
with open("info.txt", "r", encoding='utf-8') as file:
    lines = file.readlines()

# info_result.txt로 결과를 저장합니다.
output_file_name = "info_result.txt"
with open(output_file_name, "w", encoding='utf-8') as output_file:
    for line in lines:
        # 변수를 선언합니다.
        (name, weight, height) = line.strip().split(", ")

        # 데이터가 문제없는지 확인합니다: 문제가 있으면 지나감
        if (not name) or (not weight) or (not height):
            continue

        # 결과를 계산합니다.
        bmi = int(weight) / ((int(height) / 100) ** 2)
        result = ""
        if 25 <= bmi:
            result = "과체중"
        elif 18.5 <= bmi:
            result = "정상 체중"
        else:
            result = "저체중"

        # 결과를 출력합니다.
        output = '\n'.join([
            "이름: {}",
            "몸무게: {}",
            "키: {}",
            "BMI: {}",
            "결과: {}"
        ]).format(name, weight, height, bmi, result)

        print(output)
        print()

        # 결과를 파일에 저장합니다.
        output_file.write(output + "\n\n")  # 각 결과 뒤에 빈 줄 추가

print(f"결과가 '{output_file_name}' 파일에 저장되었습니다.")

# info_result.txt 파일을 다시 읽어 새로운 이름으로 저장합니다.
new_output_file_name = "new_info_result.txt"
with open(output_file_name, "r", encoding='utf-8') as read_file:
    content = read_file.read()

with open(new_output_file_name, "w", encoding='utf-8') as new_output_file:
    new_output_file.write(content)

print(f"결과가 '{new_output_file_name}' 파일에 다시 저장되었습니다.")