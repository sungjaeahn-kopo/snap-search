package com.snap_search.lvalue.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.snap_search.lvalue.api.ApiPlayerTeam;
import com.snap_search.lvalue.api.ApiResponse;
import com.snap_search.lvalue.dto.PlayerDTO;
import com.snap_search.lvalue.dto.TeamWithPlayersDTO;
import com.snap_search.lvalue.model.Player;
import com.snap_search.lvalue.model.Team;
import com.snap_search.lvalue.repository.PlayerRepository;
import com.snap_search.lvalue.repository.TeamRepository;

import reactor.core.publisher.Mono;

@Service
public class PlayerServiceImpl implements PlayerService {

	private final WebClient webClient;
	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;

	public PlayerServiceImpl(@Qualifier("webClientForDefault") WebClient webClient,
		PlayerRepository playerRepository,
		TeamRepository teamRepository) {
		this.webClient = webClient;
		this.playerRepository = playerRepository;
		this.teamRepository = teamRepository;
	}

	@Override
	public List<TeamWithPlayersDTO> fetchAndSavePlayers(List<Long> teamIds) {
		return teamIds.stream()
			.flatMap(teamId -> {
				// API 호출 및 데이터 저장 로직
				Mono<ApiResponse<ApiPlayerTeam>> responseMono = webClient.get()
					.uri(uriBuilder -> uriBuilder
						.path("/players/squads")
						.queryParam("team", teamId)
						.build())
					.retrieve()
					.bodyToMono(new ParameterizedTypeReference<ApiResponse<ApiPlayerTeam>>() {
					});

				ApiResponse<ApiPlayerTeam> response = responseMono.block();

				if (response != null && response.getResponse() != null) {
					return response.getResponse().stream().map(playerTeam -> {
						// 팀 정보를 가져오거나 생성
						Team team = getOrCreateTeam(
							playerTeam.getTeam().getId(),
							playerTeam.getTeam().getName(),
							playerTeam.getTeam().getLogo(),
							List.of(140L) // 리그 ID를 동적으로 설정 가능
						);

						// Player 데이터 저장
						List<PlayerDTO> players = playerTeam.getPlayers().stream().map(apiPlayer -> {
							Player player = playerRepository.findById(apiPlayer.getId()).orElse(new Player());
							player.setId(apiPlayer.getId());
							player.setName(apiPlayer.getName());
							player.setAge(apiPlayer.getAge());
							player.setNumber(apiPlayer.getNumber());
							player.setPosition(apiPlayer.getPosition());
							player.setPhoto(apiPlayer.getPhoto());
							player.setTeam(team); // Team 매핑
							playerRepository.save(player);

							return new PlayerDTO(
								player.getId(),
								player.getName(),
								player.getAge(),
								player.getNumber(),
								player.getPosition(),
								player.getPhoto()
							);
						}).collect(Collectors.toList());

						return new TeamWithPlayersDTO(players, team.getId());
					});
				}
				// 빈 스트림 반환
				return Stream.empty();
			})
			.collect(Collectors.toList());
	}

	// @Override
	// public List<TeamWithPlayersDTO> fetchAndSavePlayers(List<Long> teamIds) {
	// 	// API 호출 및 데이터 저장 로직
	// 	Mono<ApiResponse<ApiPlayerTeam>> responseMono = webClient.get()
	// 		.uri(uriBuilder -> uriBuilder
	// 			.path("/players/squads")
	// 			.queryParam("team", 34)
	// 			.build())
	// 		.retrieve()
	// 		.bodyToMono(new ParameterizedTypeReference<ApiResponse<ApiPlayerTeam>>() {
	// 		});
	//
	// 	ApiResponse<ApiPlayerTeam> response = responseMono.block();
	//
	// 	if (response != null && response.getResponse() != null) {
	// 		// 팀과 플레이어 데이터를 저장
	// 		List<TeamWithPlayersDTO> result = response.getResponse().stream().map(playerTeam -> {
	// 			// 팀 정보를 가져오거나 생성
	// 			Team team = getOrCreateTeam(playerTeam.getTeam().getId(), playerTeam.getTeam().getName(),
	// 				playerTeam.getTeam().getLogo(), List.of(39L)); // 예: 리그 ID
	//
	// 			// Player 데이터 저장
	// 			List<PlayerDTO> players = playerTeam.getPlayers().stream().map(apiPlayer -> {
	// 				Player player = playerRepository.findById(apiPlayer.getId()).orElse(new Player());
	// 				player.setId(apiPlayer.getId());
	// 				player.setName(apiPlayer.getName());
	// 				player.setAge(apiPlayer.getAge());
	// 				player.setNumber(apiPlayer.getNumber());
	// 				player.setPosition(apiPlayer.getPosition());
	// 				player.setPhoto(apiPlayer.getPhoto());
	// 				player.setTeam(team); // Team 매핑
	// 				playerRepository.save(player);
	//
	// 				return new PlayerDTO(
	// 					player.getId(),
	// 					player.getName(),
	// 					player.getAge(),
	// 					player.getNumber(),
	// 					player.getPosition(),
	// 					player.getPhoto()
	// 				);
	// 			}).collect(Collectors.toList());
	//
	// 			return new TeamWithPlayersDTO(players, team.getId());
	// 		}).collect(Collectors.toList());
	//
	// 		return result;
	// 	}
	// 	return List.of();
	// }

	// CountryLeagueMap을 생성하거나 가져오는 private 메소드
	private Team getOrCreateTeam(Long teamId, String teamName, String teamLogo, List<Long> leagueIds) {
		Team team = teamRepository.findById(teamId).orElse(new Team());
		team.setId(teamId);
		team.setName(teamName);
		team.setLogo(teamLogo);
		team.setLeagueIdList(leagueIds); // List<Long>를 쉼표 구분 문자열로 변환하여 저장
		return teamRepository.save(team);
	}
}

