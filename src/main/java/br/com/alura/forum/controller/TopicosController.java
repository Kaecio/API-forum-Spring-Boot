package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public  List<TopicoDto> listaFiltroById(String nomeCurso){
        if (nomeCurso == null){
            System.out.println(nomeCurso);
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        }else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }

    //forma para mostrar que esse é uma requisicao de inclusao
    //@RequestMapping(value = "/topicos", method = RequestMethod.POST)
    @PostMapping
    @Transactional // dispara o commit no banco de dados

    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    //metodo getOne antigo:
    //Topico topico = topicoRepository.getOne(id);

    //Utilizar agora o metodo getReferenceById:

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id){

       Optional<Topico> topico =  topicoRepository.findById(id);
       if (topico.isPresent()){
           return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
       }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional // dispara o commit no banco de dados
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
        Optional<Topico> optional =  topicoRepository.findById(id);
        if (optional.isPresent()){
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok( new TopicoDto(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional // dispara o commit no banco de dados

    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> optional =  topicoRepository.findById(id);
        if (optional.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok( ).build();
        }
        return ResponseEntity.notFound().build();
    }
}
