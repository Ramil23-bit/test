package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name must not be blank")
    private String name;

    @Column(name = "phone")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,15}$", message = "Invalid phone number")
    private String phoneNumber;

    @Column(name = "avatar")
    @URL(message = "Avatar must be a valid URL")
    private String avatar;

    @JoinColumn(name = "role_id", nullable = false)
    @ManyToOne
    @NotNull(message = "Role must be provided")
    private Role role;
}
