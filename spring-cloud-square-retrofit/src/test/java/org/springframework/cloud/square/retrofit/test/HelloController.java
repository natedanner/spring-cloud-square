/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.square.retrofit.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Spencer Gibb
 */
@RestController
public abstract class HelloController implements TestConstants {

	public static ArrayList<Hello> getHelloList() {
		ArrayList<Hello> hellos = new ArrayList<>();
		hellos.add(new Hello(HELLO_WORLD_1));
		hellos.add(new Hello(OI_TERRA_2));
		return hellos;
	}

	@RequestMapping(method = GET, path = "/hello")
	public Hello getHello() {
		return new Hello(HELLO_WORLD_1);
	}

	@RequestMapping(method = POST, path = "/hello")
	public Hello postHello(@RequestBody Hello hello) {
		return new Hello(hello.getMessage());
	}

	@RequestMapping(method = GET, path = "/hellos")
	public List<Hello> getHellos() {
		return getHelloList();
	}

	@RequestMapping(method = GET, path = "/hellostrings")
	public List<String> getHelloStrings() {
		ArrayList<String> hellos = new ArrayList<>();
		hellos.add(HELLO_WORLD_1);
		hellos.add(OI_TERRA_2);
		return hellos;
	}

	@RequestMapping(method = GET, path = "/helloparams")
	public List<String> getParams(@RequestParam("params") List<String> params) {
		return params;
	}

	@RequestMapping(method = GET, path = "/noContent")
	ResponseEntity<Void> noContent() {
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.HEAD, path = "/head")
	ResponseEntity<Void> head() {
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = GET, path = "/fail")
	String fail() {
		throw new RuntimeException("always fails");
	}

	@RequestMapping(method = GET, path = "/notFound")
	ResponseEntity<String> notFound() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body((String) null);
	}

}
