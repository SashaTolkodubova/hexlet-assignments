package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = repository.findAll();
        var result = books.stream()
                .map(bookMapper::map)
                .toList();
        return result;
    }

    public BookDTO create(BookCreateDTO bookData) {
        var book = bookMapper.map(bookData);
        repository.save(book);
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public BookDTO findById(Long id) {
        var book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bool with id " + id + " not found"));
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        var book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bool with id " + id + " not found"));
        bookMapper.update(bookData, book);
        repository.save(book);
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
