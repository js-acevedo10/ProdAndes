package com.sistrans.mundo;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Pedido
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String iD;
	
	/**
	 * @return the iD
	 */
	public String getiD() {
		return iD;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setiD(String iD) {
		this.iD = iD;
	}

	/**
	 * @return the estadoPago
	 */
	public String getEstadoPago() {
		return estadoPago;
	}

	/**
	 * @param estadoPago the estadoPago to set
	 */
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the deadline
	 */
	public String getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the fechaRecibido
	 */
	public Date getFechaRecibido() {
		return fechaRecibido;
	}

	/**
	 * @param fechaRecibido the fechaRecibido to set
	 */
	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the producto
	 */
	public Set<Producto> getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Set<Producto> producto) {
		this.producto = producto;
	}

	/**
	 * @return the materiasProveedor
	 */
	public Set<MateriasProveedor> getMateriasProveedor() {
		return materiasProveedor;
	}

	/**
	 * @param materiasProveedor the materiasProveedor to set
	 */
	public void setMateriasProveedor(Set<MateriasProveedor> materiasProveedor) {
		this.materiasProveedor = materiasProveedor;
	}

	/**
	 * @return the gerentedeProduccion
	 */
	public Set<GerentedeProduccion> getGerentedeProduccion() {
		return gerentedeProduccion;
	}

	/**
	 * @param gerentedeProduccion the gerentedeProduccion to set
	 */
	public void setGerentedeProduccion(Set<GerentedeProduccion> gerentedeProduccion) {
		this.gerentedeProduccion = gerentedeProduccion;
	}

	public Pedido(String iD, String estadoPago, Date fechaCreacion,
			String deadline, Date fechaRecibido, Cliente cliente,
			Set<Producto> producto, Set<MateriasProveedor> materiasProveedor,
			Set<GerentedeProduccion> gerentedeProduccion) {
		super();
		this.iD = iD;
		this.estadoPago = estadoPago;
		this.fechaCreacion = fechaCreacion;
		this.deadline = deadline;
		this.fechaRecibido = fechaRecibido;
		this.cliente = cliente;
		this.producto = producto;
		this.materiasProveedor = materiasProveedor;
		this.gerentedeProduccion = gerentedeProduccion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String estadoPago;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date fechaCreacion;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String deadline;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date fechaRecibido;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Cliente cliente;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<Producto> producto;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<MateriasProveedor> materiasProveedor;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<GerentedeProduccion> gerentedeProduccion;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Pedido(){
		super();
	}

}

