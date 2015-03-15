package com.sistrans.mundo;
import java.util.ArrayList;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class GerentedeProduccion extends Usuario
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Pedido> pedido;
	
	/**
	 * @return the pedido
	 */
	public ArrayList<Pedido> getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(ArrayList<Pedido> pedido) {
		this.pedido = pedido;
	}

	public GerentedeProduccion(ArrayList<Pedido> pedido) {
		super();
		this.pedido = pedido;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public GerentedeProduccion(){
		super();
	}

}

