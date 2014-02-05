package TpFinal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import TpFinal.dataccess.DataAccess;
import TpFinal.domain.Adicional;
import TpFinal.domain.ComisionProducto;
import TpFinal.domain.ComisionVenta;
import TpFinal.domain.Payroll;
import TpFinal.domain.Premio;
import TpFinal.domain.Producto;
import TpFinal.domain.Vendedor;
import TpFinal.domain.Venta;
import TpFinal.domain.adicional.monto.ComisionProductoMonto;
import TpFinal.domain.adicional.monto.ComisionVentaMonto;
import TpFinal.domain.adicional.monto.PremioMonto;
import TpFinal.security.RolUsuario;
import TpFinal.security.Usuario;
import TpFinal.service.Service;

public class ServiceBasico implements Service {

	private DataAccess dataAccess = null;

	public DataAccess getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}
	
//USUARIOS
	@Override
	public Usuario getUsuario(Integer id) {
		return dataAccess.getUsuario(id);
	}

	@Override
	public ArrayList<Usuario> getUsuarios() {
		return dataAccess.getUsuarios();
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		dataAccess.guardarUsuario(usuario);
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		dataAccess.actualizarUsuario(usuario);
	}
	
	@Override
	public boolean login(String username, String password)
	{
		ArrayList<Usuario> todos = dataAccess.getUsuarios();
		for (Usuario item : todos)
			if (password.equals(item.getPassword()) && username.equals(item.getUsername()) && item.isActivo()==true)
				return true;
		return false;
	}
	
	@Override
	public Usuario getUsuario(String username)
	{
		ArrayList<Usuario> todos = dataAccess.getUsuarios();
		for (Usuario item : todos)
			if (username.equals(item.getUsername()) && item.isActivo()==true)
				return item;
		return null;
	}
	
//ROL DE USUARIOS
	@Override
	public RolUsuario getRol(Integer id) {
		return dataAccess.getRol(id);
	}

	@Override
	public ArrayList<RolUsuario> getRoles() {
		return dataAccess.getRoles();
	}

	@Override
	public void guardarRol(RolUsuario rol) {
		dataAccess.guardarRol(rol);
	}

//VENDEDOR
	@Override
	public Vendedor getVendedor(Integer id) {
		return dataAccess.getVendedor(id);
	}

	@Override
	public ArrayList<Vendedor> getVendedores() {
		return dataAccess.getVendedores();
	}

	@Override
	public ArrayList<Vendedor> getVendedoresActivos()
	{
		ArrayList<Vendedor> todos=getVendedores();
		ArrayList<Vendedor> activos=new ArrayList<Vendedor>();
		for (Vendedor item : todos)
			if (item.isActivo()==true)
				activos.add(item);
		return activos;
	}
	
	@Override
	public void guardarVendedor(Vendedor vendedor) {
		dataAccess.guardarVendedor(vendedor);
	}

	@Override
	public void actualizarVendedor(Vendedor vendedor) {
		dataAccess.actualizarVendedor(vendedor);
	}
	
//PRODUCTO
	@Override
	public Producto getProducto(Integer id) {
		return dataAccess.getProducto(id);
	}

	@Override
	public ArrayList<Producto> getProductos() {
		return dataAccess.getProductos();
	}

	@Override
	public void guardarProducto(Producto producto) {
		dataAccess.guardarProducto(producto);
	}

