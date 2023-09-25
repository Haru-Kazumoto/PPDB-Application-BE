package dev.pack.modules.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.pack.modules.authorization.Role;
import dev.pack.modules.ppdbFlow.PpdbFlow;
import dev.pack.modules.token.Token;
import dev.pack.utils.CustomDateSerializer;
import dev.pack.utils.Timestamps;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static dev.pack.modules.authorization.Role.ADMIN;
import static dev.pack.modules.authorization.Role.USER;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tbl")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class User extends Timestamps implements UserDetails {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;

  @JsonIgnore
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

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
  private List<Token> tokens;

  /**
   * ADMIN DATA
   */
  @OneToMany(
          mappedBy = "userId",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  private List<PpdbFlow> ppdbFlows;

  /**
   * STUDENT DATA
   */
  //--

  @PrePersist
  protected void onCreate(){
      this.joinAt = new Date();

      if(role == ADMIN){
        isAdmin = true;
      }
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
