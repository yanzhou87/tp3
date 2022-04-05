package library.forms;

import library.model.Client;
import lombok.Data;

@Data
public class SaveClientForm {
    private String id;
    private final String firstName;
    private final String lastName;
    private final int age;

    public SaveClientForm(String id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public SaveClientForm(Client client){
        this(Long.toString(client.getId()),client.getFirstName(),client.getLastName(),client.getAge());
    }

    public Client toClient(){
        return new Client(firstName,lastName,age);
    }
}
