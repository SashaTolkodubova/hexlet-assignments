package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAll() {
        var authors = repository.findAll();
        var result = authors.stream()
                .map(authorMapper::map)
                .toList();
        return result;
    }

    public AuthorDTO create(AuthorCreateDTO authorData) {
        var author = authorMapper.map(authorData);
        repository.save(author);
        var authorDto = authorMapper.map(author);
        return authorDto;
    }

    public AuthorDTO findById(Long id) {
        var author = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        var authorDto = authorMapper.map(author);
        return authorDto;
    }

    public AuthorDTO update(AuthorUpdateDTO authorData, Long id) {
        var author = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        authorMapper.update(authorData, author);
        repository.save(author);
        var authorDto = authorMapper.map(author);
        return authorDto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
