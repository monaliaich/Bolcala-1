package com.tech.bolcala.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				contact =  @Contact(
						name = "Bol.com Team",
						email = "bolcalaboardteam@bol.com",
						url = ""),
				description = """
				The bol.com game
						Board Setup
							Each of the two players has his six pits in front of him. To the right of the six pits, each player has a larger pit.						Game Rules
						Game Rules
							The player who begins with the first move picks up all the stones in any of his own six pits, and sows the stones on to the right, one in each of the following pits, including his own big pit. No stones are put in the opponents' big pit. If the player's last stone lands in his own big pit, he gets another turn. This can be repeated several times before it's the other player's turn.
						Capturing Stones
						   	During the game the pits are emptied on both sides. Always when the last stone lands in an own empty pit, the player captures his own stone and all stones in the opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.
						The Game Ends
						   	The game is over as soon as one of the sides runs out of stones. The player who still has stones in his pits keeps them and puts them in his big pit. The winner of the game is the player who has the most stones in his big pit.
			""",
				title = "Bol.com Game",
				version = "1.0",
				license = @License(
						name = "Bol.com License",
						url = "https://www.investopedia.com/terms/l/licensing-agreement.asp"),
				termsOfService = "Terms&Condition"
				),
		servers = {
				@Server (
						description = "Dev Env",
						url = "http://localhost:8080"
						),
				@Server(
						description = "Prod Env",
						url = "")
				
		}
		/*security = {
				@SecurityRequirement(
						name = "bearerAuth")
		}*/
		)
/*@SecurityScheme(
		name = "bearerAuth",
		description="JWT Auth description",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
		)*/
public class OpenApiConfig {

}
