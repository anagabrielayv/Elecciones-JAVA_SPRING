package com.elecciones.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecciones.bean.EmailBean;
import com.elecciones.entity.Alumno;
import com.elecciones.entity.PartidoPolitico;
import com.elecciones.entity.Usuario;
import com.elecciones.entity.Voto;
import com.elecciones.repository.AlumnoRepository;
import com.elecciones.repository.PartidoPoliticoRepository;
import com.elecciones.repository.UsuarioRepository;
import com.elecciones.repository.VotoRepository;
import com.elecciones.service.IEmailService;


@Controller
@RequestMapping("/voto")
public class VotoController {


	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	VotoRepository votoRepository;
	
	@Autowired
	PartidoPoliticoRepository partidoPoliticoRepository;

	@Autowired
	IEmailService emailService;
	
	@PostMapping("/registrarVoto")
	public String registrarVoto(HttpServletRequest request,@RequestParam("idPartido") int idPartido,
			Model model, RedirectAttributes attributes) {
		
		PartidoPolitico partidoPolitico = partidoPoliticoRepository.findByCodigo(idPartido);		
		 
		int idUsuario = (int)request.getSession().getAttribute("id_usuario");
		Usuario usuario = usuarioRepository.findByCodigo(idUsuario);
		Alumno alumno = alumnoRepository.findByUsuario(usuario);

		if(partidoPolitico!=null ) {			
			Voto voto =new Voto();
			voto.setAlumno(alumno);
			voto.setPartido(partidoPolitico);			
			votoRepository.save(voto);
			
			model.addAttribute("mensaje", "Voto registrado con éxito");
	
			EmailBean datosEmail = new EmailBean();
			datosEmail.setsNumeroCertificado("N0001");
			datosEmail.setxCertificado(null);
			datosEmail.setsEmailTo(alumno.getUsuario().getCorreo());
			datosEmail.setsSubject("Voto Registrado" );
			datosEmail.setPartido(voto.getPartido().getNombrePartido());
			datosEmail.setNombreSolicitante(alumno.getNombres() + " " + alumno.getApellidoPaterno() + " " + alumno.getApellidoMaterno());
			
			emailService.enviarEmailVoto(datosEmail); // LLamada para el envio del correo
			Usuario objUsuario2 = new Usuario();
			model.addAttribute("objUsuario", objUsuario2);
			return "redirect:/";
		
		}else {
			model.addAttribute("mensaje", "seleccione el Partido Politico");
		}
	
		
		List<PartidoPolitico> listaPartidos = partidoPoliticoRepository.findAll();		
		model.addAttribute("listaPartidos", listaPartidos);		
		model.addAttribute("objAlumno", alumno);
		
		return "RegistrarVoto";
	}

}
