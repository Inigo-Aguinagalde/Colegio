package com.Colegio.colegio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private AlumnoRespository AlumnoRespository;
	@Autowired
	private CicloRepository CicloRepository;
	@Autowired
	private DeleteAlumnoRespository DAR;
	@Autowired
	private DeleteCiclosRespository DCR;
	
	@RequestMapping(path="/addAlumnos") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name,@RequestParam String apellido,@RequestParam int ciclo) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Alumnos a = new Alumnos();

		a.setNombre(name);
		a.setApellido(apellido);
		a.setId_ciclo(ciclo);
		AlumnoRespository.save(a);

		return "saved";

	}

	@RequestMapping(path="/addCiclos") // Map ONLY POST Requests
	public @ResponseBody String addNewCiclo (@RequestParam String name,@RequestParam String especialidad) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Ciclos c = new Ciclos();

		c.setName(name);
		c.setEspecialidad(especialidad);
		CicloRepository.save(c);

		return "saved";

	}

	@RequestMapping(path="/deleteAlumno") // Map ONLY POST Requests
	public Alumnos deleteAlumno(@RequestParam int id) {
		
	
		return DAR.deleteById(id);
		
	}

	@RequestMapping(path="/deleteCiclo") // Map ONLY POST Requests
	public Ciclos deleteCiclo(@RequestParam int id) {
		
		
		return DCR.deleteById(id);
		
	}

	@RequestMapping(path="/updateCiclo") // Map ONLY POST Requests
	public @ResponseBody String UpdateCiclo (@RequestParam String name,@RequestParam String especialidad,@RequestParam int id) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Ciclos c = new Ciclos();
		
		c.setId_ciclo(id);
		c.setName(name);
		c.setEspecialidad(especialidad);
		CicloRepository.save(c);

		return "saved";

	}

	@RequestMapping(path="/updateAlumno") // Map ONLY POST Requests
	public @ResponseBody String updateAlumno (@RequestParam String name,@RequestParam String apellido,@RequestParam int id_ciclo,@RequestParam int id_personas ) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Alumnos a = new Alumnos();
		
		a.setId_ciclo(id_ciclo);
		a.setNombre(name);
		a.setApellido(apellido);
		a.setId_personas(id_personas);
		AlumnoRespository.save(a);

		return "saved";

	}




	@GetMapping(path="/allAlumnos")
	public @ResponseBody Iterable<Alumnos> getAllUsers() {
		// This returns a JSON or XML with the users
		return AlumnoRespository.findAll();
	}

	@GetMapping(path="/allCiclos")
	public @ResponseBody Iterable<Ciclos> TodosCiclos() {
		// This returns a JSON or XML with the users
		return CicloRepository.findAll();
	}

	@GetMapping(path="/byName")
	public @ResponseBody Alumnos busquedaNombre(String name){

		return AlumnoRespository.findByNombre(name);

	}

	@GetMapping(path="/byCiclo")
	public @ResponseBody Ciclos busquedaCiclo(String ciclo){

		return CicloRepository.findByEspecialidad(ciclo);

	}
}