package com.cjx.springboot.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cjx.springboot.elasticsearch.entity.Article;

/**
 * 泛型的参数分别是实体类型和主键类型
 *
 * @author chenjunxi
 *
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long> {

}
