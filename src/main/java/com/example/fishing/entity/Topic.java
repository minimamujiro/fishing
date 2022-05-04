package com.example.fishing.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	@Column(nullable = false, length = 1000)
	private String description;
	/*日にちのカラム*/
	@Column(nullable = false)
	private Date date;
	/*天気のカラム*/
	@Column(nullable = false)
	private String weather;
	/*潮汐*/
	@Column(nullable = false)
	private String tide_level;
	/*緯度カラム*/
	@Column
	private Double latitude;
	/*経度カラム*/
	@Column
	private Double longitude;
	/*開始時間*/
	@Column(nullable = false)
	private LocalTime start_time;
	/*終了時間*/
	@Column(nullable = false)
	private LocalTime end_time;

	@OneToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
	
	@OneToMany
	@JoinColumn(name = "topicId", insertable = false, updatable = false)
	private List<Favorite> favorites;
	
	@OneToMany
	@JoinColumn(name = "topicId", insertable = false, updatable = false)
	private List<Comment> comments;
}