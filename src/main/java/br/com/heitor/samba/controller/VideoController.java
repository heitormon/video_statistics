package br.com.heitor.samba.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.heitor.samba.commons.VideoErrorEnum;
import br.com.heitor.samba.dto.VideoDTO;
import br.com.heitor.samba.model.VideoModel;
import br.com.heitor.samba.service.VideoService;

@RestController
@RequestMapping(value = "videos")
public class VideoController {

	@Autowired
	VideoService videoService;

	@PostMapping(consumes = { "application/json" })
	public ResponseEntity<Object> inserirVideo(@Valid @RequestBody VideoDTO newVideo) {
		try {
			videoService.inserirVideo(newVideo.toModel());
		} catch (Exception e) {

			if (e.getMessage() == VideoErrorEnum.INVALID_TIMESTAMP.getValor())
				throw new ResponseStatusException(HttpStatus.NO_CONTENT);

		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@GetMapping()
	public ResponseEntity<List<VideoModel>> getVideo() {
		List<VideoModel> listaVideos = videoService.getVideo();
		return ResponseEntity.status(HttpStatus.OK).body(listaVideos);
	}

	@DeleteMapping()
	public ResponseEntity<Object> apagarVideos() {
		try {
			videoService.deleteVideos();
		} catch (Exception e) {
			System.out.println(e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
