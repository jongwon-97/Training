package com.kosmo.book.controller;

import com.kosmo.book.dto.BookDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // @Controller +@ResponseBody 를 합친 컨트롤러
@RequestMapping("/api/books")
public class BookRestController {

    @GetMapping("") // "/api/books"
    public BookDTO testDTO(){
        return new BookDTO(100L,"Restful api",
                "aaaa",5000,"1.jpg");
    }
}
