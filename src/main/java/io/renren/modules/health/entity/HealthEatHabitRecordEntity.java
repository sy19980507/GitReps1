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
 * @date 2023-03-12 21:29:38
 */
@Data
@TableName("health_eat_habit_record")
public class HealthEatHabitRecordEntity implements Serializable {
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
	 * 类型(早餐，中餐，晚餐)
	 */
	private Integer mealType;
	/**
	 * 膳食
	 */
	private String meals;
	/**
	 * 摄入量
	 */
	private String intake;
	/**
	 * 创建时间
	 */
	private Date createTime;

	@TableField(exist = false)
	private String username;

}
