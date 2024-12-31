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

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper mapper;

    public List<AuthorDTO> getAll() {
        List<Author> authors = authorRepository.findAll();
        return mapper.map(authors);
    }

    public AuthorDTO findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return mapper.map(author);
    }

    public AuthorDTO create(AuthorCreateDTO dto) {
        Author author = mapper.map(dto);
        authorRepository.save(author);
        return mapper.map(author);
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        mapper.update(dto, author);
        authorRepository.save(author);
        return mapper.map(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
