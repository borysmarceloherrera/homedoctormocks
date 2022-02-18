package br.com.fabricads.services.api.controller;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricads.services.api.vo.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Validated
@RestController
@AllArgsConstructor
@Service
@RequestMapping("/")
public class HomeDoctorAuthController {




	  
	  public void printarHeaders(HttpServletRequest request) {
		  try {
			  Enumeration<String> chaves = request.getHeaderNames();
			  do {
				  String element = chaves.nextElement();
				  log.info("chave: " + element + " valor: " +request.getHeader(element));
			  } while(chaves.hasMoreElements());
			  
			  for (Cookie cookie:request.getCookies()) {
				  log.info("cookie: " + cookie.getName() + " value: " +cookie.getValue());
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
    
		


	

	
	  
	  @PostMapping(path = "/auth", produces = "application/json")
	  public Usuario validarScaTicket(@RequestBody Usuario usuarioEntrada, HttpServletRequest request, HttpServletResponse httpServletResponse) {
		  		  
		  		 Usuario usuario = new Usuario();
		  		  try {
		  			usuario.setId("801");
		  			usuario.setName("Vitor de Oliveira");
		  			usuario.setEmail("vitor.junior@fabricads.com.br");
		  			usuario.setPassword("$2a$08$XKYhzbTfGdTXvm0l5BxJ3uk8xtwSw17L1oWDnfxNvcP3NTzlTc9Su");
		  			usuario.setCpf("31665178809");
		  			usuario.setBirthDate("1984-11-12T00:00:00.000Z");
		  			usuario.setCreatedAt("2022-02-08T21:45:51.767Z");
		  			usuario.setUpdatedAt("2022-02-18T13:09:03.254Z");
		  			usuario.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ODAxLCJpYXQiOjE2NDUyMTE4MjEsImV4cCI6MTY3Njc0NzgyMX0.1z_4tOpjyCCX4ubQtsxSMcm-ujKdA-TPc7q6-y8Y8S4");
		  		
		  		  } catch (Exception e) {
		  			  e.printStackTrace();
		  			
		  		  }
		  		  
		  		  return usuario;
	  }

	 




}
