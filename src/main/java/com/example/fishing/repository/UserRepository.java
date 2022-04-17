package com.example.fishing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fishing.entity.User;

/*DAOパターンのデータアクセス部分に当たる
 * ここでデータベースのアクセスアクセス操作を行う
 * */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUsername(String username);
}
