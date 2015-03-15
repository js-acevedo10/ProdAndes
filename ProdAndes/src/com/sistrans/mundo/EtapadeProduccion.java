package com.sistrans.mundo;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class EtapadeProduccion
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String num;
	
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}

	/**
	 * @return the fechaInicial
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the estaciondeProducto
	 */
	public Set<EstaciondeProducto> getEstaciondeProducto() {
		return estaciondeProducto;
	}

	/**
	 * @param estaciondeProducto the estaciondeProducto to set
	 */
	public void setEstaciondeProducto(Set<EstaciondeProducto> estaciondeProducto) {
		this.estaciondeProducto = estaciondeProducto;
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

	public EtapadeProduccion(String num, Date fechaInicial, Date fechaFinal,
			Set<EstaciondeProducto> estaciondeProducto, Producto producto) {
		super();
		this.num = num;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.estaciondeProducto = estaciondeProducto;
		this.producto = producto;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date fechaInicial;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date fechaFinal;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<EstaciondeProducto> estaciondeProducto;
	
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
	 */
	public EtapadeProduccion(){
		super();
	}

}

