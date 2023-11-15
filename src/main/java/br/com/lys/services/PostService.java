package br.com.lys.services;
import br.com.lys.models.post.Post;
import br.com.lys.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Cacheable(value = "post", key = "#post.id")
    public Post create (Post post){
        return postRepository.save(post);
    }
    @Cacheable(value = "post", key = "#id")
    public Post read (Long id){
        return postRepository.findById(id).orElse(null);
    }
    @CacheEvict(value = "post", key = "#id")
    public void delete (Long id){
        postRepository.deleteById(id);
    }
    @Cacheable(value = "post", key = "#page.pageNumber")
    public Page<Post> findAll (Pageable page){
        return postRepository.findAll(page);
    }
    @CachePut(value = "post", key = "#id")
    public Post update (Long id, Post post){
        var postAux = postRepository.findById(id);
        if(postAux.isPresent()){
            modelMapper.map(post, postAux.get());
            return postRepository.save(postAux.get());
        } else {
            throw new RuntimeException("Post não encontrado");
        }
    }
}
