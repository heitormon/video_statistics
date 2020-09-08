package br.com.heitor.samba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.heitor.samba.dto.StatisticsDTO;
import br.com.heitor.samba.model.VideoModel;

@Repository
public interface VideoRepository extends JpaRepository<VideoModel, Long> {

	@Query(value = "SELECT "
				 + "ISNULL(SUM(duration),0) as sum,"
				 + "ISNULL(AVG(duration),0) as avg,"
			     + "ISNULL(MAX(duration),0)as max,"
			     + "ISNULL(MIN(duration),0) as min,"
			     + "COUNT(*) as count " 
			     + "FROM video_info  "
			     + " WHERE DATEDIFF(SECOND,DATEADD(MS, timestamp, '1970-01-01'),CURRENT_TIMESTAMP) + 10800 < 60"
			     , nativeQuery = true)
	StatisticsDTO getStatistics();

}
