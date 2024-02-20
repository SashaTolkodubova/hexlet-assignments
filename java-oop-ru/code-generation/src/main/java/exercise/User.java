package exercise;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

// BEGIN
@Getter
@RequiredArgsConstructor
@Value
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
