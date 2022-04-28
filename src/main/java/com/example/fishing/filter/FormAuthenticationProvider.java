package com.example.fishing.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.fishing.entity.User;
import com.example.fishing.repository.UserRepository;

/*@Configurationないのメソッドに@Beanを付与して部品化する
 * 今回は認証するメソッドを対象としてる
 * */
@Configuration
public class FormAuthenticationProvider implements AuthenticationProvider {

	protected static Logger log = LoggerFactory.getLogger(FormAuthenticationProvider.class);

	@Autowired
	private UserRepository repository;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String name = auth.getName();
		String password = auth.getCredentials().toString();

		log.debug("name={}", name);
		log.debug("password={}", password);

		/*name又はpasswordが空白だったら*/
		if ("".equals(name) || "".equals(password)) {
			throw new AuthenticationCredentialsNotFoundException("ログイン情報に不備があります。");
		}
		/*falseの場合
		 * リポジトリから名前を探してローカル変数entityに代入
		 * その後中身がnullか確認
		 * */
		User entity = repository.findByUsername(name);
		if (entity == null) {
			throw new AuthenticationCredentialsNotFoundException("ログイン情報が存在しません。");
		}
		/*インスタンス化して呼び出し元に返す*/
		return new UsernamePasswordAuthenticationToken(entity, password, entity.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
