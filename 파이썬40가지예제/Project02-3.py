import requests
import re

# 요청과 함께 인코딩 설정
req = requests.get("http://ipconfig.kr")
req.encoding = 'utf-8'  # 페이지 인코딩을 UTF-8로 설정

out_addr = re.search(r'(\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})', req.text)[1]
print(out_addr)
