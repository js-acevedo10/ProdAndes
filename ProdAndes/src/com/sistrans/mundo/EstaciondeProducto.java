package com.sistrans.mundo;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class EstaciondeProducto
{
	
	public EstaciondeProducto(String codigo, String idEtapaProduccion, String tiempoRealizacion2) {
		super();
		this.codigo = codigo;
		this.idEtapadeProduccion = idEtapaProduccion;
		this.tiempoRealizacion = tiempoRealizacion2;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public String getIdEtapadeProduccion() {
		return idEtapadeProduccion;
	}

	public void setIdEtapadeProduccion(String idEtapadeProduccion) {
		this.idEtapadeProduccion = idEtapadeProduccion;
	}


	public String getTiempoRealizacion() {
		return tiempoRealizacion;
	}

	public void setTiempoRealizacion(String tiempoRealizacion) {
		this.tiempoRealizacion = tiempoRealizacion;
	}


	public String getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(String idComponente) {
		this.idComponente = idComponente;
	}


	public int getNumComponente() {
		return numComponente;
	}

	public void setNumComponente(int numComponente) {
		this.numComponente = numComponente;
	}


	public String getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdMateriaPrima(String idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}


	public int getNumMateriaPrima() {
		return numMateriaPrima;
	}

	public void setNumMateriaPrima(int numMateriaPrima) {
		this.numMateriaPrima = numMateriaPrima;
	}


	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}


	public int getNumProducto() {
		return numProducto;
	}

	public void setNumProducto(int numProducto) {
		this.numProducto = numProducto;
	}


	private String idEtapadeProduccion;
	private String codigo;
	private String tiempoRealizacion;
	private String idComponente;
	private int numComponente;
	private String idMateriaPrima;
	private int numMateriaPrima;
	private String idProducto;
	private int numProducto;
}

