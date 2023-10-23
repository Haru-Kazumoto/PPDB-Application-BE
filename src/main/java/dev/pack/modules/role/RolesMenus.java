package dev.pack.modules.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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


    @JoinColumn(name = "role_id",nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE)
    private Roles role_id;

}
