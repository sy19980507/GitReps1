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
 * @date 2023-03-13 15:25:24
 */
@Data
@TableName("health_sport_record")
public class HealthSportRecordEntity implements Serializable {
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
	 * 运动类型
	 */
	private String sportType;
	/**
	 * 持续时间
	 */
	private Double duration;
	/**
	 * 消耗
	 */
	private String consume;
	/**
	 * 创建时间
	 */
	private Date createTime;

	@TableField(exist = false)
	private String username;

}
