package com.example.fishing.validation.constraints;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordEqualsValidator implements ConstraintValidator<PasswordEquals, Object> {
	
	private String field1;
	private String field2;
	private String message;
	
	/*バリデーターを初期化する*/
	@Override
	public void initialize(PasswordEquals annotation) {
		field1 = "password";
		field2 = "passwordConfirmation";
		message = annotation.message();
	}
	
	/*検証を実行するメソッド
	 *BeanWrapperをインスタント化する
	 *PasswordEqualsから取得した値をクラス変数に代入する
	 *その後変数が空だったらtrueを返す 
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		String field1Value = (String) beanWrapper.getPropertyValue(field1);
		String field2Value = (String) beanWrapper.getPropertyValue(field2);
		if ((field1Value.isEmpty() || field2Value.isEmpty()) || Objects.equals(field1Value, field2Value)) {
			return true;
		} else {
			/*デフォルトのバリデーションメッセージを破棄して別のメッセージを生成できる*/
			context.disableDefaultConstraintViolation();
			/**/
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(field2).addConstraintViolation();
			return false;
		}
	}
	
}