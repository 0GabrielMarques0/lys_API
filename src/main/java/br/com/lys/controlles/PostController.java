package br.com.lys.controlles;

import br.com.lys.models.post.PostDetails;
import br.com.lys.models.post.PostRecord;
import br.com.lys.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDetails> save(@RequestBody PostRecord post, UriComponentsBuilder builder){
        var postAux = postService.create(post.toPost());
        var uri = builder.path("/posts/{id}").buildAndExpand(postAux.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostDetails(postAux));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetails> read(@PathVariable Long id){
        var post = postService.read(id);
        if(post == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PostDetails(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault Pageable page){
        var posts = postService.findAll(page);
        if(posts.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts.stream().map(PostDetails::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDetails> update(@PathVariable Long id, @RequestBody PostRecord post){
        var postAux = postService.update(id, post.toPost());
        if(postAux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PostDetails(postAux));
    }


}
