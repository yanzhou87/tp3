package library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Amende {
    @Id
    @GeneratedValue( generator = "amande_seq")
    private long id;
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
    private long amendeForDay = 21L;
    private long sommeAmende;
}
