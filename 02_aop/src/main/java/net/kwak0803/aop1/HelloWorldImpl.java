package net.kwak0803.aop1;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public void sayHello(String msg) {
		System.out.println("μλ μΈμ ::" +msg);
		
	}
	
}
