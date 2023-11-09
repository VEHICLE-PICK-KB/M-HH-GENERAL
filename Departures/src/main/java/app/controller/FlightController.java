package app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import app.model.Flight;
import app.model.FlightRepository;
import app.model.TypeRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FlightController {
	
	@Autowired
	private FlightRepository repository;
	@Autowired
	private TypeRepository trepository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    
	@RequestMapping(value= {"/flightlist"})
    public String flightList(Model model) {	
		model.addAttribute("flights", repository.findAll());
    		
        return "flightlist";
    }
	//REST
    @RequestMapping(value="/flights", method = RequestMethod.GET)
    public @ResponseBody List<Flight> flightListRest() {	
        return (List<Flight>) repository.findAll();
    }    
	//REST
    @RequestMapping(value="/flight/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Flight> findFlightRest(@PathVariable("id") Long flightId) {	
    	return repository.findById(flightId);
    }
	
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addBook(Model model){
	model.addAttribute("flight"
	, new Flight());
	model.addAttribute("types", trepository.findAll());
	return "addflight";
	}
	@RequestMapping(value = "/save"
	, method = RequestMethod.POST)
	public String save(Flight flight){
	repository.save(flight);
	return "redirect:flightlist";
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteFlight(@PathVariable("id") Long flightId, Model model) {
	repository.deleteById(flightId);
	return "redirect:/flightlist";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}")
	public String editFlight(@PathVariable("id") Long flightId, Model model){
	model.addAttribute("flight", repository.findById(flightId));
	model.addAttribute("types", trepository.findAll());
	return "editflight";
	}
}