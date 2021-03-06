package com.proyecto.dao;

import java.util.List;

import com.proyecto.entity.Ciudadanos;
import com.proyecto.entity.Triaje;
import com.proyecto.entity.Usuarios;

public interface CiudadanosDAO {
	Ciudadanos registrarActualizaCiudadano(Ciudadanos c);
	List<Triaje> listaCiudadanos();
	Triaje registrarTriaje(Triaje c);
	Triaje findCiudadanos(int cod);
	Triaje updateTriaje(Triaje c);
	//acceso admin
	Usuarios loginSistema(String pLogin, String pContrasenia);
}
