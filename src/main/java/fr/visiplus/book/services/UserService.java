package fr.visiplus.book.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.visiplus.book.dtos.BookDTO;
import fr.visiplus.book.dtos.LoginRequest;
import fr.visiplus.book.dtos.RegisterRequest;
import fr.visiplus.book.dtos.ReservationDTO;
import fr.visiplus.book.dtos.UserDTO;
import fr.visiplus.book.entities.Reservation;
import fr.visiplus.book.entities.User;
import fr.visiplus.book.repositories.UserRepository;


@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<UserDTO> login(final LoginRequest login) {
		Optional<User> user = this.userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		UserDTO userDTO = null;
		if (user.isPresent()) {
			userDTO = convert(user.get());
		}
		return Optional.ofNullable(userDTO);
	}
	
	public UserDTO register(RegisterRequest register) {
		User user = new User();
		user.setUsername(register.getUsername());
		user.setPassword(register.getPassword());
		return convert(userRepository.save(user));		
	}

	private UserDTO convert(final User user) {		
		UserDTO userDTO = new UserDTO(user.getId(), user.getUsername());
		userDTO.getReservations().addAll(
				user.getReservations().stream().map((resa) -> convert(resa)).collect(Collectors.toList())
				);
		return userDTO;
	}
	
	private ReservationDTO convert(final Reservation reservation) {
		BookDTO bookDTO = new BookDTO(
				reservation.getBook().getId(),
				reservation.getBook().getName(),
				reservation.getBook().getDescription(),
				reservation.getBook().getStatus());
		ReservationDTO resaDTO = new ReservationDTO(reservation.getId(), reservation.getDateResa(), bookDTO);
		return resaDTO;
	}

}