//VENTA
	@Override
	public Venta getVenta(Integer idVenta) {
		return dataAccess.getVenta(idVenta);
	}

	@Override
	public ArrayList<Venta> getVentas(int idVendedor)
	{
		ArrayList<Venta> todos = dataAccess.getVentas();
		ArrayList<Venta> rta= new ArrayList<Venta>();
		for (Venta item : todos)
			if (item.getVendedor().getId()==idVendedor)
				rta.add(item);
		return rta;
	}
	
	@Override
	public ArrayList<Venta> getVentas(Vendedor vendedor, GregorianCalendar desde, GregorianCalendar hasta)
	{
		//System.out.println("Buscar desde " + desde.toString() + " hasta " + hasta.toString());
		ArrayList<Venta> todos=getVentas(vendedor.getId());
		ArrayList<Venta> seleccion=new ArrayList<Venta>();
		GregorianCalendar calendario = new GregorianCalendar();
		
		for (Venta item : todos)
		{	
			//System.out.println(item.getId() + ": " + item.getFechaCreacion().toString());
			calendario.setTime(item.getFecha());
			if (calendario.after(desde) && calendario.before(hasta))
				seleccion.add(item);
		}
		
		//System.out.println("Encontrados " + seleccion.size() + " de " + todos.size() + " registros");
		return seleccion;
	}
	
	@Override
	public ArrayList<Venta> getVentas(GregorianCalendar desde, GregorianCalendar hasta)
	{
		ArrayList<Venta> todos=getVentas();
		ArrayList<Venta> seleccion=new ArrayList<Venta>();
		GregorianCalendar calendario = new GregorianCalendar();
		
		for (Venta item : todos)
		{	
			//System.out.println(item.getId() + ": " + item.getFechaCreacion().toString());
			calendario.setTime(item.getFecha());
			if (calendario.after(desde) && calendario.before(hasta))
				seleccion.add(item);
		}
		
		//System.out.println("Encontrados " + seleccion.size() + " de " + todos.size() + " registros");
		return seleccion;
	}
	
	@Override
	public ArrayList<Venta> getVentas() {
		return dataAccess.getVentas();
	}

	@Override
	public void guardarVenta(Venta venta) {
		dataAccess.guardarVenta(venta);
	}

//MONTO DE COMISIONES
	@Override
	public ComisionProductoMonto getMontoProducto(Integer id) {
		return dataAccess.getMontoProducto(id);
	}

	@Override
	public ArrayList<ComisionProductoMonto> getMontosProducto() {
		return dataAccess.getMontosProducto();
	}

	@Override
	public ComisionProductoMonto getMontoProducto(Producto producto)
	{
		ArrayList<ComisionProductoMonto> todos=getMontosProducto();
		for (ComisionProductoMonto item : todos)
			if (item.getProducto().getId()==producto.getId())
				return item;
		return null;
	}
	
	@Override
	public void guardarProductoMonto(ComisionProductoMonto item) {
		dataAccess.guardarProductoMonto(item);
	}

	@Override
	public ComisionVentaMonto getMontoVenta(Integer id) {
		return dataAccess.getMontoVenta(id);
	}

	@Override
	public ArrayList<ComisionVentaMonto> getMontosVenta() {
		return dataAccess.getMontosVenta();
	}

	@Override
	public void actualizarProductoMonto(ComisionProductoMonto item)
	{
		dataAccess.actualizarProductoMonto(item);
	}

	@Override
	public void actualizarVentaMonto(ComisionVentaMonto item)
	{
		dataAccess.actualizarVentaMonto(item);
	}

	@Override
	public void guardarVentaMonto(ComisionVentaMonto item) {
		dataAccess.guardarVentaMonto(item);
	}
	
//MONTO DE PREMIOS
	@Override
	public PremioMonto getMontoPremio(Integer id) {
		return dataAccess.getMontoPremio(id);
	}

	@Override
	public ArrayList<PremioMonto> getMontosPremio() {
		return dataAccess.getMontosPremio();
	}

	@Override
	public PremioMonto getMontoPremio(boolean campania)
	{
		ArrayList<PremioMonto> todos=getMontosPremio();
		for (PremioMonto item: todos)
			if (item.isCampania()==campania)
				return item;
		return null;
	}
	
	@Override
	public void actualizarPremioMonto(PremioMonto item)
	{
		dataAccess.actualizarPremioMonto(item);
	}
		
	@Override
	public void guardarPremioMonto(PremioMonto item) {
		dataAccess.guardarPremioMonto(item);
	}

