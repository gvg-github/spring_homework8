package net.zt.funcode.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import net.zt.funcode.domain.Article;

public interface ArticleService {
	
	public Page<Article> getAll(Pageable pageable);
	
	public Article get(Long id);
	
	public Page<Article> getByCategoryId(Long id, Pageable pageable);
	
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	public void save(Article article);
	
	@PreAuthorize("hasAuthority('admin')")
	public void update(Article article);
	
	
	@PreAuthorize("hasAuthority('admin')")
	public void delete(Long id);

}
