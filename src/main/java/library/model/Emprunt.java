package library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate dateEmprunt;
    private LocalDate dateReturn;
    private boolean isReturn = false;

    public Emprunt(Client client, Exemplaire exemplaire) {
        this.client = client;
        this.exemplaire = exemplaire;
    }

    public Emprunt(Client client, Exemplaire exemplaire, LocalDate dateReturn,boolean isReturn) {
        this.client = client;
        this.exemplaire = exemplaire;
        this.dateEmprunt = dateReturn;
        this.isReturn = isReturn;
    }

    public Emprunt(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", client=" + client.firstName +
                ", exemplaire=" + exemplaire +
                ", date=" + dateEmprunt +
                ", isReturn=" + isReturn +
                '}';
    }
}
