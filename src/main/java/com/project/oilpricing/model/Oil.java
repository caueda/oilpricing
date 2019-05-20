package com.project.oilpricing.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Entity foi Oil
 * @author Carlos Roberto Ueda
 *
 */
@Data
@EqualsAndHashCode(callSuper=false, of={"oilID"})
@ToString(callSuper=false, of={"oilID", "type"})
@Builder
public class Oil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3784575723207233722L;
	private String oilID;
	private Type type;
	private Double fixedRevenue;
	private Double variableRevenue;
	private Double barrelValue;
	
	public Oil(){
		super();
	}
	
	public Oil(String oilID, Type type, Double fixedRevenue, Double variableRevenue, Double barrelValue) {
		super();
		this.oilID = oilID;
		this.type = type;
		this.fixedRevenue = fixedRevenue;
		this.variableRevenue = variableRevenue;
		this.barrelValue = barrelValue;
	}
}
