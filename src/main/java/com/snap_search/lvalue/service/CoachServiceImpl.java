package com.snap_search.lvalue.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.snap_search.lvalue.dto.CareerDTO;
import com.snap_search.lvalue.dto.CoachDTO;
import com.snap_search.lvalue.repository.CoachRepository;

@Service
public class CoachServiceImpl implements CoachService {

	private final CoachRepository coachRepository;

	public CoachServiceImpl(CoachRepository coachRepository) {
		this.coachRepository = coachRepository;
	}

	@Override
	public Optional<CoachDTO> getCoachByTeamId(Long teamId) {
		Optional result = coachRepository.findCoachInfoByteamId(teamId);
		if (result.isEmpty()) {
			return Optional.empty(); // 결과가 없을 경우 null 반환
		}

		List<CareerDTO> careers = new ArrayList<>();
		Object[] rawData = (Object[])result.get();

		for (int i = 5; i < rawData.length; i += 5) {
			if (rawData[i] == null)
				break; // 더 이상 Career 데이터가 없으면 종료
			// CareerDTO의 id값은 bigInt이므로 Interger로 cast
			CareerDTO career = new CareerDTO((Integer)rawData[i], (String)rawData[i + 1],
				(String)rawData[i + 2],
				(String)rawData[i + 3], (String)rawData[i + 4]);
			careers.add(career);
		}

		// CareerDTO의 id값은 int이므로 Long로 cast
		CoachDTO coachDTO = new CoachDTO((Long)rawData[0], (String)rawData[1], (String)rawData[2],
			(String)rawData[3],
			(String)rawData[4], careers);

		return Optional.of(coachDTO);
	}
}
