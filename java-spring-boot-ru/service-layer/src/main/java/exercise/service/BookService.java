package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper mapper;

    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        return mapper.map(books);
    }

    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return mapper.map(book);
    }

    public BookDTO create(BookCreateDTO dto) {
        Book book = mapper.map(dto);
        bookRepository.save(book);
        return mapper.map(book);
    }

    public BookDTO update(Long id, BookUpdateDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        mapper.update(dto, book);
        bookRepository.save(book);
        return mapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
