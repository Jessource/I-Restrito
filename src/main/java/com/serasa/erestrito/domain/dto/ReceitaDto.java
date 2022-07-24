package com.serasa.erestrito.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.serasa.erestrito.domain.entity.Receita;
import com.serasa.erestrito.domain.enums.Restricao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceitaDto {

  @NotBlank
  @Length(min = 5, max = 50)
  private String titulo;

  @NotBlank
  @Length(min = 3, max = 255)
  private String ingredientes;

  @NotBlank
  @Length(min = 3)
  private String modoDeFazer;

  @NotBlank
  @Length(min = 3, max = 255)
  private String tempoDePreparo;

  @NotBlank
  @Length(min = 1, max = 255)
  private String rendimento;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Restricao restricao;

  public Receita converte() {
    Receita receita = new Receita();

    receita.setTitulo(this.getTitulo());
    receita.setIngredientes(this.getIngredientes());
    receita.setModoDeFazer(this.getModoDeFazer());
    receita.setTempoDePreparo(this.getTempoDePreparo());
    receita.setRendimento(this.getRendimento());
    receita.setRestricao(this.getRestricao());

    return receita;
  }
}