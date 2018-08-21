package br.com.cassunde.api;

import java.util.List;

import br.com.cassunde.model.Post;

public interface PostDAO {
	Post create(Post post);

	Post get(String key);

	Post update(Post post);

    List<Post> list();

    long count();

    void delete(String key);
}
