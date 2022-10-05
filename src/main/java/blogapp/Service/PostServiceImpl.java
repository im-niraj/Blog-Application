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
import java.util.Optional;

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
        Optional<Post> optPost = postRepo.findById(id);
        if(optPost.isPresent()){
            if(optPost.get().getComments().size() > 0){

            }
            postRepo.deleteById(id);
            return "Post deleted successfully";
        }
        return "Post id is not correct";
    }

    @Override
    public List<Post> pageAndSort(int pageNo, int pageSize, String sortBy) {
        if(sortBy == null){
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            return postRepo.findAll(pageable).toList();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Direction.DESC, sortBy);
        return postRepo.findAll(pageable).toList();
    }
}
