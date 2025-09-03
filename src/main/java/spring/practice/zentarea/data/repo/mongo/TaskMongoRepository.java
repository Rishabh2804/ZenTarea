package spring.practice.zentarea.data.repo.mongo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.practice.zentarea.model.mongo.TaskDocument;

@Repository("taskMongoRepository")
@ConditionalOnProperty(name = "spring.data.mongodb.uri")
public interface TaskMongoRepository extends MongoRepository<TaskDocument, String> {
    // MongoDB-specific queries can be added here if needed
}
