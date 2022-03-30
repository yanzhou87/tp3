package library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LibraryUser")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "User_type", discriminatorType = DiscriminatorType.STRING)
public abstract class LibraryUser {

    @Id
    @GeneratedValue(generator = "libraryUser_seq")
    protected long id;

    protected String firstName;
    protected String lastName;
    protected int age;
    protected String address;


}
