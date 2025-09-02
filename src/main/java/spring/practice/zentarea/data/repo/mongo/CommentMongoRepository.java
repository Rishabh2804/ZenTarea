package spring.practice.zentarea.data.repo.mongo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.practice.zentarea.model.mongo.CommentDocument;

import java.util.List;

@Repository("commentMongoRepository")
@ConditionalOnProperty(name = "spring.data.mongodb.uri")
public interface CommentMongoRepository extends MongoRepository<CommentDocument, String> {
    List<CommentDocument> findByTaskId(String taskId);
}