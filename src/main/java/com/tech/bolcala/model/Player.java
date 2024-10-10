package com.tech.bolcala.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Player {

    private int largePit;
    private boolean playerTurn;
    @NotNull
    private int selectedPit ;
    private String message ;
    private int[] boardSetUp;
    private String nextPlayerName;

}