//ADICIONAL
	@Override
	public Adicional getAdicional(Integer idAdicional) {
		return dataAccess.getAdicional(idAdicional);
	}

	@Override
	public ArrayList<Adicional> getAdicional(Date fecha)
	{
		ArrayList<Adicional> todos = dataAccess.getAdicionales();
		ArrayList<Adicional> rta= new ArrayList<Adicional>();
		for (Adicional item : todos)
			if (item.getFechaCreacion()==fecha)
				rta.add(item);
		return rta;
	}
	
	@Override
	public ArrayList<Adicional> getAdicional(int idVendedor)
	{
		ArrayList<Adicional> todos = dataAccess.getAdicionales();
		ArrayList<Adicional> rta= new ArrayList<Adicional>();
		for (Adicional item : todos)
			if (item.getVendedor().getId()==idVendedor)
				rta.add(item);
		return rta;
	}
	
	@Override
	public Adicional getAdicional(int idVendedor, GregorianCalendar desde, GregorianCalendar hasta)
	{
		ArrayList<Adicional> todos = getAdicional(idVendedor);
		GregorianCalendar calendarioDesde = new GregorianCalendar();
		GregorianCalendar calendarioHasta = new GregorianCalendar();
		
		for (Adicional item : todos)
		{
			calendarioDesde.setTime(item.getFechaDesde());
			calendarioHasta.setTime(item.getFechaHasta());
			if (calendarioDesde==desde && calendarioHasta==hasta)
				return item;
		}
		return null;
	}
	
	@Override
	public Adicional getAdicional(int idVendedor, Date fechaCreacion)
	{
		ArrayList<Adicional> todos = getAdicional(idVendedor);
		for (Adicional item : todos)
			if (item.getFechaCreacion()==fechaCreacion)
				return item;
		return null;
	}
	
	@Override
	public ArrayList<Adicional> getAdicional(GregorianCalendar desde, GregorianCalendar hasta)
	{
		ArrayList<Adicional> todos = getAdicionales();
		ArrayList<Adicional> seleccion = new ArrayList<Adicional>();
		GregorianCalendar calendarDesde = new GregorianCalendar();
		GregorianCalendar calendarHasta = new GregorianCalendar();
		
		for (Adicional adicional : todos)
		{
			calendarDesde.setTime(adicional.getFechaDesde());
			calendarHasta.setTime(adicional.getFechaHasta());
			
			if (calendarDesde==desde && calendarHasta==hasta)
				seleccion.add(adicional);
		}
		return seleccion;
	}
	
	@Override
	public ArrayList<Adicional> getAdicionales() {
		ArrayList<Adicional> todos = dataAccess.getAdicionales();
		
		for (Adicional adicional : todos)
		{
			if (adicional.getCampania()!=null && adicional.getCampania().getProducto()==null)
				adicional.setCampania(null);
			
			if (adicional.getMejorVendedorMes()!=null && adicional.getMejorVendedorMes().getProducto()!=null)
				adicional.setMejorVendedorMes(null);
			
/*			adicional.setCampania(getPremioCampania(adicional.getFechaDesde(), adicional.getFechaHasta(), adicional.getVendedor()));
			adicional.setMejorVendedorMes(getPremioMejorVendedorMes(adicional.getFechaDesde(), adicional.getVendedor().getId()));
			
			System.out.println("adicional " + adicional.getId());
			if (adicional.getCampania()!=null)
			{
				System.out.print("\t campania: ");
				if(adicional.getCampania().isCampania()==true)
					System.out.print("si");
				else
					System.out.print("no");
				System.out.print("\n");
			}
			
			if (adicional.getMejorVendedorMes()!=null)
			{
				System.out.print("\t mejor vendedor: ");
				if(adicional.getMejorVendedorMes().isCampania()==false)
					System.out.print("si");
				else
					System.out.print("no");
				System.out.print("\n");
			}*/
		}
		return todos;
	}

	@Override
	public void guardarAdicional(Adicional adicional) {
		if (adicional.getComisionVentas()!=null)//si o si tiene que haber comision por ventas para que se justifique guardar un adicional
			dataAccess.guardarAdicional(adicional);
	}

	
