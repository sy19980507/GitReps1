package io.renren.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-03-12 18:21:26
 */
@Data
@TableName("health_user_info")
public class HealthUserInfoEntity implements Serializable {
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
	 * 心率
	 */
	private Integer heartRate;
	/**
	 * 舒张压
	 */
	private Integer bloodPressure;
	/**
	 * 收缩压
	 */
	private Integer bloodShrinkPressure;
	/**
	 * 升高
	 */
	private Double height;
	/**
	 * 体重
	 */
	private Double weight;
	/**
	 * BMI指数
	 */
	private Double bmi;
	/**
	 * 饮食习惯
	 */
	private String eatHabits;
	/**
	 * 疾病
	 */
	private String disease;
	/**
	 *
	 */
	private Date createTime;

	@TableField(exist = false)
	private String username;
	@TableField(exist = false)
	private String time;
	/**
	 * 记录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recordTime;

}
