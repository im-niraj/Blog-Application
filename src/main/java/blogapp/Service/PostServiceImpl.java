package blogapp.Service;

import blogapp.Model.Comment;
import blogapp.Model.Post;
import blogapp.Repo.CommentRepo;
import blogapp.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Override
    public List<Post> getAllPost() {

        return postRepo.findAll();
    }

    @Override
    public Post getPostById(int id) {
        return postRepo.findById(id).get();
    }

    @Override
    public String createPost(Post post) {
        postRepo.save(post);
        return "New Post Added successfully..";
    }

    @Override
    public Post updatePostById(Post post, int id) {
        Post post1 = postRepo.findById(id).get();
        post1.setTitle(post.getTitle());
        post1.setDescription(post.getDescription());
        return postRepo.save(post1);
    }

    @Override
    public String deletePostById(int id) {
        List<Comment> comments = commentRepo.getAllCommentByPostId(id);
        if(comments.size()>0){
            commentRepo.deleteByPostId(id);
            postRepo.deleteById(id);
            return "Post deleted successfully";
        }

        postRepo.deleteById(id);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> pageAndSort(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.DESC, sortBy);
        return postRepo.findAll(pageable).toList();
    }
}
