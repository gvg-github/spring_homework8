package net.zt.funcode.service;

import java.util.List;

import net.zt.funcode.domain.Category;


public interface CategoryService {
	

	public Category get(Long id);
	
	public List<Category> getAll();
	
	public void save(Category category);
	
	public void remove(Category category);

}
