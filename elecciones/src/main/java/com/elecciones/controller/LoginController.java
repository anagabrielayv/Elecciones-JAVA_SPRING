package com.elecciones.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecciones.entity.Alumno;
import com.elecciones.entity.PartidoPolitico;
import com.elecciones.entity.Usuario;
import com.elecciones.entity.Voto;
import com.elecciones.repository.AlumnoRepository;
import com.elecciones.repository.PartidoPoliticoRepository;
import com.elecciones.repository.UsuarioRepository;
import com.elecciones.repository.VotoRepository;

@Controller
@RequestMapping("/usuario")
public class LoginController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	AlumnoRepository alumnoRepository;

	@Autowired
	PartidoPoliticoRepository partidoPoliticoRepository;

	@Autowired
	VotoRepository votoRepository;

	@PostMapping("/validarUsuario")
	public String validarUsuario(HttpServletRequest request, Usuario objUsuario, Model model,
			RedirectAttributes attributes) {

		Usuario usuarioBD = usuarioRepository.findByCorreoAndPassword(objUsuario.getCorreo(), objUsuario.getPassword());
		Alumno alumno = alumnoRepository.findByUsuario(usuarioBD);
		Voto voto = votoRepository.findByAlumno(alumno);// para saber que no tiene votos

		if (usuarioBD != null && voto == null) {
			List<PartidoPolitico> listaPartidos = partidoPoliticoRepository.findAll();
			request.getSession().setAttribute("id_usuario", usuarioBD.getCodigo());
			model.addAttribute("listaPartidos", listaPartidos);
			model.addAttribute("objUsuario", objUsuario);
			model.addAttribute("objAlumno", alumno);

			return "RegistrarVoto";

		} else {
			if (usuarioBD != null) {
				model.addAttribute("mensaje", "El usuario ya efectuo su voto");
			} else {
				model.addAttribute("mensaje", "Usuario y/o contraseña incorrecta");
			}
		}

		Usuario objUsuario1 = new Usuario();
		model.addAttribute("objUsuario", objUsuario1);
		return "Login";
	}
}
