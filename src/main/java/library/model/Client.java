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

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Amende> amendes = new ArrayList<>();

    public Client(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", emprunts='" + emprunts + '\'' +
                '}';
    }

    public void addEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
        emprunt.setClient(this);
    }

    public void addAmende(Amende amende) {
        amendes.add(amende);
        amende.setClient(this);
    }
}
