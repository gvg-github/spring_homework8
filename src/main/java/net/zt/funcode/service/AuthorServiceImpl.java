package net.zt.funcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.zt.funcode.domain.Author;
import net.zt.funcode.domain.Role;
import net.zt.funcode.repository.AuthorRepository;
import net.zt.funcode.repository.RoleRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	//имя роли по умолчанию
	private static final String DEFAULT_ROLE_NAME="user";

	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	@Transactional(readOnly=true)
	public Author get(Long id) {
	
		return authorRepo.findOne(id);
	}
	
	

	@Override
	@Transactional(readOnly=true)
	public List<Author> getAll() {

		return authorRepo.findAll();
	}

	@Override
	@Transactional
	public void save(Author author) {
        //поиск конкретной роли по имени
		Role role = roleRepo.findByName(DEFAULT_ROLE_NAME);
		//установка роли для автора
		author.setRole(role);
		//сохранения автора
		authorRepo.save(author);
	}
	
	
	@Override
	@Transactional
	public void remove(Author author) {

		authorRepo.delete(author);
	}


	@Override
	@Transactional(readOnly=true)
	public Author getByLogin(String login) {
		// TODO Auto-generated method stub
		Author author = authorRepo.findByLogin(login);
		return author;
	}

}
