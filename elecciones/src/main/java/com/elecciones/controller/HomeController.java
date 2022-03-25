package com.elecciones.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.elecciones.entity.Usuario;


@Controller
public class HomeController {
	@GetMapping("/")
	public String mostrarHome(Model model) {
		Usuario objusuario = new Usuario();
		model.addAttribute("objUsuario", objusuario);
		return "Login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		if(request.getSession().getAttribute("id_usuario")!=null){
			request.getSession().removeAttribute("id_usuario");
		}
		return "redirect:/";
	}
	
}
