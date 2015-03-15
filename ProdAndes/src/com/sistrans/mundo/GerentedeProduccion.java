package com.sistrans.mundo;
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
	
	public Set<Pedido> pedido;
	
	/**
	 * @return the pedido
	 */
	public Set<Pedido> getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(Set<Pedido> pedido) {
		this.pedido = pedido;
	}

	public GerentedeProduccion(Set<Pedido> pedido) {
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

