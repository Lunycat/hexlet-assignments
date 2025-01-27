package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(long id, User data) {
        return userRepository.findById(id)
                .flatMap(user -> {
                    user.setEmail(data.getEmail());
                    user.setFirstName(data.getFirstName());
                    user.setLastName(data.getLastName());
                    return userRepository.save(user);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    public Mono<Void> delete(long id) {
        return userRepository.deleteById(id);
    }
}
