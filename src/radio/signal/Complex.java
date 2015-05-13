package radio.signal;


public class Complex {
	
	private double pReal;
	private double pImg;
	
	
	public Complex () {
	}
	public Complex(double pReal, double pImg) {
		this.pReal = pReal;
		this.pImg = pImg;
	}
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
	
	public void complexCon() {
		this.pImg = - this.pImg;
	}
	
	public static Complex sum(Complex c1, Complex c2) {
		double pReal = c1.getpReal()+c2.getpReal();
		double pImg =  c1.getpImg()+c2.getpImg();
		return new Complex(pReal, pImg);
	}
	
	public static Complex diff(Complex c1, Complex c2) {
		double pReal = c1.getpReal() - c2.getpReal();
		double pImg = c1.getpImg() - c2.getpImg();
		return new Complex(pReal, pImg);
	}
	
	public static Complex product(Complex c1, Complex c2) {
		double pReal = c1.getpReal() * c2.getpReal() - c1.getpImg() * c2.getpImg();
		double pImg = c1.getpImg() * c2.getpReal() + c1.getpReal() * c2.getpImg();
		return new Complex(pReal, pImg);
	}
	
	public static Complex div(Complex c1, Complex c2) {
		double moduleDivisor = c2.module();
		c2.complexCon();
		Complex product = product(c1, c2);
		double pReal = product.getpReal()/moduleDivisor;
		double pImg = product.getpImg()/moduleDivisor;
		return new Complex(pReal, pImg);
	}
	
	
	public double module() {
		return Math.hypot(this.pReal, this.pImg);
	}
	
	public void reciprocal() {
		complexCon();
		setpReal(this.pReal/module()); 
		setpImg(this.pImg/ module());
	}
	
	public double phase() {
		return Math.atan2(pImg, pReal);
	}
	
	
	@Override
	public String toString() {
		return this.pReal + "\t" + this.pImg;
	}
	
	@Override
	public boolean equals(Object obj) {
		Complex that = (Complex) obj;
		return that.getpReal() == this.pReal && that.pImg == this.pImg;
	}
}
