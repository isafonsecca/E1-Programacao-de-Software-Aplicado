package softaplic.exercicio1.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
public class Usuario {

    @Id
    private String id;

    @NotBlank
    private String nome;

    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    @PositiveOrZero
    private int numeroDependentes;

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
