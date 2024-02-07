package capstone.mauriziocrispino.MaurizioCrispino.Entities;
import capstone.mauriziocrispino.MaurizioCrispino.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "utenti")
@ToString
@NoArgsConstructor
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente")
    private long id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utente")
    @JsonIgnore
    private List<Feedback> feedbackList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

