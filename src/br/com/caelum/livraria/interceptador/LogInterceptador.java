package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptador {
	
	 @AroundInvoke
	public Object intercepta(InvocationContext context) throws Exception {

        long millis = System.currentTimeMillis();

        Object o = context.proceed();
        
        String metodo = context.getMethod().getName(); 
        String nomeClass = context.getTarget().getClass().getName();
        
        System.out.println(nomeClass+ "   " +metodo );
        System.out.println("[INFO] xxxxxxxxxxxxxxxxxxxxxxx  Tempo gasto no acesso ao BD: "
                + (System.currentTimeMillis() - millis) + "ms");

        return o;
	}
}
