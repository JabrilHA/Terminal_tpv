package com.ipartek.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.modelo.Producto;
import com.ipartek.repositorio.ProductoRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class InicializarDatos {
	
	@Autowired
	private ProductoRepository productoRepo;

	@PostConstruct
	@Transactional
	public void cargarDatos() {
		
		productoRepo.save(new Producto(1, "Croquetas de bacalao", 3.00, "croquetas_baca.jpg", 10));
		productoRepo.save(new Producto(2, "Croquetas de jamón", 4.00, "croquetas_jam.jpg", 10));
		productoRepo.save(new Producto(3, "Gilda", 1.00, "gilda.jpg", 10));
		productoRepo.save(new Producto(4, "Tortilla de patatas", 1.80, "tortilla.jpg", 10));
		productoRepo.save(new Producto(5, "Pintxo de bacalao", 1.50, "bacalao.jpg", 10));
		productoRepo.save(new Producto(6, "Txakoli", 2.00, "txakoli.jpg", 21));
		productoRepo.save(new Producto(7, "Botellin de agua", 1.00, "botella_agua.jpg", 10));
		productoRepo.save(new Producto(8, "Zurito", 1.20, "zuri.jpg", 21));
		productoRepo.save(new Producto(9, "Caña", 2.20, "cana.jpg", 21));
		productoRepo.save(new Producto(10, "Pinta", 3.30, "pinta.jpg", 21));
		productoRepo.save(new Producto(11, "Txikito", 1.00, "txikito.jpg", 21));
	}
}
