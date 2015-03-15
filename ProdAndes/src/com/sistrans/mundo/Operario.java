package com.sistrans.mundo;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Operario extends Usuario
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int operacionesHechas;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public EstaciondeProducto estaciondeProducto;
	
	/**
	 * @return the operacionesHechas
	 */
	public int getOperacionesHechas() {
		return operacionesHechas;
	}

	/**
	 * @param operacionesHechas the operacionesHechas to set
	 */
	public void setOperacionesHechas(int operacionesHechas) {
		this.operacionesHechas = operacionesHechas;
	}

	/**
	 * @return the estaciondeProducto
	 */
	public EstaciondeProducto getEstaciondeProducto() {
		return estaciondeProducto;
	}

	/**
	 * @param estaciondeProducto the estaciondeProducto to set
	 */
	public void setEstaciondeProducto(EstaciondeProducto estaciondeProducto) {
		this.estaciondeProducto = estaciondeProducto;
	}

	public Operario(int operacionesHechas, EstaciondeProducto estaciondeProducto) {
		super();
		this.operacionesHechas = operacionesHechas;
		this.estaciondeProducto = estaciondeProducto;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Operario(){
		super();
	}

}

