package radio.signal;

/**
 * Classe che modella i numeri Complessi e le operazioni fondamentali
 * @author Antonio Matinata
 */

public class Complex {

	private double pReal; //parte Reale
	private double pImg; //parte immaginaria

	/** COSTRUTTORI **/
	public Complex () {
	}

	public Complex(double pReal, double pImg) {
		this.pReal = pReal;
		this.pImg = pImg;
	}

	/** SETTERS AND GETTERS **/
	public double getpImg() {
		return pImg;
	}

	public void setpImg(double pImg) {
		this.pImg = pImg;
	}

	public double getpReal() {
		return pReal;
	}

	public void setpReal(double pReal) {
		this.pReal = pReal;
	}

	/** OPERAZIONI **/
	
	/**
	 * Controlla se il numero complesso � costituito da sola parte reale
	 * @return true se la parte immaginaria � nulla
	 *  */
	public boolean isReale(){
		return this.pReal==0.0;
	}
	
	/**
	 * Controlla se il numero complesso � costituito da sola parte immaginaria
	 * @return true se la parte reale � nulla
	 */
	public boolean isImmaginario(){
		return this.pImg==0.0;
	}
	
	/** 
	 * Metodo che permette di ottenere il complesso e coniugato di un numero complesso z;
	 * Il complesso e coniugato di un numero complesso z ha la stessa parte reale e la parte immaginaria
	 * cambiata di segno.
	 * **/
	public void complexCon() {
		this.pImg = - this.pImg;
	}

	/** 
	 * Metodo che permette di calcolare la somma tra due numeri complessi c1,c2
	 * La somma di due numeri complessi � pari ad un numero complesso c che ha
	 * per parte reale la somma delle parti reali di c1 e c2
	 * e per parte immaginaria la somma delle parti immaginarie di c1 e c2
	 * @param Complex c1, Complex c2
	 * @return Complex c1+c2
	 * **/	
	public static Complex sum(Complex c1, Complex c2) {
		double pReal = c1.getpReal()+c2.getpReal();
		double pImg =  c1.getpImg()+c2.getpImg();
		return new Complex(pReal, pImg);
	}

	/** 
	 * Metodo che permette di calcolare la differenza tra due numeri complessi c1,c2
	 * La differenza di due numeri complessi � pari ad un numero complesso c che ha
	 * per parte reale la differenza delle parti reali di c1 e c2
	 * e per parte immaginaria la differenza delle parti immaginarie di c1 e c2
	 * @param Complex c1, Complex c2
	 * @return Complex c1-c2
	 * **/
	public static Complex diff(Complex c1, Complex c2) {
		double pReal = c1.getpReal() - c2.getpReal();
		double pImg = c1.getpImg() - c2.getpImg();
		return new Complex(pReal, pImg);
	}

	/**
	 * Restituisce il prodotto di due numeri complessi c1 e c2.
	 * ovvero il prodotto � pari a un nuovo complesso con parte reale pari alla somma dei prodotti fra parti immaginarie e reali dei due complessi
	 * e parte immaginaria pari alla somma dei prodotti incrociati fra parte immaginaria e reale dei due numeri
	 * @param Complex c1, Complex c2
	 * @return Complex c1 * c2
	 */
	public static Complex product(Complex c1, Complex c2) {
		double pReal = c1.getpReal() * c2.getpReal() - c1.getpImg() * c2.getpImg();
		double pImg = c1.getpImg() * c2.getpReal() + c1.getpReal() * c2.getpImg();
		return new Complex(pReal, pImg);
	}

	/**
	 * Restituisce il prodotto di un numero complesso e uno scalare scal. Il prodotto con uno scalare � pari a 
	 * un nuovo complesso che ha parte reale e parte immaginaria moltiplicate entrambe per quello scalare
	 * @param Complex c1, double scalare
	 * @return Complex prodotto C1*Scalare
	 */
	public static Complex prod(Complex c1, double scal){
		return new Complex(c1.getpReal()*scal,c1.getpImg()*scal);
	}

	/**
	 * Restituisce il rapporto di due numeri complessi c1 e c2.
	 * il rapporto � uguale a un nuovo complesso che ha al numeratore il prodotto dei complessi 
	 * e al denominatore la somma dei quadrati di parte reale e immaginaria del complesso al denominatore
	 * @param Complex c1, Complex c2
	 * @return Complex c1/c2
	 */
	public static Complex div(Complex c1, Complex c2) {
		double moduleDivisor = c2.module();
		c2.complexCon();
		Complex product = product(c1, c2);
		double pReal = product.getpReal()/moduleDivisor;
		double pImg = product.getpImg()/moduleDivisor;
		return new Complex(pReal, pImg);
	}

	/**
	 * Metodo che permette di ottenere il modulo di un numero complesso.
	 * @see Math.hypot(double,double)
	 * @return double fase del numero complesso.
	 * */
	public double module() {
		return Math.hypot(this.pReal, this.pImg);
	}

	/**
	 * Metodo che permette di ottenere il reciproco di un numero complesso.
	 * Il reciproco � pari ha al numeratore parte reale e parte immaginaria cambiata di segno,
	 * al denominatore la somma dei quadrati di parte immaginaria e reale.
	 */
	public void reciprocal() {
		complexCon();
		setpReal(this.pReal/module()); 
		setpImg(this.pImg/ module());
	}

	/**
	 * Metodo che permette di ottenere la fase di un numero complesso
	 * @see Math.atan2(double,double)
	 * @return double fase del numero complesso*/

	public double phase() {
		return Math.atan2(pImg, pReal);
	}

	/**
	 * Restituisce una descrizione testuale del numero complesso z= re + im j
	 * @return String descrizione
	 * */
	public String toString(){
		return this.pReal + "\t" + this.pImg;
	}

	/**
	 * Metodo che verifica l'uguaglianza tra due numeri complessi.
	 * Due numeri complessi sono uguali se parte reale e immaginaria sono uguali
	 * @return boolean uguaglianza 
	 * */
	@Override
	public boolean equals(Object obj) {
		Complex that = (Complex) obj;
		return that.getpReal() == this.pReal && that.pImg == this.pImg;
	}
}