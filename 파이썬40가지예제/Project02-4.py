import socket
import requests
import re

in_addr=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
in_addr.connect(("www.google.co.kr",443))
print("내부 IP: ",in_addr.getsockname()[0])

req = requests.get("http://ipconfig.kr")
req.encoding = 'utf-8'  # 페이지 인코딩을 UTF-8로 설정
out_addr = re.search(r'(\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3})', req.text)[1]
print("외부 IP: ",out_addr)