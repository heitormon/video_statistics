package br.com.heitor.samba.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.heitor.samba.commons.VideoErrorEnum;
import br.com.heitor.samba.dto.StatisticsDTO;
import br.com.heitor.samba.model.VideoModel;
import br.com.heitor.samba.repository.VideoRepository;

@Service
public class VideoService {
	@Autowired
	VideoRepository videoRepository;

	public void inserirVideo(VideoModel video) throws Exception {
		long current_seconds = TimeUnit.MILLISECONDS.toSeconds(new Date().getTime());
		long difference = Math.subtractExact(current_seconds, TimeUnit.MILLISECONDS.toSeconds(video.getTimestamp()));
		if (difference > 60 || difference < 0)
			throw new Exception(VideoErrorEnum.INVALID_TIMESTAMP.getValor());
		videoRepository.save(video);
	}

	public List<VideoModel> getVideo() {
		return videoRepository.findAll();
	}

	public void deleteVideos() {
		videoRepository.deleteAll();
		
	}

	public StatisticsDTO getStatistics() {
		return videoRepository.getStatistics();
	}
}
