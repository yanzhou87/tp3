package library.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("client")

public class Client extends LibraryUser {

    @OneToMany(mappedBy = "client",  cascade = CascadeType.ALL)
    private List<Emprunt> emprunts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Amende> amendes = new ArrayList<>();

}
