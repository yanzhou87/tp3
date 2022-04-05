package library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPRUNT")
public class Emprunt {
    @Id
    @GeneratedValue(generator = "emprunt_seq")
    private long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToOne
    @JoinColumn(name = "EXEMPLAIRE_ID")
    private Exemplaire exemplaire;

    private LocalDateTime date;
    private boolean isReturn = false;

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", client=" + client.firstName +
                ", exemplaire=" + exemplaire +
                ", date=" + date +
                ", isReturn=" + isReturn +
                '}';
    }
}
