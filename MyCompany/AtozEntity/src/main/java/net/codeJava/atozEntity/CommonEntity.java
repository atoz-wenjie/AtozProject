package net.codeJava.atozEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CommonEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -304420707722746096L;

	@Column(name = "createDate",nullable = false)
	private Date createDate;
	
	@Column(name = "createByUser")
	private String createByUser;
	
	@Column(name = "updateDate")
	private String updateDate;
	
	@Column(name = "updateById")
	private Long updateById;
	
	@Column(name = "isDeleted")
	protected boolean isDeleted;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateByUser() {
		return createByUser;
	}

	public void setCreateByUser(String createByUser) {
		this.createByUser = createByUser;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateById() {
		return updateById;
	}

	public void setUpdateById(Long updateById) {
		this.updateById = updateById;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
