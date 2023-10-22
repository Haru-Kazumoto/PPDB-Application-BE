package dev.pack.modules.data.role;

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
    @JoinColumn(name = "roles",nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Roles role_id;

}
