package com.santosediego.VidasPorVidas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosediego.VidasPorVidas.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUser(String user);
}
