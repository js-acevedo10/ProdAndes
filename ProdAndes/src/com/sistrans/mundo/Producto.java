package com.sistrans.mundo;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Producto
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String nombre;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int costoVenta;
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the costoVenta
	 */
	public int getCostoVenta() {
		return costoVenta;
	}

	/**
	 * @param costoVenta the costoVenta to set
	 */
	public void setCostoVenta(int costoVenta) {
		this.costoVenta = costoVenta;
	}

	/**
	 * @return the materiaPrima
	 */
	public ArrayList<MateriaPrima> getMateriaPrima() {
		return materiaPrima;
	}

	/**
	 * @param materiaPrima the materiaPrima to set
	 */
	public void setMateriaPrima(ArrayList<MateriaPrima> materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	public void agregarMateriaPrima(MateriaPrima a)
	{
		materiaPrima.add(a);
	}

	/**
	 * @return the componente
	 */
	public ArrayList<Componente> getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(ArrayList<Componente> componente) {
		this.componente = componente;
	}
	public void agregarComponente(Componente a)
	{
		componente.add(a);
	}

	/**
	 * @return the etapadeProduccion
	 */
	public ArrayList<EtapadeProduccion> getEtapadeProduccion() {
		return etapadeProduccion;
	}

	/**
	 * @param etapadeProduccion the etapadeProduccion to set
	 */
	public void setEtapadeProduccion(ArrayList<EtapadeProduccion> etapadeProduccion) {
		this.etapadeProduccion = etapadeProduccion;
	}
	public void agregarEtapaProduccion(EtapadeProduccion a)
	{
		etapadeProduccion.add(a);
	}

	/**
	 * @return the pedido
	 */
	public int getnumInventario() {
		return numInventario;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setNuminventairo(int num) {
		this.numInventario = num;
	}

	/**
	 * @return the inventario
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param inventario the inventario to set
	 */
	public void settipo(String tipo) {
		this.tipo=tipo;
	}

	public Producto(String nombre, int costoVenta, int numInventario, String tipo) {
		super();
		this.nombre = nombre;
		this.costoVenta = costoVenta;
		this.materiaPrima = new ArrayList<>();
		this.componente = new ArrayList<>();
		this.etapadeProduccion = new ArrayList<>();
		this.numInventario = numInventario;
		this.tipo= tipo;
	}

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
	
	public ArrayList<EtapadeProduccion> etapadeProduccion;
	
	private int numInventario;
	private String tipo;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Producto(){
		super();
	}

}

