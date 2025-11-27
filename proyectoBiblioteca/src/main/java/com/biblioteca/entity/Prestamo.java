package com.biblioteca.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(
		name = "tbl_prestamo"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {

	@Id
	@SequenceGenerator(
			name = "prestamo_sequence",
			sequenceName = "prestamo_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			generator = "prestamo_sequence",
			strategy = GenerationType.SEQUENCE
			)
	private Long prestamoId;
	@ManyToOne
	@JoinColumn(
			name = "libro_id",
			referencedColumnName = "libroId"
			)
	private Libro libro;
	@ManyToOne
	@JoinColumn(
			name = "usuario_id",
			referencedColumnName = "usuarioId"
			)
	private Usuario usuario;
	
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	private boolean devuelto;
	

	
	
	
	
}
