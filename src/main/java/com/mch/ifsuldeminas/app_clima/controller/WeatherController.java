package com.mch.ifsuldeminas.app_clima.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.mch.ifsuldeminas.model.*;
import com.mch.ifsuldeminas.parse.XStreamParser;
import com.mch.ifsuldeminas.service.WeatherForecastService;

@RestController
@RequestMapping("/api/clima")
@CrossOrigin(origins = {
	    "http://localhost:5173", 
	    "https://clima-pi-seven.vercel.app"
	})

public class WeatherController {
    
    @GetMapping("/cidades")
    public ListaCidades getCidades(@RequestParam String nome) throws Exception {
        String xml = WeatherForecastService.cidades(nome);
        XStreamParser<PrevisaoCidade, ListaCidades> xsp = new XStreamParser<>();
        return xsp.cidades(xml);
    }

    @GetMapping("/previsao/{id}")
    public PrevisaoCidade getPrevisao(@PathVariable int id) throws Exception {
        String xml = WeatherForecastService.previsoesParaSeteDias(id);
        XStreamParser<PrevisaoCidade, ListaCidades> xsp = new XStreamParser<>();
        return xsp.previsao(xml);
    }
}
