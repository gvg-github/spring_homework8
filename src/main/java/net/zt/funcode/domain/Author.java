package net.zt.funcode.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="author")
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Pattern(regexp="[A-Z][a-zA-Z]*",message="{validation.author.firstname.pattern}")
	@Size(min=2, max=50, message="{validation.author.firstname.size}")
	@Column(name="first_name")
	private String firstname;
	
	@Pattern(regexp="[a-zA-z]+([ '-][a-zA-Z]+)*",message="{validation.author.lastname.pattern}")
	@Size(min=2, max=50, message="{validation.author.lastname.size}")
	@Column(name="last_name")
	private String lastname;
	
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="{validation.author.email.pattern}")
	@Column(name="email")
	private String email;
	
    @ManyToOne
    @JoinColumn(name="role_id")
	private Role role;
    
    @Pattern(regexp="^[a-zA-Z0-9._-]{3,}$", message="{validation.author.login.pattern}")
    @Column(name="login")
    private String login;
    
    @Pattern(regexp=".{8,}", message="{validation.author.password.pattern}")
    @Column(name="password")
    private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy="author", fetch=FetchType.LAZY)
	private List<Article> articles;
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
