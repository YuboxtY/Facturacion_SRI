package Facturacion.Facturacion.Servicio;

import Facturacion.Facturacion.Modelo.Cliente;
import Facturacion.Facturacion.Repositorio.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    private final ClienteRepository clienteRepository;

    public ClienteServicio(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crearCliente(Cliente cliente) {
        // Validaciones básicas
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
        }

        if (cliente.getEmail() != null &&
                !cliente.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (cliente.getTelefono() != null &&
                !cliente.getTelefono().matches("\\d{7,15}")) {
            throw new IllegalArgumentException("Teléfono inválido. Debe tener entre 7 y 15 dígitos.");
        }

        // Evitar duplicados por email
        Optional<Cliente> existente = clienteRepository.findAll()
                .stream()
                .filter(c -> c.getEmail() != null && c.getEmail().equals(cliente.getEmail()))
                .findFirst();
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente con este email.");
        }

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente actualizarCliente(Long id, Cliente datos) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        if (datos.getNombre() != null && !datos.getNombre().isBlank()) {
            cliente.setNombre(datos.getNombre());
        }

        if (datos.getEmail() != null &&
                datos.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            cliente.setEmail(datos.getEmail());
        }

        if (datos.getTelefono() != null && datos.getTelefono().matches("\\d{7,15}")) {
            cliente.setTelefono(datos.getTelefono());
        }

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("No existe cliente con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
