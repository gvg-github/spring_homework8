package net.zt.funcode.service;

import java.util.List;

import net.zt.funcode.domain.Author;
import net.zt.funcode.domain.Role;

public interface AuthorService {
	
	
	public Author get(Long id);
	
	public Author getByLogin(String login);
	
	public List<Author> getAll();
	
	public void save(Author author);
	
	public void remove(Author author);
	

}
