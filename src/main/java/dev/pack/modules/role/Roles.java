package dev.pack.modules.role;

import dev.pack.modules.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Roles implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, name = "role_name")
    private String role_name;

    @OneToMany(
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            mappedBy = "role_id"
    )
    private List<RolesMenus> rolesMenus;

    @OneToMany(
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            mappedBy = "role_id"
    )
    private List<User> users;

}
