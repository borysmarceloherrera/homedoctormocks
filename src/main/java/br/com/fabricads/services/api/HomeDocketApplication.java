package br.com.fabricads.services.api;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Log
@Configuration
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
@EnableSwagger2
public class HomeDocketApplication {


	@Bean
	@Primary
	public RestTemplate restTemplate() 
	                throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
	                    .loadTrustMaterial(null, acceptingTrustStrategy)
	                    .build();

	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

	    CloseableHttpClient httpClient = HttpClients.custom()
	                    .setSSLSocketFactory(csf)
	                    .build();

	    HttpComponentsClientHttpRequestFactory requestFactory =
	                    new HttpComponentsClientHttpRequestFactory();

	    requestFactory.setHttpClient(httpClient);
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	 }

           @Bean
	    public Docket api() {


	        return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("br.com.fabricads.services.api.controller"))
	            .paths(PathSelectors.any())
	            .build().apiInfo(apiInfo());
	    }


	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder().title("HomeDockerMocks")
	                .description("Microserviço HomeDoctorMocks.")
	                .version("1.0.0")
	                .build();
	    }



//	@Value("${config.api-govbr.MEMCACHED_URL}")
//	String urlMemcached;
//
//	@Bean
//	MemcachedClient memcachedClient(){
//
//
//		// Get a memcache client operating on the specified memcached locations.
//		MemcachedClient crunchifySpyMemCached;
//		try {
//			crunchifySpyMemCached = new MemcachedClient(new InetSocketAddress(urlMemcached, 11211));
//
//			log.info("=====> Approach-1: SpyMemcached <=====\n");
//
//			log.info("==> Connected to Crunchify's Local Server Successfully." + " Host: "+urlMemcached+", Port: 11211");
//
//			// Set an object in the cache (using the default transcoder) regardless of any existing value.
//			// The exp value is passed along to memcached exactly as given, and will be processed per the memcached protocol specification:
//
//			// The actual value sent may either be Unix time (number of seconds since January 1, 1970, as a 32-bit value), or a number of seconds starting from
//			// current time. In the latter case, this number of seconds may not exceed 60*60*24*30 (number of seconds in 30 days); if the number sent by a
//			// client is larger than that, the server will consider it to be real Unix time value rather than an offset from current time.
//			crunchifySpyMemCached.set("Crunchify", 2000, "New York");
//			crunchifySpyMemCached.set("Google", 2000, "Mountain View");
//			crunchifySpyMemCached.set("PayPal", 2000, "San Jose");
//			crunchifySpyMemCached.set("Twitter", 2000, "San Francisco");
//			crunchifySpyMemCached.set("Amazon", 2000, "Seattle");
//
//			log.info("==> Total 5 Records added to MemCached using net.spy.spymemcached method\n");
//
//			// Get with a single key and decode using the default transcoder.
//			log.info("Key: Google, Value: " + crunchifySpyMemCached.get("Google"));
//			log.info("Key: PayPal, Value: " + crunchifySpyMemCached.get("PayPal"));
//			log.info("Key: Twitter, Value: " + crunchifySpyMemCached.get("Twitter"));
//			log.info("Key: Amazon, Value: " + crunchifySpyMemCached.get("Amazon"));
//			log.info("Key: Crunchify, Value: " + crunchifySpyMemCached.get("Crunchify"));
//
//			log.info("==> Total 5 Records Retrieved from MemCached using net.spy.spymemcached method\n");
//
//			// Delete the given key from the cache.
//			crunchifySpyMemCached.delete("Crunchify");
//			log.info("==> Key:Crunchify deleted successfully\n");
//
//			log.info("Key: Crunchify, Value: " + crunchifySpyMemCached.get("Crunchify"));
//			log.info("==> If no record found, it returns NULL\n");
//			return crunchifySpyMemCached;
//		} catch (Exception e) {
//			// Prints this throwable and its backtrace to the standard error stream
//			e.printStackTrace();
//		}
//		return null;
//
//	}


}
