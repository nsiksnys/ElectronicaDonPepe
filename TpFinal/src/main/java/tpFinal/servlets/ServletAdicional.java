package tpFinal.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import tpFinal.service.AdicionalService;
import tpFinal.servlets.Servlet;

public class ServletAdicional extends Servlet {
	private static final long serialVersionUID = 1L;
	private int rolPagina=1;
	private AdicionalService service;
	
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	
	public ServletAdicional()
	{
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setRequestResponse(request, response);
		
		if (!isLogedIn()){
			redirectLogin();
		}
		else if (isAllowed(rolPagina)){
			setDefaultAttributes();
			redirectPagina("CalcularAdicionales");
		}
		else{
			redirectPaginaError();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setRequestResponse(request, response);
		
		Date fechaDesde = getFecha("desde");
		Date fechaHasta = getFecha("hasta");
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		System.out.println("Calculando adicionales desde " + df.format(fechaDesde) + " hasta " + df.format(fechaHasta));
		setParamsVendedores();
		service.calcular(fechaDesde, fechaHasta);
		setDefaultAttributes();
		redirectPagina("CalcularAdicionales");
	}
	
	@Override
	public void init(ServletConfig config) {
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		this.service = (AdicionalService) ctx.getBean("AdicionalService");
	}
	
	public int[] getParamVendedoresIntArray(){
		int cantVendedores=service.getVendedoresActivos().size(), i;
		int[] vendedoresElegidosInteger=new int[cantVendedores];
		
		for (i=0; i<=cantVendedores; i++)
			vendedoresElegidosInteger[i]=0;
		
		for (i=1; i<=cantVendedores; i++)//recupero todos los parametros
			vendedoresElegidosInteger[i-1]=parseString(getParameter("vendedor"+i));
		
		return vendedoresElegidosInteger;
	}
	
	public void setParamsVendedores(){
		for (int i : getParamVendedoresIntArray()){
			if (i!=0){
				service.addVendedor(i);
			}
		}
	}
	
	private void setDefaultAttributes() {
		setAttribute("vendedores", service.getVendedoresActivos());
		setAttribute("adicionales", service.getAll());
	}
}
