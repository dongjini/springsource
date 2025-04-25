package com.example.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDTO;
import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public Long insert(BookDTO dto) {
        // dto => entity
        Book book = modelMapper.map(dto, Book.class);

        return bookRepository.save(book).getCode();
    }

    public BookDTO read(Long code) {
        Book book = bookRepository.findById(code).get();
        return modelMapper.map(book, BookDTO.class);
    }

    public List<BookDTO> readAll() {
        List<Book> list = bookRepository.findAll();
        log.info(list);
        // entity -> dto
        // modelMapper.map(book, BookDTO.class);
        List<BookDTO> books = list.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());

        // List<BookDTO> books = new ArrayList<>();
        // for (Book book : list) {
        // books.add(BookDTO.builder().code(book.getCode()).title(book.getTitle()).author(book.getAuthor()).build());
        // }

        log.info(books);
        return books;
    }

    public void modify(BookDTO dto) {

        Book book = bookRepository.findById(dto.getCode()).get();
        book.setPrice(dto.getPrice());
        bookRepository.save(book);
    }

    public void remove(Long code) {

        // repository 호출
        bookRepository.deleteById(code);
    }
}
