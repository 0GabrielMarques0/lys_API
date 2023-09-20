package br.com.lys.services;

import br.com.lys.models.post.Post;
import br.com.lys.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post create (Post post){
        return postRepository.save(post);
    }
    public Post read (Long id){
        return postRepository.findById(id).orElse(null);
    }
    public void delete (Long id){
        postRepository.deleteById(id);
    }
    public List <Post> findAll (){
        return postRepository.findAll();
    }
    public Post update (Long id, Post post){
        var postAux = postRepository.findById(id).orElse(null);
        if(postAux == null){
            return null;
        }
        if ( post.getTexto() != null){
            postAux.setTexto(post.getTexto());
        }
        return postRepository.save(postAux);
    }
}
