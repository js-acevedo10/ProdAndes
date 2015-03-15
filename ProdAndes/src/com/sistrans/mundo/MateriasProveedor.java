package com.sistrans.mundo;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MateriasProveedor
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int volMax;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int tiempoEntrega;
	
	/**
	 * @return the volMax
	 */
	public int getVolMax() {
		return volMax;
	}

	/**
	 * @param volMax the volMax to set
	 */
	public void setVolMax(int volMax) {
		this.volMax = volMax;
	}

	/**
	 * @return the tiempoEntrega
	 */
	public int getTiempoEntrega() {
		return tiempoEntrega;
	}

	/**
	 * @param tiempoEntrega the tiempoEntrega to set
	 */
	public void setTiempoEntrega(int tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}

	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the materiaPrima
	 */
	public ArrayList<MateriaPrima> getMateriaPrima() {
		return materiaPrima;
	}
	public void agregarMateriaPrima(MateriaPrima a)
	{
		materiaPrima.add(a);
	}

	/**
	 * @param materiaPrima the materiaPrima to set
	 */
	public void setMateriaPrima(ArrayList<MateriaPrima> materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	/**
	 * @return the componente
	 */
	public ArrayList<Componente> getComponente() {
		return componente;
	}
	public void agregarComponente(Componente a)
	{
		componente.add(a);
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(ArrayList<Componente> componente) {
		this.componente = componente;
	}

	/**
	 * @return the pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public MateriasProveedor(int volMax, int tiempoEntrega,
			Proveedor proveedor, ArrayList<MateriaPrima> materiaPrima,
			ArrayList<Componente> componente, Pedido pedido) {
		super();
		this.volMax = volMax;
		this.tiempoEntrega = tiempoEntrega;
		this.proveedor = proveedor;
		this.materiaPrima = materiaPrima;
		this.componente = componente;
		this.pedido = pedido;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Proveedor proveedor;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<MateriaPrima> materiaPrima;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Componente> componente;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Pedido pedido;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public MateriasProveedor(){
		super();
	}

}

