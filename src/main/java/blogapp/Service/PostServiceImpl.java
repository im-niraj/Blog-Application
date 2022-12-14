package blogapp.Service;

import blogapp.Exceptions.BlogException;
import blogapp.Model.Comment;
import blogapp.Model.Post;
import blogapp.Repo.CommentRepo;
import blogapp.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
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
    public Post getPostById(int id) throws BlogException{
        Optional<Post> optionalPost = postRepo.findById(id);
        if(optionalPost.isPresent()){
            return optionalPost.get();
        }
        throw  new BlogException("Post id is incorrect");
    }

    @Override
    public String createPost(Post post) {
        postRepo.save(post);
        return "New Post Added successfully..";
    }

    @Override
    public Post updatePostById(Post post, int id) throws BlogException{
        Optional<Post> optionalPost = postRepo.findById(id);
        if(optionalPost.isPresent()){
            optionalPost.get().setTitle(post.getTitle());
            optionalPost.get().setDescription(post.getDescription());
            return postRepo.save(optionalPost.get());
        }
        throw  new BlogException("Post id is incorrect");
    }


    @Override
    public String deletePostById(int id) throws BlogException{
        Optional<Post> optPost = postRepo.findById(id);
        if(optPost.isPresent()){
            Iterator itr = optPost.get().getComments().iterator();
            while(itr.hasNext()){
                Comment c = (Comment) itr.next();
                itr.remove();
                commentRepo.deleteById(c.getId());
            }
            postRepo.save(optPost.get());
            postRepo.deleteById(id);
            return "Post deleted successfully";
        }

        throw new BlogException("Post id is not correct");
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
