package com.kosmo.book.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.basic.entity.BookEntity;
import com.kosmo.book.dto.BookDTO;
import com.kosmo.book.entity.Book;
import com.kosmo.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;
    //Entity와 DTO객체를 변환할때 사용

    @Override
    public List<BookDTO> getAllbooks(String search) {
        List<Book> list = null;
        if(search.isBlank()) {
            list = bookRepository.findAll();
        }else{
            //list= title로 검색한 결과 받아오는 메서드 호출
            list=bookRepository.findByTitleContainingIgnoreCase(search);
        }
        //repository에서 받은 데이터는 엔티티 객체
        //=> 표현계층에 전달할 때는 DTO로 변환해야 한다.
        List<BookDTO>arrList =list.stream().map(entity
                ->new BookDTO(entity.getId(), entity.getTitle(),
                entity.getPublish(), entity.getPrice(), entity.getBookImage()))
                .collect(Collectors.toList());
        return arrList;
    }

    @Override
    public BookDTO getBookInfo(Long id) {
        Optional<Book> opt = bookRepository.findById(id);
        return opt.map(entity -> new BookDTO(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getPublish(),
                        entity.getPrice(),
                        entity.getBookImage()))
                .orElse(null);
    }

    @Override
    public Book saveBook(BookDTO bookDTO) {

        Book createEntity=Book.builder()
                .title(bookDTO.getTitle())
                .publish(bookDTO.getPublish())
                .price(bookDTO.getPrice())
                .bookImage((bookDTO.getBookImage()))
                .build();
        Book entity=bookRepository.save(createEntity);
        return entity;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }

    @SneakyThrows
    @Override
    public Book updateBook(BookDTO bookDTO) {
        //Entitiy와 DTO 간에 property명(멤버변수)이같으면 objectMapper이용해서 변환가능

        //Book tmpEntity = objectMapper.convertValue(bookDTO,Book.class);

        Book existEntity=bookRepository.findById(bookDTO.getId())
                .orElseThrow(()->new IllegalAccessException("no data found"));

        BeanUtils.copyProperties(bookDTO,existEntity,"id");
        //bookDTO가 가진 프로퍼티 값을 existEntity 로 카피를 하ㅚ "id"는 제외시킴

        Book updateEntity=bookRepository.save(existEntity);//tmpEntity);
        return updateEntity;
    }
}
