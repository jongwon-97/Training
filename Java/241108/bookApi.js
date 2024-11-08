const baseUrl="/api/books"
//GET /api/books ==> 모든 도서 가져오기
//GET /api/books/100 ==> 100번 도서 가져오기
//POST /api/books ==> 새로운 도서 정보 생성
//DELETE /api/books/15 ==> 15번 도서 정보 삭제하기
//PUT /api/books ==> 도서 정보 수정하기
const addBook=async(newBook)=>{
    try{
        const response= await fetch(baseUrl,{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify(newBook)
        })
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

const getAllBooks= async ()=>{
    try{
        const response= await fetch(baseUrl);
        const data = await response.json();
        renderBooks(data);
    }catch(error){
        alert("Error :"+error);
    }

}
const getAllBooks_old=()=>{
       fetch(baseUrl,{
            method:"GET",
            cache:"no-cache"
       })
       .then((response)=>{
            if(!response.ok){
                throw new Error("Error: 네트워크 오류");
            }
            return response.json();
       })
       .then((data)=>{
           // alert(JSON.stringify(data));
           renderBooks(data);
       })
       .catch((error)=>{ //에러가 날 경우 catch()
            alert(' error:' + error);
       })
      }
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
    const updateBook=async (tmpBook)=>{
        try{
            const response=await fetch(baseUrl,{
                method:'PUT',
                headers:{
                    'Content-Type':'application/json'
                },
                body: JSON.stringify(tmpBook)
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
    const findBook = async (keyword) => {
        let url = baseUrl+`?search=${keyword}`;
        try {

            const response=await fetch(url);
            const data = await response.json();

            renderBooks(data);
        } catch (error) {
            alert("Error: " + error);
        }
    };

export{getAllBooks,addBook,getBook,updateBook,goRemove,findBook}