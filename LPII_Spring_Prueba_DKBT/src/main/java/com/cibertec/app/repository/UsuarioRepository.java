package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value="SELECT u.id_usuario,u.usuario,e.id_empleado,u.clave,u.id_rol FROM usuario u INNER JOIN empleado e ON u.id_empleado = e.id_empleado where u.usuario = :username",nativeQuery = true)
	public Usuario findByUsuario(@Param("username") String username);
	
	@Query(value="SELECT u.id_usuario,u.usuario, e.id_empleado,u.clave,u.id_rol FROM usuario u INNER JOIN empleado e ON u.id_empleado = e.id_empleado where u.usuario = :username and u.clave = :clave",nativeQuery = true)
	public Usuario findByUsuarioAndClave(@Param("username") String username,@Param("clave") String clave);
	
}
