package com.example.fishing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
/*どのDBのカラムに共通してあるものを指定している*/
@MappedSuperclass
@Data
public class AbstractEntity {
	@Column(name = "created_at") /*DBのカラム名指定*/
	private Date createdAt;      /*作成時間の変数*/

	@Column(name = "update_at")
	private Date updatedAt;     /*更新時間の変数*/

	@PrePersist  /*DBにINSERTする前に呼び出されるメソッドであることを示している*/
	public void onPrePersist() {
		Date date = new Date();  /*日時取得のインスタンス*/
		setCreatedAt(date);     /*setCreatedAtに日時代入*/
		setUpdatedAt(date);     /*setUpdatadAtに日時代入*/
	}

	@PreUpdate
	public void onPreUpdate() {
		setUpdatedAt(new Date());
	}
}