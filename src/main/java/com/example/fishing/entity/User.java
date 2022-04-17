package com.example.fishing.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/*エンティティクラスであることを示す*/
@Entity
/*マッピングさせるテーブル名を指定する*/
@Table(name = "users")
@Data
public class User extends AbstractEntity implements UserDetails, UserInf {
	private static final long serialVersionUID = 1L;
	
	/*列挙型で定数を定義*/
	public enum Authority {   
		ROLE_USER, ROLE_ADMIN
	};
	/*継承した一つ前のコンストラクタを呼び出し*/
	public User() {
		super();
	}
	/*各変数に値を代入する*/
	public User(String email, String name, String password, Authority authority, String live) {
		this.username = email;
		this.name = name;
		this.password = password;
		this.authority = authority;
		this.live = live;
	}
	/*主キーである事を示している*/
	@Id
	/**/
	@SequenceGenerator(name = "users_id_seq")
	/*主キーの生成をJPAに委ねている*/
	@GeneratedValue(strategy = GenerationType .IDENTITY)
	private Long userId;
	/*データベースのカラムとマッピング（関連付け）する*/
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Authority authority;
	
	@Column(nullable = false)
	private String live;
	
	/*UserDetailsのgetAuthoritiesを実装する
	  List付与された権限の文字列を保管する
	*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(authority.toString()));
		return authorities;
	}
	/*ユーザーのアカウントの有効期限が切れているかどうかを判定*/
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/*ユーザーがロックされているかロック解除されているかを判定*/
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/*ユーザーの資格情報（パスワード）の有効期限が切れているかどうかを*/
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
 }
