package dev.pack.modules.action;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.pack.modules.authorization.Role;
import dev.pack.utils.CustomDateSerializer;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpMethod;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "action_tbl")
public class Action extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String path;

    private String method;

    private Role roleAccess;

    @Column(columnDefinition = "VARCHAR(255)")
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
