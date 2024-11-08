import {getAllBooks,addBook,getBook,updateBook,goRemove,findBook} from './bookApi.js'

const init = () => {
    getAllBooks();

    const btnAll = document.querySelector("#btnAll");
    const btnCreate = document.querySelector("#btnCreate");
    const btnUpdate = document.querySelector("#btnUpdate");
    const btnSearch = document.querySelector("#btnSearch"); //검색
    const inputSearch =document.querySelector('#keyword');

    btnAll.onclick = () => { //모두보기 버튼
        getAllBooks();
    };
    inputSearch.onkeyup=(event)=>{
            //alert(event.keyCode);
            if(event.keyCode==13){
                const keyword=event.target.value;//input에 입력한 값 받기
            //alert(keyword)
            if(!keyword) return;
            findBook(keyword)
            }
    }
    //GET /api/books?search={검색어}
    btnSearch.onclick =()=>{
        //1.검색 키워드값 받아오기
        const keyword = document.querySelector("#keyword").value.trim();
        //2.유효성 체크하기
        if (!keyword) {
            alert("검색할 도서명을 입력해 주세요.");
            return;
        }
        //3.bookApi.js의 findBook(키워드)
        findBook(keyword);
    }

    btnCreate.onclick = () => { //등록 버튼

        //사용자가 입력한 값 받기
        const title=document.querySelector("#title").value;
        const publish=document.querySelector("#publish").value;
        const price=document.querySelector("#price").value;
        //유효성 체크
        if(!title||!publish||!price){
            alert("모든 값을 입력해주세요")
            return
        }
        if(isNaN(price)){ //is not a number 함수 숫자가 아니면 true를 반환
            alert("가격은 숫자여야 합니다")
            return
        }

        const tmpBook={
            "title":title,
            "publish":publish,
            "price":price
        }

        //api요청을 보내는 함수 호출
        addBook(tmpBook);
    };

    btnUpdate.onclick = () => { //수정 버튼
        const isbn=document.querySelector('#isbn').value;
        //사용자가 입력한 값 받기
        const title=document.querySelector("#title").value;
        const publish=document.querySelector("#publish").value;
        const price=document.querySelector("#price").value;
        //유효성 체크
        if(!title||!publish||!price){
            alert("모든 값을 입력해주세요")
            return
        }
        if(isNaN(price)){ //is not a number 함수 숫자가 아니면 true를 반환
            alert("가격은 숫자여야 합니다")
            return
        }
        const tmpBook={
            "id":isbn,
            "title":title,
            publish,    //속성명과 변수명이 같으면 축약해서 사용 가능
            price
        }
        console.log("tmpBook==========",tmpBook)
        updateBook(tmpBook);

    }
}
//윈도우 객체에 getBook함수 등록
window.getBook = getBook;
//모듈에서 정의된 변수나 함수는 해당 모듈에서만 접근 가능하다
//export하면 다른 모듈에서 import해서 사용 가능하다
//모듈화한 함수를 html에서 사용하려면 window객체에 속성으로 등록해줘야 한다.
window.goRemove = goRemove;
//이미지 파일 첨부
//document.querySelector("#imageUpload").addEventListener("change", function (event) {
//    const file = event.target.files[0];
//    const previewImage = document.querySelector("#previewImage");
//
//    if (file && file.type.startsWith("image/")) { // 이미지 파일인지 확인
//        const reader = new FileReader();
//        reader.onload = function (e) {
//            previewImage.src = e.target.result;    // 이미지 미리보기
//            previewImage.style.display = "block";  // 이미지 보이기
//        };
//        reader.readAsDataURL(file); // 파일을 Data URL로 읽어옴
//    } else {
//        alert("이미지 파일을 선택해 주세요.");
//        previewImage.style.display = "none"; // 이미지 숨기기
//    }
//});

document.addEventListener("DOMContentLoaded", init);