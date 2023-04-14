package io.renren.modules.message.entity;

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
 * @date 2023-03-14 20:54:39
 */
@Data
@TableName("leave_doctor_message")
public class LeaveDoctorMessageEntity implements Serializable {
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
	 *
	 */
	private Long doctorId;
	/**
	 *
	 */
	private String message;
	/**
	 *
	 */
	private Date createTime;

	@TableField(exist = false)
	private String username;
	@TableField(exist = false)
	private String doctorName;
	//判断是否是自己说的
	@TableField(exist = false)
	private Integer flag;

}
