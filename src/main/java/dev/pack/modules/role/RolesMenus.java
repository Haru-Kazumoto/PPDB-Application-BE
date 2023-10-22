package dev.pack.modules.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles_menus")
public class RolesMenus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            unique = true,
            nullable = false,
            name = "path"
    )
    private String path;

    @Column(
            nullable = false,
            name = "role_id"
    )
    private Integer role_id;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "roles",nullable = false)
    @JsonIgnore
    private Roles roles;

}