//COMISION
	@Override
	public ComisionVenta getComisionVenta(Integer id) {
		return dataAccess.getComisionVenta(id);
	}

	@Override
	public ComisionProducto getComisionProducto(Integer id) {
		return dataAccess.getComisionProducto(id);
	}

	@Override
	public ComisionVenta getComisionVenta(int idVendedor, GregorianCalendar desde, GregorianCalendar hasta)
	{
		ArrayList<ComisionVenta> todos = getComisionVenta(idVendedor);
		GregorianCalendar calendarioDesde = new GregorianCalendar();
		GregorianCalendar calendarioHasta = new GregorianCalendar();
		
		for (ComisionVenta item : todos)
		{
			calendarioDesde.setTime(item.getFechaDesde());
			calendarioHasta.setTime(item.getFechaHasta());
			if (calendarioDesde==desde && calendarioHasta==hasta)
				return item;
		}
		return null;
	}

	@Override
	public ComisionProducto getComisionProducto(int idVendedor, GregorianCalendar desde, GregorianCalendar hasta, Producto producto)
	{
		ArrayList<ComisionProducto> todos = getComisionProducto(idVendedor);
		GregorianCalendar calendarioDesde = new GregorianCalendar();
		GregorianCalendar calendarioHasta = new GregorianCalendar();
		
		for (ComisionProducto item : todos)
		{
			calendarioDesde.setTime(item.getFechaDesde());
			calendarioHasta.setTime(item.getFechaHasta());
			if (calendarioDesde==desde && calendarioHasta==hasta && item.getProducto().getId()==producto.getId())
				return item;
		}
		return null;
	}
	
	@Override
	public ArrayList<ComisionVenta> getComisionVenta(Date fecha)
	{
		ArrayList<ComisionVenta> todos = dataAccess.getComisionVenta();
		ArrayList<ComisionVenta> rta= new ArrayList<ComisionVenta>();
		for (ComisionVenta item : todos)
			if (item.getFechaCreacion()==fecha)
				rta.add(item);
		return rta;
	}
	
	@Override
	public ArrayList<ComisionProducto> getComisionProducto(Date fecha)
	{
		ArrayList<ComisionProducto> todos = dataAccess.getComisionProducto();
		ArrayList<ComisionProducto> rta= new ArrayList<ComisionProducto>();
		for (ComisionProducto item : todos)
			if (item.getFechaCreacion()==fecha)
				rta.add(item);
		return rta;
	}
	
	@Override
	public ArrayList<ComisionVenta> getComisionVenta(int idVendedor)
	{
		ArrayList<ComisionVenta> todos = dataAccess.getComisionVenta();
		ArrayList<ComisionVenta> rta= new ArrayList<ComisionVenta>();
		for (ComisionVenta item : todos)
			if (item.getVendedor().getId()==idVendedor)
				rta.add(item);
		return rta;
	}
	
	@Override
	public ArrayList<ComisionProducto> getComisionProducto(int idVendedor)
	{
		ArrayList<ComisionProducto> todos = dataAccess.getComisionProducto();
		ArrayList<ComisionProducto> rta= new ArrayList<ComisionProducto>();
		for (ComisionProducto item : todos)
			if (item.getVendedor().getId()==idVendedor)
				rta.add(item);
		return rta;
	}
	
	@Override
	public ComisionVenta getComisionVenta(Date fecha, int idVendedor)
	{
		ArrayList<ComisionVenta> todos = getComisionVenta(fecha);
		for (ComisionVenta item : todos)
			if (item.getVendedor().getId()==idVendedor)
				return item;
		return null;
	}
	
	@Override
	public ComisionProducto getComisionProducto(Date fecha, int idVendedor)
	{
		ArrayList<ComisionProducto> todos = getComisionProducto(fecha);
		for (ComisionProducto item : todos)
			if (item.getVendedor().getId()==idVendedor)
				return item;
		return null;
	}
	
	@Override
	public ArrayList<ComisionVenta> getComisionVenta() {
		return dataAccess.getComisionVenta();
	}

	@Override
	public ArrayList<ComisionProducto> getComisionProducto() {
		return dataAccess.getComisionProducto();
	}
	
	@Override
	public void actualizarComisionVenta(ComisionVenta comisionVenta) {
		dataAccess.actualizarComisionVenta(comisionVenta);
	}

	@Override
	public void actualizarComisionProducto(ComisionProducto comisionProducto) {
		dataAccess.actualizarComisionProducto(comisionProducto);
	}

	@Override
	public void guardarComisionVenta(ComisionVenta comisionVenta) {
		if (comisionVenta!=null)
			dataAccess.guardarComisionVenta(comisionVenta);
	}

	@Override
	public void guardarComisionProducto(ComisionProducto comisionProducto) {
		if (comisionProducto!=null)
			dataAccess.guardarComisionProducto(comisionProducto);
	}

