package br.com.heitor.samba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.heitor.samba.dto.StatisticsDTO;
import br.com.heitor.samba.service.VideoService;

@RestController
@RequestMapping(value = "statistics")
public class StatisticsController {
	@Autowired
	VideoService videoService;

	@GetMapping()
	public ResponseEntity<StatisticsDTO> getStatistics() {
		StatisticsDTO statistics = null;
		try {
			statistics = videoService.getStatistics();
		} catch (Exception e) {
			System.out.println(e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.OK).body(statistics);
	}

}
