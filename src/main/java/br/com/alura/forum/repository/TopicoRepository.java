package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeCurso);

    // forma de filtrar sem usar o springData
    //@Query("SELECT t FROM topico t WHERE t.curso.nome = :nomeCurso")
    //List<Topico> filtrarPeloNomeDoCurso(@Param("nomeCurso") nomeCurso);
}
