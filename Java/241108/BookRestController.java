package com.kosmo.book.controller;

import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.dto.ResponseDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController

@RequestMapping("/api/books")
@RequiredArgsConstructor //생성자 인젝션(final필드 주입)
@Slf4j
public class BookRestController {
    private final BookService bookService;
    @PostMapping("")//파일 업로드 기능이 들어간경우
    public ResponseEntity<ResponseDTO>bookSaveAndUp(BookDTO dto,
                                                    @RequestParam(name= "mbookImage",required = false)MultipartFile mbookImage,
                                                    HttpServletRequest req){

        log.info("dto ======{}",dto);
        log.info("mbookImage ======{}",mbookImage);
        String fname=fileUpload(req,mbookImage); // ==> 파일 업로드 처리후 파일명 반환
        dto.setBookImage(fname);
        Book entity=bookService.saveBook(dto);
        log.info("entity = {}", entity);
        String status = (entity == null) ? "fail" : "success";
        String message = (entity == null) ? "도서 등록 실패" : "도서 등록 완료";
        ResponseDTO resDTO = new ResponseDTO(status, message);

        return ResponseEntity.status(200).body(resDTO);


    }

    public String fileUpload(HttpServletRequest req,MultipartFile mbookImage){
        log.info("mbookImage ======{}",mbookImage);
        String upDir = req.getServletContext().getRealPath("/images");
        log.info("upDir=========={}",upDir);
        File dir = new File (upDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String fname=null;
        try{
            fname= UUID.randomUUID().toString()+"_"+mbookImage.getOriginalFilename();
            if(mbookImage!=null) {
                if (!mbookImage.isEmpty()) {
                    mbookImage.transferTo(new File(upDir, fname));
                    log.info("파일 업로드 성공" + fname);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File Upload Error");
        }
        return fname;
    }


    @PostMapping("/old")//파일 업로드 기능 없음
    public ResponseEntity<ResponseDTO> bookSave(@RequestBody BookDTO dto) {
        //json 데이터를 받으려면 dto 앞에 @RequestBody 를 붙여주자
        log.info("dto = {}", dto);

        Book entity = bookService.saveBook(dto);
        log.info("entity = {}", entity);
        String status = (entity == null) ? "fail" : "success";
        String message = (entity == null) ? "도서 등록 실패" : "도서 등록 완료";
        ResponseDTO resDTO = new ResponseDTO(status, message);

        log.info("resDTO = {}", resDTO);

        return ResponseEntity.status(200).body(resDTO);
    }


    //GET /api/books 모든 도서 정보 가져오기
    @GetMapping("")
    public List<BookDTO>getAllbooks(@RequestParam(defaultValue = "")
                                    String search){
        return bookService.getAllbooks(search);
    }

    @GetMapping("/{id}")
    public BookDTO getBookInfo(@PathVariable("id") Long id) {
        BookDTO result = bookService.getBookInfo(id);
        log.info("dto======{}", result);
        return result;
    }
    @PutMapping("")
    public ResponseEntity<ResponseDTO>updateBookFileUp(BookDTO bookDTO,
                                                       @RequestParam(name="mbookImage") MultipartFile mbookImage,
                                                 HttpServletRequest req){
        log.info("bookDTO========{}",bookDTO);
        String fname=fileUpload(req,mbookImage);
        bookDTO.setBookImage((fname));

        Book updateBook=bookService.updateBook(bookDTO);
        String status =(updateBook==null)?"fail":"success";
        String message =(updateBook==null)?"도서정보 수정 실패":"수정 완료";
        ResponseDTO responseDTO=new ResponseDTO(status,message);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/old") //파일 업로드 안할 경우
    public ResponseEntity<ResponseDTO>updateBook(@RequestBody BookDTO bookDTO){
        log.info("bookDTO========{}",bookDTO);

        Book updateBook=bookService.updateBook(bookDTO);
        String status =(updateBook==null)?"fail":"success";
        String message =(updateBook==null)?"도서정보 수정 실패":"수정 완료";
        ResponseDTO responseDTO=new ResponseDTO(status,message);
        return ResponseEntity.ok(responseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO>
        deleteBook(@PathVariable("id") Long id){
        log.info("id======{}", id);
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ResponseDTO("success","삭제완료"));
    }



    @GetMapping("/json")
    public BookDTO testDTO(){
        return new BookDTO(100L,"Restful API",
                "길벗 출판사",5000,"1.jpg");
    }
    @GetMapping("/jsonArr")
    public List<BookDTO> testArrayList(){
        BookDTO b1 = new BookDTO(100L,"Restful API",
                "길벗 출판사",5000,"1.jpg");
        BookDTO b2 = new BookDTO(200L,"SpringBoot",
                "생능 출판사",25000,"2.jpg");
        BookDTO b3 = new BookDTO(300L,"JPA",
                "에이콘 출판사",35000,"3.jpg");
        return List.of(b1,b2,b3);
    }
}
