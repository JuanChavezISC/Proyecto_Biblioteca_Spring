package com.biblioteca.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.biblioteca.security.user.UserAccount;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(
		name ="tbl_usuario",
		uniqueConstraints = @UniqueConstraint(
				name= "email_unique", // Indica el nombre de la restriccion
				columnNames = "email_address"
		)
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

	@Id
	@SequenceGenerator(
			name = "usuario_sequence",
			sequenceName = "usuario_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			generator = "usuario_sequence",
			strategy = GenerationType.SEQUENCE
	)
	private Long usuarioId;
	private String nombre;
	private String apellido;
	
	@Column(
			name = "email_address",
			nullable = false
	)
	private String email;

    private String telefono;
    private String direccion;
    private String ciudad;

    private LocalDateTime fechaRegistro = LocalDateTime.now(); // Se llena automaticamente

    private Boolean activo = true;

	@OneToMany(
			mappedBy = "usuario"
			)
	private List<Prestamo> prestamo;

    @OneToOne(mappedBy = "usuario")
    private UserAccount userAccount;
	// Constructores


	public List<Prestamo> getPrestamo() {
		return prestamo;
	}


}
