package tpFinal.service.calculation.calculationImpl;

import java.util.ArrayList;
import java.util.Date;

import tpFinal.dao.impl.PremioMontoDao;
import tpFinal.domain.Premio;
import tpFinal.domain.Vendedor;
import tpFinal.domain.Venta;
import tpFinal.domain.adicional.monto.PremioMonto;
import tpFinal.service.calculation.CalculationUtils;
//import tpFinal.service.calculation.CalculationService;
import tpFinal.service.findItem.findItemImpl.PremioFindItem;
import tpFinal.service.findItem.findItemImpl.VentaFindItem;

public class PremioMesCalculation extends CalculationUtils{
	private PremioFindItem findItem;
	protected PremioMontoDao daoMontos;
	
	//parametros necesarios para el calculo
	private ArrayList<Vendedor> vendedores;
	private Date fechaDesde;
	private Date fechaHoy;
	private VentaFindItem findVentas;
		
	public void setDaoMontos(PremioMontoDao daoMontos) {
		this.daoMontos = daoMontos;
	}
	
	public void setFindItem(PremioFindItem findItem){
		this.findItem=findItem;
	}
	
	public void setFindVentas(VentaFindItem findVentas) {
		this.findVentas=findVentas;
	}
	
	public void setParams(ArrayList<Vendedor> vendedores, Date desde, Date hoy){
		this.vendedores=vendedores;
		this.fechaDesde=desde;
		this.fechaHoy=hoy;
	}
	
	public Premio calcular(Date fechaHasta) {
		int cantidad=0;
		Premio registro;
		ArrayList<Venta>ventas = new ArrayList<Venta>();
		Vendedor premiado=new Vendedor();
		PremioMonto monto=daoMontos.getMontoPremioMes();
		
		//System.out.println("Premio mejor vendedor del mes: (" + df.format(desde.getTime()) + " - " + df.format(hasta) + ")");
	
		for (Vendedor vendedor : vendedores)
		{
			ventas=findVentas.findBySpecificDatesCreatorId(vendedor.getId(), fechaDesde, fechaHasta);//FIXIT: no recupera ninguna venta!
			
			if (ventas!=null && !ventas.isEmpty())
			{
				if (ventas.size()>cantidad)
				{
					cantidad=ventas.size();
					premiado=vendedor;
				}
			}/*
			else //Verificacion
			{
				System.out.println("\t\t" No se encontraron ventas para el período buscado");
			}*/
		//System.out.print("\n");
		}
		
		registro=new Premio(fechaHoy, fechaDesde, fechaHasta, premiado, false, null, monto.getMonto());
		
		if (cantidad==0)
			return null;
		if (findItem.findIdByObject(registro)!=0)//si ya existe un registro calculado con estos parámetros, así no insertamos registros repetidos //TODO: testear!!
			registro.setId(findItem.findIdByObject(registro));
		
				
		return registro;
	}
	//@Override
	public String showResult(Premio registro) {
		if (registro!=null)
			return "\t id = " + registro.getId() + "\t idProducto: " + registro.getProducto().getId() + "\t Importe: " + registro.getImporte();
		else
			return "\t No";
	}

	}
