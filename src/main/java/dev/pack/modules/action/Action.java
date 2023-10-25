package dev.pack.modules.action;

import dev.pack.modules.enums.Role;
import dev.pack.utils.Timestamps;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "action")
public class Action extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String path;

    @Nullable
    private String parameter;

    private String method;

    private String module;

    private Role roleAccess;

    private String descriptionAction;

    private Boolean isBug = true;

    private String bugDescription;

    @PrePersist
    protected void onCreate(){
        if(!isBug){
            this.bugDescription = null;
        }
    }

}