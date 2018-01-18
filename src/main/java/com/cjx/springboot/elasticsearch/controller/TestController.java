package com.cjx.springboot.elasticsearch.controller;

import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjx.springboot.elasticsearch.entity.Article;
import com.cjx.springboot.elasticsearch.entity.Author;
import com.cjx.springboot.elasticsearch.entity.Tutorial;
import com.cjx.springboot.elasticsearch.repository.ArticleSearchRepository;

/**
 * @author chenjunxi
 *
 */
@RestController
public class TestController {

	protected static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private ArticleSearchRepository articleSearchRepository;

	@Autowired
	private ElasticsearchTemplate template;

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		if(!template.typeExists("springboot-elasticsearch", "article")){
			template.createIndex(Article.class);
		}
		template.putMapping(Article.class);
	}

	/**
	 * 往es添加一条数据
	 */
	@RequestMapping("/add")
	public void testSaveArticleIndex() {
		logger.info("add start...");
		Author author = new Author();
		author.setId(1L);
		author.setName("tianshouzhi");
		author.setRemark("java developer");

		Tutorial tutorial = new Tutorial();
		tutorial.setId(1L);
		tutorial.setName("elastic search");

		Article article = new Article();
		article.setId(1L);
		article.setTitle("springboot integreate elasticsearch");
		article.setAbstracts("springboot integreate elasticsearch is very easy");
		article.setTutorial(tutorial);
		article.setAuthor(author);
		article.setContent(
				"elasticsearch based on lucene,spring-data-elastichsearch based on elaticsearch,this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
		article.setPostTime(new Date());
		article.setClickCount(1L);

		articleSearchRepository.save(article);
		logger.info("add end...");
	}

	/**
	 * 查询
	 */
	@RequestMapping("/query")
	public void testSearch() {
		logger.info("query start...");
		String queryString = "springboot";// 搜索关键字
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
		Iterable<Article> searchResult = articleSearchRepository.search(builder);
		Iterator<Article> iterator = searchResult.iterator();
		while (iterator.hasNext()) {
			logger.info(iterator.next().toString());
		}
		logger.info("query end...");
	}
}
