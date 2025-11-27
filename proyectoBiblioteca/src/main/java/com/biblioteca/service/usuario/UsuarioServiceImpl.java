package com.biblioteca.service.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.biblioteca.auth.dto.RegistroUsuarioDto;
import com.biblioteca.dto.UsuarioDto;
import com.biblioteca.security.role.RoleRepository;
import com.biblioteca.security.service.UserAccountService;
import com.biblioteca.security.user.UserAccount;
import com.biblioteca.security.user.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.entity.Usuario;
import com.biblioteca.mapper.UsuarioMapper;
import com.biblioteca.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private final UsuarioMapper usuarioMapper;
	private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
	private final IUsuarioRepository usuarioRepository;
    private final UserAccountService userAccountService;
    private final PasswordEncoder encoder;
	
	public UsuarioServiceImpl(UsuarioMapper usuarioMapper, UserAccountRepository userAccountRepository, RoleRepository roleRepository, IUsuarioRepository usuarioRepository, UserAccountService userAccountService, PasswordEncoder encoder) {
		super();
		this.usuarioMapper = usuarioMapper;
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.userAccountService = userAccountService;
        this.encoder = encoder;
    }

	@Override
	@Transactional(readOnly = true)
	public UsuarioDto findUserById(Long id) {
		Usuario user = usuarioRepository.findById(id).orElseThrow(() -> 
		new RuntimeException("Usuario no encontrado con el id " + id));
		
		return usuarioMapper.toDto(user);
	}

	// Encontrar Todos los Usuarios
	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDto> findAllUsers() {
		return usuarioRepository.findAll()
				.stream()
				.map(u -> usuarioMapper.toDto(u))
				.collect(Collectors.toList());
	}

	// Guardar todos los usuarios
	@Override
	public UsuarioDto saveUser(RegistroUsuarioDto usuario) {

        // 1. Crear Usuario (sinUserAccount)
		Usuario usuarioEntity = usuarioMapper.toEntity(usuario);
        usuarioEntity.setFechaRegistro(LocalDateTime.now());
        usuarioEntity.setActivo(true);
		
		Usuario guardado = usuarioRepository.save(usuarioEntity);

        // 2. Crear UserAccount llamando al servicio de seguridad
        userAccountService.register(usuario, guardado);
		
		return usuarioMapper.toDto(guardado);
	}

	// Actualizar Usuario
	@Override
    @Transactional
	public UsuarioDto updateUser(Long id, UsuarioDto usuario) {
		
		Usuario usuarioDb = usuarioRepository.findById(id).get();
		if (Objects.nonNull(usuario.nombre()) && !"".equalsIgnoreCase(usuario.nombre())) {
			usuarioDb.setNombre(usuario.nombre());
		}
		if (Objects.nonNull(usuario.apellido()) && !"".equalsIgnoreCase(usuario.apellido())) {
			usuarioDb.setApellido(usuario.apellido());
		}
		if (Objects.nonNull(usuario.email()) && !"".equalsIgnoreCase(usuario.email())) {
			usuarioDb.setEmail(usuario.email());
		}
        if (Objects.nonNull(usuario.telefono()) && !"".equalsIgnoreCase(usuario.telefono())) {
            usuarioDb.setTelefono(usuario.telefono());
        }
        if (Objects.nonNull(usuario.direccion()) && !"".equalsIgnoreCase(usuario.direccion())) {
            usuarioDb.setDireccion(usuario.direccion());
        }
        if (Objects.nonNull(usuario.ciudad()) && !"".equalsIgnoreCase(usuario.ciudad())) {
            usuarioDb.setCiudad(usuario.ciudad());
        }
		Usuario actualizado = usuarioRepository.save(usuarioDb);
		return usuarioMapper.toDto(actualizado);
	}

	@Override
    @Transactional
	public void deleteUser(Long id) {
		usuarioRepository.deleteById(id);
	}

	
}
