package dev.pack.modules.newdata.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.pack.modules.newdata.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "roles"
    )
    private RolesMenus rolesMenus;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userId", updatable = false)
//    @JsonIgnore
//    private User userId;

}
