package dev.pack.modules.alur_ppdb;

import com.fasterxml.jackson.annotation.*;
import dev.pack.modules.user.User;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "alur_ppdb")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class AlurPpdb extends Timestamps {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(length = 2000)
    private String content;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer user_id; //FOR RELATION

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

}
