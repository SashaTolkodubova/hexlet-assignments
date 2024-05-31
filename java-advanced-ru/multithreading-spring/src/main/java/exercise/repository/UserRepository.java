package exercise.repository;

import exercise.model.User;
import io.r2dbc.spi.Parameter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


// BEGIN
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long>{
}
// END