//PREMIO
	@Override
	public Premio getPremio(Integer id) {
		return dataAccess.getPremio(id);
	}

	@Override
	public ArrayList<Premio> getPremioMejorVendedorMes() {
		return dataAccess.getPremioMejorVendedorMes();
	}

	@Override
	public ArrayList<Premio> getPremioCampania() {
		return dataAccess.getPremioCampania();
	}

	@Override
	public ArrayList<Premio> getPremio() {
		return dataAccess.getPremio();
	}

	@Override
	public ArrayList<Premio> getPremioMejorVendedorMes(Date fechaCreacion)
	{
		ArrayList<Premio> todos = dataAccess.getPremio();
		ArrayList<Premio> rta= new ArrayList<Premio>();
		for (Premio item : todos)
			if (item.getFechaCreacion()==fechaCreacion && item.isCampania()==false)
				rta.add(item);
		return rta;
	}
	
	@Override
	public ArrayList<Premio> getPremioCampania(Date fechaCreacion)
	{
		ArrayList<Premio> todos = dataAccess.getPremio();
		ArrayList<Premio> rta= new ArrayList<Premio>();
		for (Premio item : todos)
			if (item.getFechaCreacion()==fechaCreacion && item.isCampania()==true)
				rta.add(item);
		return rta;
	}

	@Override
	public Premio getPremioMejorVendedorMes(Date fechaCreacion, Vendedor vendedor)
	{
		ArrayList<Premio> todos=getPremioMejorVendedorMes(fechaCreacion);
		for (Premio item : todos)
			if (item.getPremiado().getId()==vendedor.getId())
				return item;
		return null;
	}
	
	@Override
	public ArrayList<Premio> getPremioCampania(int idVendedor)
	{
		ArrayList<Premio> todos=getPremioCampania();
		ArrayList<Premio> seleccion = new ArrayList<Premio>();
		for (Premio item : todos)
			if (item.getPremiado().getId()==idVendedor)
				seleccion.add(item);
		return seleccion;
	}
	
	@Override
	public ArrayList<Premio> getPremioMejorVendedorMes(int idVendedor)
	{
		ArrayList<Premio> todos=getPremioMejorVendedorMes();
		ArrayList<Premio> seleccion = new ArrayList<Premio>();
		for (Premio item : todos)
			if (item.getPremiado().getId()== idVendedor)
				seleccion.add(item);
		return seleccion;
	}
	
	@Override
	public Premio getPremioCampania(Date desde, Date hasta, Vendedor vendedor)
	{
		ArrayList<Premio> todos=dataAccess.getPremioCampania();
		for (Premio item : todos)
			if (item.getFechaDesde()==desde && item.getFechaHasta()==hasta && item.getPremiado().getId()==vendedor.getId())
				return item;
		return null;
	}
	
	@Override
	public Premio getPremioMejorVendedorMes(Date desde, int idVendedor)
	{
		ArrayList<Premio> todos = getPremioMejorVendedorMes(idVendedor);
		
		for (Premio item : todos)
		{
			if (item.getFechaDesde()==desde && item.getPremiado().getId()==idVendedor)
				return item;
		}
		return null;
	}
	
	@Override
	public Premio getPremioCampania(int idVendedor, GregorianCalendar desde, GregorianCalendar hasta, Producto producto)
	{
		ArrayList<Premio> todos = getPremioCampania(idVendedor);
		GregorianCalendar calendarioDesde = new GregorianCalendar();
		GregorianCalendar calendarioHasta = new GregorianCalendar();
		
		for (Premio item : todos)
		{
			calendarioDesde.setTime(item.getFechaDesde());
			calendarioHasta.setTime(item.getFechaHasta());
			if (calendarioDesde==desde && calendarioHasta==hasta && item.getProducto().getId()==producto.getId())
				return item;
		}
		return null;
	}
	
	@Override
	public Premio getPremioMejorVendedorMes(GregorianCalendar desde)
	{
		ArrayList<Premio> todos = getPremioMejorVendedorMes();
		
		GregorianCalendar calendarioDesde = new GregorianCalendar();
		
		for (Premio item : todos)
		{
			calendarioDesde.setTime(item.getFechaDesde());
			if (calendarioDesde==desde)
				return item;
		}
		return null;
	}
	
	@Override
	public Premio getPremioCampania(GregorianCalendar desde, GregorianCalendar hasta, Producto producto)
	{
		ArrayList<Premio> todos = getPremioMejorVendedorMes();
		
		GregorianCalendar calendarioDesde = new GregorianCalendar();
		GregorianCalendar calendarioHasta = new GregorianCalendar();
		
		for (Premio item : todos)
		{
			calendarioDesde.setTime(item.getFechaDesde());
			calendarioHasta.setTime(item.getFechaHasta());
			if (calendarioDesde==desde && calendarioHasta==hasta && item.getProducto().getId()==producto.getId())
				return item;
		}
		return null;
	}
	
	@Override
	public void actualizarPremio(Premio premio) {
		dataAccess.actualizarPremio(premio);
	}

	@Override
	public void guardarPremio(Premio premio) {
		if (premio!=null)
			dataAccess.guardarPremio(premio);
	}

	
