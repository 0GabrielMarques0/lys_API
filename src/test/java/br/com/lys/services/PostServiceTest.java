package br.com.lys.services;

import br.com.lys.models.post.Post;
import br.com.lys.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    private PostService postService;
    private PostRepository mockedPostRepository;

    @BeforeEach
    void setUp() {
        mockedPostRepository = mock(PostRepository.class);
        postService = new PostService(mockedPostRepository);
    }

    @Test
    void createShouldSaveAndReturnPost() {
        Post post = new Post();
        when(mockedPostRepository.save(post)).thenReturn(post);

        Post result = postService.create(post);

        assertEquals(post, result);
    }

    @Test
    void readShouldReturnPostIfExists() {
        Post post = new Post();
        when(mockedPostRepository.findById(1L)).thenReturn(Optional.of(post));

        Post result = postService.read(1L);

        assertEquals(post, result);
    }

    @Test
    void readShouldReturnNullIfNotExists() {
        when(mockedPostRepository.findById(1L)).thenReturn(Optional.empty());

        Post result = postService.read(1L);

        assertNull(result);
    }

    @Test
    void deleteShouldCallDeleteById() {
        postService.delete(1L);
        verify(mockedPostRepository, times(1)).deleteById(1L);
    }

    @Test
    void findAllShouldReturnPageOfPosts() {
        Page<Post> page = new PageImpl<>(List.of(new Post()));
        when(mockedPostRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Post> result = postService.findAll(PageRequest.of(0, 10));

        assertEquals(page, result);
    }

    @Test
    void updateShouldUpdateAndReturnPost() {
        Post existingPost = new Post();
        Post newPost = new Post();
        newPost.setTexto("New Text");
        when(mockedPostRepository.findById(1L)).thenReturn(Optional.of(existingPost));
        when(mockedPostRepository.save(existingPost)).thenReturn(existingPost);

        Post result = postService.update(1L, newPost);

        assertEquals("New Text", result.getTexto());
    }

    @Test
    void updateShouldReturnNullIfPostDoesNotExist() {
        Post newPost = new Post();
        when(mockedPostRepository.findById(1L)).thenReturn(Optional.empty());

        Post result = postService.update(1L, newPost);

        assertNull(result);
    }
}
