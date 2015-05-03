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
	
	public int pagEtapa1, pagEtapa2, pagPedido2, pagMaterial2;
	
	public Inventario inventario;

	public Administrador() {
		pagEtapa1 = 0;
		pagEtapa2 = 0;
		pagPedido2 = 0;
		pagMaterial2 = 0;
	}
	
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

