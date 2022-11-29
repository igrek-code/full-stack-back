package univrouen.full_stack_back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name="Shop")
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    @NotBlank(message = "Name is mandatory")
    private  String name;

    @Column(name="closed")
    private  Boolean closed;

    @Column
    private String prenom;

}
