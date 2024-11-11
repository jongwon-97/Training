const baseUrl="/books"
//GET /books ==> 모든 도서 가져오기
//GET //books/100 ==> 100번 도서 가져오기
//POST /books ==> 새로운 도서 정보 생성
//DELETE /books/15 ==> 15번 도서 정보 삭제하기
//PUT /books ==> 도서 정보 수정하기
    const addBook=async(formData)=>{
        try{
            const response= await fetch(baseUrl,{
                method:"POST",
                body: formData
            })
            /* FormData객체를 사용하면 아래 설정 안해도 됨
            headers:{
                 "Content-Type":"application/json"
            },
            */
            const data = await response.json();
            //alert(JSON.stringify(data))
            if(data.status =="success"){
                getAllBooks();

            }else{
                alert(data.message);
            }
        }catch(error){
            alert("Error:"+error);
            console.error(error);
        }
    }

    const getAllBooks= async (page,search)=>{
        try{
            if(!page) page=0;
            if(!search) search="";
            let pageSize=3;     //한페이지 당 목록 갯수(3개)
            let sortBy="id";    //오름차순
            let url=`${baseUrl}?page=${page}&size=${pageSize}&sortBy=${sortBy}&search=${search}`
            console.log("url:",url);
            const response= await fetch(url);
            const data = await response.json();
            //renderBooks(data);[x]
            renderBooks(data.books);//data.books ==>배열(도서목록)
            //페이지 네비게이션을 출력하는 함수 호출
            //renderPagination(data); //innerHTML이용
            renderPagination2(data);  //DOM이용

        }catch(error){
            alert("Error :"+error);
        }

    }
    //페이지 네비게이션 문자열을 html로 출력하는 함수
    const renderPagination=(data)=>{
        const pagination = document.querySelector("div#pagination");

        //const totalPages = data.totalPages;
        //const currentPage = data.currentPage;

        //ES6 배열,객체 비구조화 할당 -destructure assignment
        const {totalCount,totalPages,currentPage,pagingBlock,blockStart,blockEnd,isFirst,isLast,search}=data;

        //콘솔에 찍어보기
        console.log(totalCount,totalPages,currentPage,blockStart,blockEnd,search);

        let str=`<ul class="pagination">`;
        let dis= (blockStart > pagingBlock)? "":"disabled";

        //이전 블럭 링크 초기 blockStart: 1 , 6 , 11 , 16
        str+=`<li class="page-item ${dis}">
                <a class="page-link" href="#" onclick='getAllBooks(${blockStart-2},"${search}")'>
                Prev
                </a>
        </li>
        `
        //페이지 번호 링크
        for(let i=blockStart-1; i<blockEnd; i++){
            //현재 페이지에 active 클래스 주기 위한 변수
            let cls = (i==currentPage)?'class="page-link active':'class=page-link';

            str+=`
                    <li class="page-item">
                        <a ${cls} href='#' onclick='getAllBooks(${i},"${search}")'>
                            ${i+1}
                        </a>
                    </li>
            `;
        }// for ---------
        //다음 블럭 --------------------------------------------

        dis= (blockEnd < totalPages)? "":"disabled";

            //다음 블럭 링크 초기 blockEnd: 5 , 10 , 15 , 20
            str+=`<li class="page-item ${dis}">
                    <a class="page-link" href="#" onclick='getAllBooks(${blockEnd},"${search}")'>
                    Next
                    </a>
            </li>
            `


        str+=`<ul>`;
        pagination.innerHTML=str;

    }//----------------------------------------------

    //페이지 네비게이션을 DOM을 이용해서 동적으로 생성해서 붙이는 함수

    const renderPagination2=(pageData)=>{
    const pagination = document.getElementById('pagination');
    pagination.innerHTML = '';

    const { totalPages, currentPage, blockStart, blockEnd, isFirst, isLast, search } = pageData;

    const ul = document.createElement('ul');
    ul.classList.add('pagination');

    // 이전 블록 버튼
    const prevBlock = document.createElement('li');
    prevBlock.classList.add('page-item');
    if (blockStart > 1) {
        prevBlock.classList.remove('disabled');
    } else {
        prevBlock.classList.add('disabled');
    }
    prevBlock.innerHTML = `<a class="page-link" href="#" onclick="getAllBooks(${blockStart - 2},'${search}')">Prev</a>`;
    ul.appendChild(prevBlock);

    // 페이지 번호 버튼
    for (let i = blockStart - 1; i < blockEnd; i++) {
        const pageLi = document.createElement('li');
        pageLi.classList.add('page-item');
        if (i === currentPage) {
            pageLi.classList.add('active');
        }
        pageLi.innerHTML = `<a class="page-link" href="#" onclick="getAllBooks(${i},'${search}')">${i + 1}</a>`;
        ul.appendChild(pageLi);
    }

    // 다음 블록 버튼
    const nextBlock = document.createElement('li');
    nextBlock.classList.add('page-item');
    if (blockEnd < totalPages) {
        nextBlock.classList.remove('disabled');
    } else {
        nextBlock.classList.add('disabled');
    }
    nextBlock.innerHTML = `<a class="page-link" href="#" onclick="getAllBooks(${blockEnd},'${search}')">Next</a>`;
    ul.appendChild(nextBlock);

    pagination.appendChild(ul);

    //<span class='badge badge-danger'>34개</span>
    let span = document.createElement('h3')
    span.textContent="총 도서 갯수: " + pageData.totalCount + "개";
    pagination.appendChild(span);
    }//-----------------------------



    //도서 데이터를 html로 출력하는 함수
    const renderBooks=(data)=>{
        const result = document.querySelector("#result");
        let str="";
        //배열.forEach(콜백함수)
        data.forEach((book)=>{
                str+=`<tr>
                        <td>${book.title}</td>
                        <td>${book.publish}</td>
                        <td>${book.price}</td>
                        <td>${book.id}</td>
                        <td>
                            <button class="btn btn-warning"
                            onclick="getBook('${book.id}')">수정</button>
                            <button class="btn btn-secondary"
                            onclick="goRemove('${book.id}')">삭제</button>
                        </td>
                    </tr>`
        })

        result.innerHTML=str;
    }

    const getBook= async (id)=>{
        //alert(id)
        //GET /api /books/21
        //fetch()이용해서 요청 보내기 ===> 해당 도서정보를 json 유형으로 백엔드에서 보낸다
        //그걸 받아서 input에 value에 넣어준다
        //document.querySelector('#아이디').value = 받은값

        let url=baseUrl+`/${id}`;
        try{
            const response = await fetch(url)

            const data = await response.json();
            alert(JSON.stringify(data))

            setFormData(data);//조회한 도서 정보를 form에 출력하기
        }catch(err){
            alert('Error'+err);
        }
    }
    const setFormData=(data)=>{
        if(!data){
            alert("해당 도서 정보가 없습니다")
            return;
        }
        document.querySelector('#isbn').value = data.id;
        document.querySelector('#title').value = data.title;
        document.querySelector('#publish').value = data.publish;
        document.querySelector('#price').value = data.price;

        // 이미지 썸네일 설정
        document.querySelector(".img-thumbnail").src = "images/" + data.bookImage;

        let str='';
        if(data.bookImage){
            str=`<img src="/images/${data.bookImage}"
            class="img img-thumbnail" alt="${data.title}">`
        }else{
            str=`<img src="/images/noimage.jpg"
            class="img img-thumbnail" alt="${data.title}">`

        }
        document.querySelector('#bookImage').innerHTML=str;
        //수정 버튼 활성화 . 초기에는 disabled ===> 활성화
        const btnUpdate = document.querySelector('#btnUpdate');
        btnUpdate.removeAttribute('disabled')
    }
    //PUT /api/books
    const updateBook=async (formData)=>{
        try{
            const response=await fetch(baseUrl,{
                method:'PUT',

                body: formData
            })
            const data = await response.json();
            if(data.status == "success"){
                getAllBooks();
                //input 초기화
                clearInput();
                //수정버튼 비활성화
                document.querySelector('#btnUpdate')
                .setAttribute("disabled","disabled");
            }else{
                alert(data.message);
            }
        }catch(err){
            alert('Error:'+err)
        }

    }
    const clearInput=()=>{
                document.getElementById('isbn').value="";
                document.getElementById('title').value="";
                document.getElementById('publish').value="";
                document.getElementById('price').value="";
                document.getElementById('bookImage').innerHTML="";
                document.getElementById('title').focus();
    }

    const goRemove=async (id)=>{
        //alert(id);
        let yn=confirm(id+"번 도서를 삭제할까요?")
        if(!yn) return;

        let url = baseUrl+`/${id}`;
        const response = await fetch(url,{
            method:"DELETE"
        })
        const data=await response.json();
        getAllBooks();
    }

export{getAllBooks,addBook,getBook,updateBook,goRemove}