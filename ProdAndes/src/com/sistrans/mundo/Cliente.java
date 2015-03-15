package com.sistrans.mundo;
import java.util.Set;
import java.util.HashSet;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Cliente extends Usuario
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int numReg;
	
	/**
	 * @return the numReg
	 */
	public int getNumReg() {
		return numReg;
	}

	/**
	 * @param numReg the numReg to set
	 */
	public void setNumReg(int numReg) {
		this.numReg = numReg;
	}

	/**
	 * @return the rLnombre
	 */
	public String getrLnombre() {
		return rLnombre;
	}

	/**
	 * @param rLnombre the rLnombre to set
	 */
	public void setrLnombre(String rLnombre) {
		this.rLnombre = rLnombre;
	}

	/**
	 * @return the rLtipoDoc
	 */
	public String getrLtipoDoc() {
		return rLtipoDoc;
	}

	/**
	 * @param rLtipoDoc the rLtipoDoc to set
	 */
	public void setrLtipoDoc(String rLtipoDoc) {
		this.rLtipoDoc = rLtipoDoc;
	}

	/**
	 * @return the rLnumDoc
	 */
	public String getrLnumDoc() {
		return rLnumDoc;
	}

	/**
	 * @param rLnumDoc the rLnumDoc to set
	 */
	public void setrLnumDoc(String rLnumDoc) {
		this.rLnumDoc = rLnumDoc;
	}

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

	public Cliente(int numReg, String rLnombre, String rLtipoDoc,
			String rLnumDoc, Set<Pedido> pedido) {
		super();
		this.numReg = numReg;
		this.rLnombre = rLnombre;
		this.rLtipoDoc = rLtipoDoc;
		this.rLnumDoc = rLnumDoc;
		this.pedido = pedido;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String rLnombre;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String rLtipoDoc;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String rLnumDoc;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<Pedido> pedido;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Cliente(){
		super();
	}

}

