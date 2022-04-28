package com.example.fishing.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import lombok.Data;

@Entity
@Table(name = "topic")
@Data
public class Topic extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/*投稿画面で使うDBのテーブル等を作成する*/
	@Id
	@SequenceGenerator(name = "topic_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private String path;
	/*説明カラム*/
	@Column(nullable = false)
	private String description;
	/*緯度カラム*/
	@Column(nullable = false)
	private Double lattitude;
	/*経度カラム*/
	@Column(nullable = false)
	private Double longitude;
	/*開始時間*/
	@Column(nullable = false)
	private DateTime start_time;
	/*終了時間*/
	@Column(nullable = false)
	private DateTime end_time;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
}