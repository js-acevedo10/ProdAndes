package com.sistrans.mundo;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Administrador extends Usuario
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Inventario inventario;

	public Administrador(Inventario inventario) {
		super();
		this.inventario = inventario;
	}

	/**
	 * @return the inventario
	 */
	public Inventario getInventario() {
		return inventario;
	}

	/**
	 * @param inventario the inventario to set
	 */
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	


}

