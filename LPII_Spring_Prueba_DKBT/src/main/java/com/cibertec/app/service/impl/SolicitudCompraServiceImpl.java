package com.cibertec.app.service.impl;

import com.cibertec.app.dto.DetalleOrdenCompraDTO;
import com.cibertec.app.dto.DetalleSolicitudDTO;
import com.cibertec.app.dto.ProveedorPrecioDTO;
import com.cibertec.app.dto.SolicitudCompraFormDTO;
import com.cibertec.app.entity.DetalleOrdenCompra;
import com.cibertec.app.entity.DetalleSolicitudCompra;
import com.cibertec.app.entity.Empleado;
import com.cibertec.app.entity.OrdenCompra;
import com.cibertec.app.entity.Producto;
import com.cibertec.app.entity.Proveedor;
import com.cibertec.app.entity.Solicitud;
import com.cibertec.app.entity.SolicitudCompra;
import com.cibertec.app.repository.DetalleOrdenCompraRepository;
import com.cibertec.app.repository.DetalleSolicitudCompraRepository;
import com.cibertec.app.repository.DetalleSolicitudRepository;
import com.cibertec.app.repository.EmpleadoRepository;
import com.cibertec.app.repository.OrdenCompraRepository;
import com.cibertec.app.repository.ProductoProveedorRepository;
import com.cibertec.app.repository.ProductoRepository;
import com.cibertec.app.repository.ProveedorRepository;
import com.cibertec.app.repository.SolicitudCompraRepository;
import com.cibertec.app.repository.SolicitudRepository;
import com.cibertec.app.service.ProductoProveedorService;
import com.cibertec.app.service.SolicitudCompraService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitudCompraServiceImpl implements SolicitudCompraService {
	
	@Autowired
	private ProductoProveedorService productoProveedorService;
	
	@Autowired
	private SolicitudRepository solicitudRepository;
	
	@Autowired
	private DetalleOrdenCompraRepository detalleOrdenCompraRepository;
	
	@Autowired
	private DetalleSolicitudCompraRepository detalleSolicitudCompraRepository;
	
	
	@Autowired
	private OrdenCompraRepository ordenCompraRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private ProveedorRepository proveedorRepository;

	
    @Autowired
    private SolicitudCompraRepository repository;
    
    @Autowired
    private DetalleSolicitudRepository detalleSolicitudRepository;

    @Override
    public List<SolicitudCompra> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<SolicitudCompra> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public SolicitudCompra guardar(SolicitudCompra solicitudCompra) {
        return repository.save(solicitudCompra);
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<DetalleSolicitudDTO> obtenerProductosReabastecimiento() {
        List<Object[]> datos = detalleSolicitudRepository.listarProductosAprobadosAgrupados();
        List<DetalleSolicitudDTO> lista = new ArrayList<>();
        
        for (Object[] fila : datos) {
            Long idSolicitud = ((Number) fila[0]).longValue();
            Long idProducto = ((Number) fila[1]).longValue();
            String nombre = (String) fila[2];
            Integer totalCantidad = ((Number) fila[3]).intValue();

            List<ProveedorPrecioDTO> proveedores = productoProveedorService.listarProveedorConPrecio(idProducto);

            DetalleSolicitudDTO dto = new DetalleSolicitudDTO(idSolicitud, idProducto, nombre, totalCantidad, proveedores);
            lista.add(dto);
        }

        
        return lista;
    }
    
    @Override
    @Transactional
    public void guardarSolicitudConOrdenes(SolicitudCompraFormDTO dto) {
        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        SolicitudCompra solicitudCompra = new SolicitudCompra();
        solicitudCompra.setEmpleado(empleado);
        solicitudCompra.setFechaEmision(new Date());
        solicitudCompra.setObservaciones(dto.getObservaciones());
        solicitudCompra.setEstadoOrden(SolicitudCompra.EstadoOrden.Emitida);
        solicitudCompra.setTotalGasto(dto.getTotalGasto());

        repository.save(solicitudCompra);
        
        if (dto.getDetallesOrdenCompra() == null || dto.getDetallesOrdenCompra().isEmpty()) {
            // O bien lanzas error o simplemente devuelves sin crear órdenes
            throw new RuntimeException("No se han enviado detalles de la orden de compra.");
        }


        // Agrupar por proveedor
        Map<Long, List<DetalleOrdenCompraDTO>> detallesAgrupados =
                dto.getDetallesOrdenCompra().stream()
                        .collect(Collectors.groupingBy(DetalleOrdenCompraDTO::getIdProveedor));

        for (Map.Entry<Long, List<DetalleOrdenCompraDTO>> entry : detallesAgrupados.entrySet()) {
            Long idProveedor = entry.getKey();
            List<DetalleOrdenCompraDTO> detalles = entry.getValue();

            Proveedor proveedor = proveedorRepository.findById(idProveedor)
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

            OrdenCompra orden = new OrdenCompra();
            orden.setSolicitudCompra(solicitudCompra);
            orden.setProveedor(proveedor);

            BigDecimal totalOrden = BigDecimal.ZERO;

            List<DetalleOrdenCompra> detallesFinal = new ArrayList<>();
            for (DetalleOrdenCompraDTO d : detalles) {
                Producto prod = productoRepository.findById(d.getIdProducto())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                BigDecimal subTotal = d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad()));
                totalOrden = totalOrden.add(subTotal);

                DetalleOrdenCompra detalle = new DetalleOrdenCompra();
                detalle.setOrdenCompra(orden);
                detalle.setProducto(prod);
                detalle.setCantidad(d.getCantidad());
                detalle.setPrecioUnitario(d.getPrecioUnitario());
                detalle.setSubTotal(subTotal);

                detallesFinal.add(detalle);
            }

            orden.setTotal(totalOrden);
            ordenCompraRepository.save(orden);
            detalleOrdenCompraRepository.saveAll(detallesFinal);
        }

        // ---------------------------
        // Aquí el detalle_solicitud_compra
        // ---------------------------

        if (dto.getIdSolicitudesOriginales() != null) {
            for (Long idSol : dto.getIdSolicitudesOriginales()) {
                if (idSol == null) {
                    throw new RuntimeException("Uno de los IDs de solicitudes originales es null. Corrige el formulario o la llamada.");
                }
                
                Solicitud solicitudOriginal = solicitudRepository.findById(idSol)
                        .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + idSol));

                DetalleSolicitudCompra detalle = new DetalleSolicitudCompra();
                detalle.setSolicitudCompra(solicitudCompra);
                detalle.setSolicitud(solicitudOriginal);
                detalleSolicitudCompraRepository.save(detalle);
            }
        }



    }




}
