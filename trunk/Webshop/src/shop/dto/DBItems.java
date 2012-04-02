/**
 * 
 */
package shop.dto;

/**
 * @author roha0001
 *
 */
public class DBItems {
	private int orderAmount;

	public DBItems(int orderAmount) {
		super();
		this.orderAmount = orderAmount;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Override
	//maybe useful for logging.
	public String toString() {
		return "Items [orderAmount=" + orderAmount + "]";
	}
	
	

}
