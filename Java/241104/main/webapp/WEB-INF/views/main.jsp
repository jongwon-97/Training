<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>인기 쇼핑몰</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script>
        function updateTime() {
            const now = new Date();
            const timeString = now.toLocaleTimeString('ko-KR', { hour12: false });
            document.getElementById('current-time').textContent = timeString;
        }

        setInterval(updateTime, 1000); // 1초마다 시간 업데이트
        window.onload = updateTime; // 페이지 로드 시 시간 업데이트
    </script>
</head>
<body>
    <main>
        <section class="hero">
            <img src="${pageContext.request.contextPath}/images/your-image.jpg" alt="배너 이미지" class="hero-image">
            <div class="time-display">
                <h2>현재 시간</h2>
                <p id="current-time"></p>
            </div>
        </section>

        <section class="features">
            <h3>주요 기능</h3>
            <div class="feature-list">
                <div class="feature-item">
                    <h4>신속한 배송</h4>
                    <p>빠르고 안전한 배송 서비스를 제공합니다.</p>
                </div>
                <div class="feature-item">
                    <h4>Kotoki 고객 지원</h4>
                    <p>언제든지 고객님의 문의를 도와드립니다.</p>
                </div>
                <div class="feature-item">
                    <h4>안전한 결제</h4>
                    <p>안전하고 간편한 결제 시스템을 제공합니다.</p>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
