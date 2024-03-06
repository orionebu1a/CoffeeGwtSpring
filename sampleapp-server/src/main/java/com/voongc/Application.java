package com.voongc;


import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voongc.rpc.RPCUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Map;

@ServletComponentScan
@SpringBootApplication(scanBasePackages={"com.voongc.repositories", "com.voongc.rpc", "com.voongc.web.servlet", "com.voongc.web.testController"})
public class Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

}
