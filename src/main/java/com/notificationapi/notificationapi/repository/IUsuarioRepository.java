package com.notificationapi.notificationapi.repository;

import com.notificationapi.notificationapi.domain.UsuarioDomain;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioRepository  extends ReactiveCrudRepository<UsuarioDomain, String> {

    @Query(value = "SELECT * FROM usuario WHERE correo_electronico =?1",nativeQuery = true)
    Mono<UsuarioDomain> findByCorreoElectronico(@Param("correo_electronico") String correoElectronico);
}
