package blogapp.Service;

import blogapp.Model.Post;

import java.util.List;

public interface PostService {
    public List<Post> getAllPost();
    public Post getPostById(int id);
    public String createPost(Post post);
    public Post updatePostById(Post post, int id);
    public String deletePostById(int id);
    public List<Post> pageAndSort(int pageNo, int pageSize, String sortBy);
}
