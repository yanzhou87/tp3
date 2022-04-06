package library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Amende {
    @Id
    @GeneratedValue( generator = "amande_seq")
    private long id;
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
    private long amendeForDay = 2L;
    private long sommeAmende;

    public Amende(Client client,  long duration) {
        this.client = client;
        this.sommeAmende = duration * amendeForDay;
    }

    @Override
    public String toString() {
        return "Amende{" +
                "id=" + id +
                ", client=" + client.firstName +
                ", amendeForDay=" + amendeForDay +
                ", sommeAmende=" + sommeAmende +
                '}';
    }
}
