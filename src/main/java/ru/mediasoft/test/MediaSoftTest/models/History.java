package ru.mediasoft.test.MediaSoftTest.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.mediasoft.test.MediaSoftTest.utils.PackageState;



@Entity
@Table(name = "history")
public class History {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "package_id")
	private Integer packageId;

	@Column(name = "post_office_id")
	private Integer postOfficeId;

	@Column(name = "arrival_date")
	private Timestamp arrivalDate;

	@Column(name = "leave_date")
	private Timestamp leaveDate;

	@Column(name = "package_state")
	@Enumerated(EnumType.ORDINAL)
	private PackageState packageState;

	public int getId() {
		return id;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPostOfficeId(int postOfficeId) {
		this.postOfficeId = postOfficeId;
	}

	public int getPostOfficeId() {
		return postOfficeId;
	}

	public void setArrivalDate(Timestamp arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Timestamp getArrivalDate() {
		return arrivalDate;
	}

	public void setLeaveDate(Timestamp leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Timestamp getLeaveDate() {
		return leaveDate;
	}

	public void setPackageState(PackageState packageState) {
		this.packageState = packageState;
	}

	public PackageState getPackageState() {
		return packageState;
	}
}
