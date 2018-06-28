package net.zt.funcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.zt.funcode.domain.Category;
import net.zt.funcode.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long > {
	
	public Role findByName(String name);

}
