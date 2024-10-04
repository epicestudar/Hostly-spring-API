package com.example.hostly_api.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostly_api.Service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaRestController {
    @Autowired
    private ReservaService reservaService;
}
