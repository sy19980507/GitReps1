package io.renren.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-13 16:14:23
 */
@Data
@TableName("health_disease_record")
public class HealthDiseaseRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	/**
	 *
	 */
	private Long userId;
	/**
	 * 疾病类型
	 */
	private String diseaseType;
	/**
	 *
	 */
	private Date createTime;
	/**
	 * 是否痊愈
	 */
	private Integer recure;
	/**
	 * 疾病开始时间
	 */
	private Date startTime;
	/**
	 * 疾病结束时间
	 */
	private Date endTime;

	@TableField(exist = false)
	private String username;

}