//PAYROLL
	@Override
	public ArrayList<Payroll> getPayroll(Date fecha)
	{
		ArrayList<Payroll> todos = dataAccess.getPayroll();
		ArrayList<Payroll> rta= new ArrayList<Payroll>();
		for (Payroll item : todos)
			if (item.getFechaCreacion()==fecha)
				rta.add(item);
		return rta;
	}
	
	@Override
	public ArrayList<Payroll> getPayroll(int idVendedor)
	{
		ArrayList<Payroll> todos = dataAccess.getPayroll();
		ArrayList<Payroll> rta= new ArrayList<Payroll>();
		for (Payroll item : todos)
			if (item.getVendedor().getId()==idVendedor)
				rta.add(item);
		return rta;
	}

	@Override
	public ArrayList<Payroll> getPayroll(Date fecha, int idVendedor)
	{
		ArrayList<Payroll> todos = getPayroll(fecha);
		ArrayList<Payroll> rta= new ArrayList<Payroll>();
		for (Payroll item : todos)
			if (item.getVendedor().getId()==idVendedor)
				rta.add(item);
		return rta;
	}

	@Override
	public Payroll getPayroll(Integer id) {
		return getPayroll(id);
	}
	
	@Override
	public ArrayList<Payroll> getPayroll() {
		return dataAccess.getPayroll();
	}
	
	@Override
	public void actualizarPayroll(Payroll payroll)
	{
		dataAccess.actualizarPayroll(payroll);
	}

	@Override
	public void guardarPayroll(Payroll payroll) {
		dataAccess.guardarPayroll(payroll);
	}
}