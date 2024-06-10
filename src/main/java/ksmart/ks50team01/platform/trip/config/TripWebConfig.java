package ksmart.ks50team01.platform.trip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class TripWebConfig implements WebMvcConfigurer {
	private static String imageDirPath = "/home/teamproject/resources/tourapi/";

	/**
	 * 주소요청에 따른 외부파일 경로 설정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String rootPath = getOSFilePath();
		registry.addResourceHandler("/tourapi/**")
			.addResourceLocations(rootPath + imageDirPath)
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	public String getOSFilePath() {
		String rootPath = "file:///";
		String os = System.getProperty("os.name").toLowerCase();

		if(os.contains("win")) {
			rootPath = "file:///d:";
		}else if(os.contains("linux")) {
			rootPath = "file:///";
		}else if(os.contains("mac")){
			rootPath = "file:///Users/Shared";
		}
		return rootPath;
	}
}
