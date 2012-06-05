package shop.dto;

import java.util.UUID;

/**
 * The mother of all persistent Objects; it provides an unique identifier. If
 * you want to use the features of IGenericDao you have to extend DBUUIDBase.
 * 
 * @author Andreas
 * 
 */
public abstract class DBUUIDBase {
	private UUID identifier;

	public UUID getIdentifier() {
		return identifier;
	}

	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}

}
