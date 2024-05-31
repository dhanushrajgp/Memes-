package com.crio.starter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "memes")
@NoArgsConstructor
public class MemeEntity extends BaseEntity{
    
    @Id
    private ObjectId id;

    private String name;

    private String url;

    private String caption;

}
