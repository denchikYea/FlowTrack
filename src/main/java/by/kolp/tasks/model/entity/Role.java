package by.kolp.tasks.model.entity;

import by.kolp.tasks.model.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleName name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<User> users;

}
