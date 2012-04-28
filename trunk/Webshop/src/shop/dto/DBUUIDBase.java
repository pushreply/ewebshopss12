package shop.dto;

import java.util.UUID;

public abstract class DBUUIDBase {
	private UUID identifier;

	public UUID getIdentifier() {
		return identifier;
	}

	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}
	
}
