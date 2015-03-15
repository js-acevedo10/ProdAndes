package com.sistrans.mundo;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MateriaPrima
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String nombre;
	
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
	 * @return the numInventario
	 */
	public int getNumInventario() {
		return numInventario;
	}

	/**
	 * @param numInventario the numInventario to set
	 */
	public void setNumInventario(int numInventario) {
		this.numInventario = numInventario;
	}

	/**
	 * @return the tonelada
	 */
	public int getTonelada() {
		return tonelada;
	}

	/**
	 * @param tonelada the tonelada to set
	 */
	public void setTonelada(int tonelada) {
		this.tonelada = tonelada;
	}

	/**
	 * @return the materiasProveedor
	 */
	public MateriasProveedor getMateriasProveedor() {
		return materiasProveedor;
	}

	/**
	 * @param materiasProveedor the materiasProveedor to set
	 */
	public void setMateriasProveedor(MateriasProveedor materiasProveedor) {
		this.materiasProveedor = materiasProveedor;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
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

	public MateriaPrima(String nombre, int numInventario, int tonelada,
			MateriasProveedor materiasProveedor, Producto producto,
			Inventario inventario) {
		super();
		this.nombre = nombre;
		this.numInventario = numInventario;
		this.tonelada = tonelada;
		this.materiasProveedor = materiasProveedor;
		this.producto = producto;
		this.inventario = inventario;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int numInventario;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int tonelada;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public MateriasProveedor materiasProveedor;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Producto producto;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Inventario inventario;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public MateriaPrima(){
		super();
	}

}

