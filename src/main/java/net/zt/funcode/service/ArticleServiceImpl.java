package net.zt.funcode.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.zt.funcode.domain.Article;
import net.zt.funcode.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepo;
	
	
	
	@Override
	@Transactional(readOnly=true)
	public Page<Article> getAll(Pageable pageable) {
		
		Page<Article> articles = articleRepo.findAll(pageable);
		
		return articles;
	}

	@Override
	@Transactional(readOnly=true)
	public Article get(Long id) {
	
		return articleRepo.findOne(id);
	}

	@Override
	@Transactional
	public void save(Article article) {
		
		articleRepo.save(article);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Article> getByCategoryId(Long id, Pageable pageable) {
		
		Page<Article> articles = articleRepo.findByCategoryId(id, pageable);
		return articles;
	}
	
	@Override
	@Transactional
	public void update(Article article){
		
		Article updatableArticle = articleRepo.findOne(article.getId());
		updatableArticle.setContent(article.getContent());
		updatableArticle.setTitle(article.getTitle());
		if(article.getCategory()!=null)
			updatableArticle.setCategory(article.getCategory());
		articleRepo.save(updatableArticle);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		articleRepo.delete(id);
		
	}
}
