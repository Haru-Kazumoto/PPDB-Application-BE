package dev.pack.modules.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.pack.modules.enums.Role;
import dev.pack.modules.alur_ppdb.AlurPpdb;
import dev.pack.modules.role.Roles;
import dev.pack.modules.student.Student;
import dev.pack.modules.token.Token;
import dev.pack.utils.CustomDateSerializer;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "users")
public class User extends Timestamps implements UserDetails{

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true)
  private String username; //ini jadi nomor whatsapp

  private String fullname;

  @JsonIgnore
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role; //changed

  //Relasi role model
  @JoinColumn(name = "role_id",nullable = false)
  @ManyToOne()
  private Roles role_id;
  
  @JsonSerialize(using = CustomDateSerializer.class)
  @Temporal(TemporalType.TIMESTAMP)
  private Date joinAt;

  @JsonIgnore
  private boolean isAdmin = false;

  @OneToMany(
          mappedBy = "userId",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JsonIgnore
  private List<Token> tokens = new ArrayList<>();

  @OneToOne(
          cascade = CascadeType.ALL,
          orphanRemoval = true,
          mappedBy = "userId"
  )
  private Student student;

  @PrePersist
  protected void onCreate(){
      this.joinAt = new Date();
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}
