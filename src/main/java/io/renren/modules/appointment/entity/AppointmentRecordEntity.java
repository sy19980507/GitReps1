package io.renren.modules.appointment.entity;

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
 * @date 2023-03-14 13:37:11
 */
@Data
@TableName("appointment_record")
public class AppointmentRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	/**
	 * 医生id
	 */
	private Long doctorId;
	/**
	 * 用户id
	 */
	private Long userId;

	private String describeInfo;
	/**
	 * 预约状态（0已预约，1已处理）
	 */
	private Integer state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 完成时间
	 */
	private Date completeTime;
	/**
	 * 预约时间
	 */
	private Date appointTime;

	@TableField(exist = false)
	private String username;
	@TableField(exist = false)
	private String doctorName;

}
