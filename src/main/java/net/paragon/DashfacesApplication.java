package net.paragon;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.paragon.dashfaces.domain.entity.Role;
import net.paragon.dashfaces.domain.entity.UserProfile;
import net.paragon.dashfaces.domain.entity.Vehicle;
import net.paragon.dashfaces.repository.UserRepository;
import net.paragon.dashfaces.repository.VehicleRepository;

@SpringBootApplication
public class DashfacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashfacesApplication.class, args);
	}

	@Component
	class DemoCommandLineRunner implements CommandLineRunner {

		@Autowired
		private VehicleRepository vehicleRepository;

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private PasswordEncoder passwordEncoder;

		@Override
		public void run(String... args) throws Exception {
			if (vehicleRepository.findByMake("Audi").isEmpty()) {
				Vehicle audi = new Vehicle();
				audi.setId(UUID.randomUUID());
				audi.setVehicleIdentityNumber("Reg#1234");
				audi.setMake("Audi");
				audi.setModel("Q5");
				vehicleRepository.save(audi);

				Vehicle tesla = new Vehicle();
				tesla.setId(UUID.randomUUID());
				tesla.setVehicleIdentityNumber("Reg#6789");
				tesla.setMake("Tesla");
				tesla.setModel("Model S");

				vehicleRepository.save(tesla);
			}

			if (!userRepository.findByUsername("application-user").isPresent()) {
				UserProfile user = new UserProfile();
				user.setUsername("application-user");
				user.setPassword(passwordEncoder.encode("password"));
				user.grantAuthority(Role.ROLE_ADMIN);

				userRepository.save(user);
			}
		}
	}
}
