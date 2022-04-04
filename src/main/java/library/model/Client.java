package library.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("client")

public class Client extends LibraryUser {

    @OneToMany(mappedBy = "client",  cascade = CascadeType.ALL)
    private List<Emprunt> emprunts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Amende> amendes = new ArrayList<>();

    public Client(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
