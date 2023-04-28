package ru.mediasoft.test.MediaSoftTest.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.mediasoft.test.MediaSoftTest.utils.PackageType;

@Entity
@Table(name = "package")
public class Package {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "type")
	@Enumerated(EnumType.ORDINAL)
	private PackageType type;

	@Column(name = "recipient_index")
	private Integer recipientIndex;

	@Column(name = "recipient_address")
	private String recipientAddress;

	@Column(name = "recipient_name")
	private String recipientName;

	public int getId() {
		return id;
	}

	public void setType(PackageType type) {
		this.type = type;
	}

	public PackageType getType() {
		return type;
	}

	public void setRecipientIndex(int recipientIndex) {
		this.recipientIndex = recipientIndex;
	}

	public int getRecipientIndex() {
		return recipientIndex;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientName() {
		return recipientName;
	}
}
