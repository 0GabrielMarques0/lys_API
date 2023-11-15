package br.com.lys.services;
import br.com.lys.models.post.Post;
import br.com.lys.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostService postService;

    private Post post;

    @BeforeEach
    void setUp() {
        post = new Post();
    }

    @Test
    void createTest() {
        when(postRepository.save(any(Post.class))).thenReturn(post);
        Post result = postService.create(post);
        assertNotNull(result);
        verify(postRepository).save(post);
    }

    @Test
    void readTest() {
        Long id = 1L;
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        Post result = postService.read(id);
        assertNotNull(result);
        verify(postRepository).findById(id);
    }

    @Test
    void deleteTest() {
        Long id = 1L;
        doNothing().when(postRepository).deleteById(id);
        postService.delete(id);
        verify(postRepository).deleteById(id);
    }

    @Test
    void findAllTest() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Post> page = new PageImpl<>(Collections.singletonList(post));
        when(postRepository.findAll(pageRequest)).thenReturn(page);
        Page<Post> result = postService.findAll(pageRequest);
        assertNotNull(result);
        verify(postRepository).findAll(pageRequest);
    }

    @Test
    void updateTest() {
        Long id = 1L;
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);
        Post updatedPost = new Post();
        Post result = postService.update(id, updatedPost);
        assertNotNull(result);
        verify(postRepository).findById(id);
        verify(postRepository).save(post);
    }
}
