package net.zt.funcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.zt.funcode.domain.Author;
import net.zt.funcode.service.AuthorService;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	public Author findByLogin(String login);

}
