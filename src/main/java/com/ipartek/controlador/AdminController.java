package com.ipartek.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.ipartek.modelo.Producto;
import com.ipartek.repositorio.ProductoRepository;

import java.io.File;
import java.io.IOException;


@Controller
public class AdminController {

	@Autowired
	private ProductoRepository productoRepo;

	@PostMapping("/guardarProducto")
	public String guardarProducto(@ModelAttribute @Validated Producto objeto_entidad, BindingResult result,
			@RequestParam("foto") MultipartFile file, Model model) {
		
		String rutaDeGuardado ="C:\\Users\\Jabril\\Documents\\workspace-spring-tool-suite-4-4.21.0.RELEASE\\Terminal_tpv\\src\\main\\resources\\static\\images\\productos";
				
		File directorio = new File(rutaDeGuardado);
		if (!directorio.exists()) {
			directorio.mkdirs();
		}

		try {
			String nombreArchivo = file.getOriginalFilename();
			File archivo = new File(rutaDeGuardado + "//" + nombreArchivo);
			file.transferTo(archivo);
			objeto_entidad.setFoto(nombreArchivo);
			productoRepo.save(objeto_entidad);
			model.addAttribute("mensaje", "Producto guardado correctamente");
			
		} catch (IOException e) {
			System.out.println("ERROR: fallo al manejar el file");
		}

		return "redirect:/administracion_productos";
	}

	@RequestMapping("/modificacionProductoCreado")
	public String modificacionProductoCreada(@ModelAttribute Producto objeto_entidad) {
		productoRepo.save(objeto_entidad);

		return "redirect:/administracion_productos";
	}

	@RequestMapping("/modificarProducto/{id}")
	public String modificarProducto(Model model, @PathVariable int id) {
		Optional<Producto> producto = productoRepo.findById(id);
		model.addAttribute("producto", producto);

		return "modificar_page";
	}

}
