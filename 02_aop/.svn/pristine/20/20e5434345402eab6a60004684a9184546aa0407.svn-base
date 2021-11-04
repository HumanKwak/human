package net.kwak0803.aop1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;



import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HelloWorldHandler implements InvocationHandler{
	private Object target;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//System.out.println(method.getName());
		System.out.println("전 처리");
		Object o = method.invoke(target, args);
		System.out.println("후 처리");
		return o;
	}

}
