package br.com.heitor.samba.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.com.heitor.samba.model.VideoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoDTO {
	@NotNull
	@Digits(fraction = 2, integer = 20)
	private Double duration;
	@NotNull
	@Digits(fraction = 0, integer = 20)
	private Long timestamp;

	public VideoModel toModel() {
		return new VideoModel(this.duration, this.timestamp);
	}
}
