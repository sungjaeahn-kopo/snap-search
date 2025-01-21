package com.snap_search.lvalue.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.snap_search.lvalue.api.ApiPlayerTeam;
import com.snap_search.lvalue.api.ApiResponse;
import com.snap_search.lvalue.model.Player;
import com.snap_search.lvalue.repository.PlayerRepository;

import reactor.core.publisher.Mono;

@Service
public class PlayerServiceImpl implements PlayerService {

	private final WebClient webClient;
	private final PlayerRepository playerRepository;

	public PlayerServiceImpl(@Qualifier("webClientForDefault") WebClient webClient,
		PlayerRepository playerRepository) {
		this.webClient = webClient;
		this.playerRepository = playerRepository;
	}

	@Override
	public List<Player> fetchAndSavePlayers() {
		// API 호출 및 데이터 저장 로직
		Mono<ApiResponse<ApiPlayerTeam>> responseMono = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/players/squads")
				.queryParam("team", 49)
				.build())
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<ApiResponse<ApiPlayerTeam>>() {
			});

		ApiResponse<ApiPlayerTeam> response = responseMono.block();

		if (response != null && response.getResponse() != null) {
			List<Player> players = response.getResponse().stream()
				.flatMap(playerTeam -> {
					// playerTeam 내 플레이어 리스트 처리
					return playerTeam.getPlayers().stream().map(apiPlayer -> {
						// 기존 Player 객체를 찾거나 새로 생성
						Player existingPlayer = playerRepository.findById(apiPlayer.getId());

						if (existingPlayer != null) {
							// 기존 플레이어 업데이트
							existingPlayer.setName(apiPlayer.getName());
							existingPlayer.setAge(apiPlayer.getAge());
							existingPlayer.setNumber(apiPlayer.getNumber());
							existingPlayer.setPosition(apiPlayer.getPosition());
							existingPlayer.setPhoto(apiPlayer.getPhoto());
							existingPlayer.setTeamId(playerTeam.getTeam().getId()); // 팀 ID 설정
							return existingPlayer;
						} else {
							// 새로운 플레이어 생성
							return new Player(
								apiPlayer.getId(),
								apiPlayer.getName(),
								apiPlayer.getAge(),
								apiPlayer.getNumber(),
								apiPlayer.getPosition(),
								apiPlayer.getPhoto(),
								playerTeam.getTeam().getId() // 팀 ID 설정
							);
						}
					});
				})
				.collect(Collectors.toList());
			return playerRepository.saveAll(players);
		}
		return List.of();
	}
}
