package com.rusher.interfaces.ws;


import com.rusher.domain.protocol.User;
import org.junit.Test;

public class Example {
	
	private static final String KEY = "14d0881c-68b8-4de7-8ef5-b2140ba2780c";
	private static final String SECRET = "0440198DB0B9D02BBF0F240AB220208A";

	@Test
	public void TestUser() throws Exception{
        Bihang bihang = BihangBuilder.getInstance()
                .build(KEY, SECRET);
        User user = bihang.getUser();
        System.out.println(user.getEmail());
    }

	
}
